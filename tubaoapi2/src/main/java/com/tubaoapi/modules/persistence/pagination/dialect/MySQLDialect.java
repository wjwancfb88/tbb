package com.tubaoapi.modules.persistence.pagination.dialect;



public class MySQLDialect extends Dialect{
	
	private static final String  SELECT = "select ";
	private static final String FROM = "from ";
	private static final String WHERE = "where ";
	private static final String ORDER = "order ";
	private static final String DESC = "desc ";
	
	private static final String MAIN_TABLE_ALIAS = "m";
	private static final String SUB_QUERY_ALIAS = "s";
	
	
    @Override
    public boolean supportsLimit() {
        return true;
    }

    @Override
    public String getLimitString(final String sql, final long offset, final long rows) {
    	
    	String lineSql = getLineSql(sql).toLowerCase();
    	
    	
    	
    	int selectIdx = lineSql.indexOf(SELECT);
    	int fromIdx = lineSql.indexOf(FROM);
    	int orderIdx = lineSql.indexOf(ORDER);
    	//int descIdx = lineSql.indexOf(DESC);
    	//int whereIdx = lineSql.indexOf(WHERE);
    	
    	String selected = lineSql.substring(selectIdx+SELECT.length(), fromIdx).trim();
    	String mainTableName = lineSql.substring(fromIdx+FROM.length());
    	mainTableName = mainTableName.substring(0,mainTableName.indexOf(" "));
    	

    	
    	StringBuilder sb = new StringBuilder();
    	sb.append(SELECT).append(MAIN_TABLE_ALIAS).append(".* ");
    	sb.append(FROM ).append(mainTableName).append(" ").append(MAIN_TABLE_ALIAS);
    	sb.append(" join  (");

    	if(selected.indexOf("*")==-1){
    		sb.append(lineSql.replaceFirst(selected, "id"));
    	}else{
    		sb.append(lineSql.replaceFirst("[*]", "id"));
    	}
    	
    	
    	sb.append(" limit ").append(offset).append(",").append(rows);
    	sb.append(") ").append(SUB_QUERY_ALIAS).append(" on ");
    	sb.append(MAIN_TABLE_ALIAS).append(".id = ").append(SUB_QUERY_ALIAS).append(".id");
    	
    	if(orderIdx!=-1){
    		String s = lineSql.substring(orderIdx);
    		String[] a = s.split(" ");
    		if(a.length==4){
    			a[2] = MAIN_TABLE_ALIAS +  "." + a[2];
    			for(String b:a){
    				sb.append(" ").append(b);
    			}
    		}
    	}
    	
    	
    	
    	//System.out.println(sb.toString());
        return sb.toString();
    }
    
    @Override
    public String getCountString(String sql) {
    	sql = sql.toLowerCase();
    	int orderIndex = sql.indexOf("order");
    	if(orderIndex!=-1){
    		sql = sql.substring(0,orderIndex);
    	}
    	String newsql = "select count(1) " + sql.substring(sql.indexOf("from")) ;
    	
    	return newsql;
    }
    
    /*
    //@Override
    public String getPageSql(String sql) {
        StringBuilder sqlBuilder = new StringBuilder(sql.length() + 14);
        sqlBuilder.append(sql);
        sqlBuilder.append(" limit ?,?");
        return sqlBuilder.toString();
    }

    //@Override
    public Map setPageParameter(MappedStatement ms, Object parameterObject, BoundSql boundSql, Page page) {
        Map paramMap = super.setPageParameter(ms, parameterObject, boundSql, page);
        paramMap.put(PAGEPARAMETER_FIRST, page.getStartRow());
        paramMap.put(PAGEPARAMETER_SECOND, page.getPageSize());
        return paramMap;
    }*/
    

   
}
