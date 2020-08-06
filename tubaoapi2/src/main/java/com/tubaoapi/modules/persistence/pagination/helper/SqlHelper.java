package com.tubaoapi.modules.persistence.pagination.helper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.scripting.defaults.DefaultParameterHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tubaoapi.modules.persistence.pagination.dialect.Dialect;

public abstract class SqlHelper {
	private static Logger logger = LoggerFactory.getLogger(SqlHelper.class);

	/*
	public static long getCount(final MappedStatement ms,final Connection connection, BoundSql boundSql,final Object parameterObject,
			Dialect dialect) throws SQLException {
		//BoundSql boundSql = ms.getBoundSql(parameterObject);
		String countSql = dialect.getCountString(boundSql.getSql());

		logger.debug("Total count SQL [{}]", countSql);
		logger.debug("Parameters: {} ", parameterObject);

		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = connection.prepareStatement(countSql);
			DefaultParameterHandler handler = new DefaultParameterHandler(ms,parameterObject, boundSql);
			handler.setParameters(stmt);
			rs = stmt.executeQuery();

			long count = 0;
			if (rs.next()) {
				count = rs.getLong(1);
			}

			return count;
		} finally {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
		}
	}
	*/
	public static long getCount(final MappedStatement ms,final Connection connection, BoundSql boundSql,final Object parameterObject) throws SQLException {
		String countSql = boundSql.getSql();
		//logger.debug("Total count SQL [{}]", countSql);
		//logger.debug("Parameters: {} ", parameterObject);

		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = connection.prepareStatement(countSql);
			DefaultParameterHandler handler = new DefaultParameterHandler(ms,parameterObject, boundSql);
			handler.setParameters(stmt);
			rs = stmt.executeQuery();

			long count = 0;
			if (rs.next()) {
				count = rs.getLong(1);
			}

			return count;
		} finally {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
		}
	}


}
