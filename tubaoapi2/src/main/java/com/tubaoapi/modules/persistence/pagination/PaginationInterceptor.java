package com.tubaoapi.modules.persistence.pagination;

import java.util.Properties;

import org.apache.ibatis.builder.SqlSourceBuilder;
import org.apache.ibatis.cache.Cache;
import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tubaoapi.model.so.BaseSO;
import com.tubaoapi.modules.persistence.model.OrderBy;
import com.tubaoapi.modules.persistence.pagination.dialect.Dialect;
import com.tubaoapi.modules.persistence.pagination.helper.DialectHelper;
import com.tubaoapi.modules.persistence.pagination.helper.SqlHelper;
import com.tubaoapi.modules.persistence.pagination.model.PageRowBounds;
import com.tubaoapi.modules.persistence.pagination.util.StringUtils;


@Intercepts({ 
	//@Signature(type = StatementHandler.class, method = "prepare", args = { Connection.class }),
	@Signature(type = Executor.class,         method = "query",   args = {MappedStatement.class,Object.class,RowBounds.class,ResultHandler.class})
})
public class PaginationInterceptor implements Interceptor {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private Dialect dialect;
    
    
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
		Object[] args = invocation.getArgs();
		MappedStatement mappedStatement = (MappedStatement) args[0];
		Object parameter = args[1]; 
		RowBounds rowBounds = (RowBounds) args[2];
		//ResultHandler resultHandler = (ResultHandler) args[3];
		
		
		if (parameter instanceof OrderBy) { 
			OrderBy orderBy = (OrderBy)parameter;
			BoundSql boundSql = mappedStatement.getBoundSql(parameter);
			PageSqlSource sqlSource = new PageSqlSource(boundSql);
			MappedStatement statement = buildMappedStatement(mappedStatement,sqlSource);
			MetaObject msObject = SystemMetaObject.forObject(statement);
			
			// 后序排序绑定 ----------------------------------------------------------------------
			
			String newSql = boundSql.getSql();
			if(!hasOrderBy(newSql)){
				newSql = newSql + orderBy.getOrderSql();
			}
			
			
			// 物理分页 -------------------------------------------------------------------------
			
			if (rowBounds.getLimit() != RowBounds.NO_ROW_LIMIT) {
				
				//物理分页SQL
				newSql = dialect.getLimitString(newSql,rowBounds.getOffset(), rowBounds.getLimit());
				
				if (rowBounds instanceof PageRowBounds) { 
					PageRowBounds pageRowBounds = (PageRowBounds) rowBounds;
					
					//统计总数
					if (pageRowBounds.getPageRequest().isAutoCount()) {
						Executor executor = (Executor) invocation.getTarget();
						String countSql = dialect.getCountString(boundSql.getSql());
						msObject.setValue("sqlSource.boundSql.sql", countSql);
						BoundSql countBoundSql = statement.getBoundSql(parameter);
						Long count = null;
						Cache cache = statement.getCache();
						if (cache != null && statement.isUseCache()) {
							CacheKey cacheKey = executor.createCacheKey(statement, parameter, RowBounds.DEFAULT,countBoundSql);
							count = (Long) cache.getObject(cacheKey);
							if (count == null) {
								count = SqlHelper.getCount(statement, executor.getTransaction().getConnection(), boundSql,parameter);
								cache.putObject(cacheKey, count);
							}
						} else {
							count = SqlHelper.getCount(statement, executor.getTransaction().getConnection(), boundSql,parameter);
						}
						pageRowBounds.setCount(count);
					}
		        }

				// sql替换成物理分页SQL后，RowBounds重置
				args[2] = RowBounds.DEFAULT;
			}
			
			// 使用新SQL
			msObject.setValue("sqlSource.boundSql.sql", newSql);
			// 替换原来的mappedStatement
			args[0] = statement;
			
		}

		return invocation.proceed();
	}
    
    private class PageSqlSource implements SqlSource {
        BoundSql boundSql;
        public PageSqlSource(BoundSql boundSql) {
            this.boundSql = boundSql;
        }
        public BoundSql getBoundSql(Object parameterObject) {
            return boundSql;
        }
    }
    
    private SqlSource buildSqlSource(Configuration configuration, String originalSql,   
    Class<?> parameterType) {  
        SqlSourceBuilder builder = new SqlSourceBuilder(configuration);
        return builder.parse(originalSql, parameterType, null);  
    }  
    
    private MappedStatement buildMappedStatement(MappedStatement ms, SqlSource sqlSource) {
        MappedStatement.Builder builder = new MappedStatement.Builder(ms.getConfiguration(), ms.getId(), sqlSource, ms.getSqlCommandType());
        builder.resource(ms.getResource());
        builder.fetchSize(ms.getFetchSize());
        builder.statementType(ms.getStatementType());
        builder.keyGenerator(ms.getKeyGenerator());
        if (ms.getKeyProperties() != null && ms.getKeyProperties().length != 0) {
            StringBuilder keyProperties = new StringBuilder();
            for (String keyProperty : ms.getKeyProperties()) {
                keyProperties.append(keyProperty).append(",");
            }
            keyProperties.delete(keyProperties.length() - 1, keyProperties.length());
            builder.keyProperty(keyProperties.toString());
        }
        builder.timeout(ms.getTimeout());
        builder.parameterMap(ms.getParameterMap());
        builder.resultMaps(ms.getResultMaps());
        builder.resultSetType(ms.getResultSetType());
        builder.cache(ms.getCache());
        builder.flushCacheRequired(ms.isFlushCacheRequired());
        builder.useCache(ms.isUseCache());
        return builder.build();
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {
        String dialectClass = properties.getProperty("dialectClass");
        if(StringUtils.isBlank(dialectClass)){
            Dialect.Type databaseType = null;
            try{
                databaseType = Dialect.Type.valueOf(properties.getProperty("dialect").toUpperCase());
            }catch (Exception e){}

            if(null == databaseType){
                throw new RuntimeException("Plug-in [PaginationInterceptor] the dialect of the attribute value is invalid! Valid values for:"
                        + getDialectTypeValidValues());
            }
            dialect = DialectHelper.getDialect(databaseType);
        }else{
            try {
                dialect = (Dialect) Class.forName(dialectClass).newInstance();
            } catch (Exception e) {
                throw new RuntimeException("Plug-in [PaginationInterceptor] cannot create dialect instance by dialectClass: " + dialectClass);
            }
        }

    }

    private String getDialectTypeValidValues(){
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<Dialect.Type.values().length; i++){
            sb.append(Dialect.Type.values()[i].name())
                    .append(",");
        }
        return sb.toString();
    }
    
    
    private boolean hasOrderBy(String sql){
    	sql = sql.toLowerCase();
    	sql = sql.replaceAll("\t", "");
    	sql = sql.replaceAll("\n", "");
    	sql = sql.replaceAll(" +", " "); 
    	return sql.indexOf("order by")!=-1;
    }
}
