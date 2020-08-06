package com.tubaoapi.modules.persistence.pagination.dialect;


public class OracleDialect extends Dialect{
    @Override
    public boolean supportsLimit() {
        return true;
    }

    @Override
    public String getLimitString(String sql, long offset, long rows) {
        
        String _sql = getLineSql(sql);
        StringBuilder pagingSelect = new StringBuilder(_sql.length() + 100);
        if (offset >= 0) {
            pagingSelect.append("select * from ( select row_.*, rownum rownum_ from ( ");
        } else {
            pagingSelect.append("select * from ( ");
        }
        pagingSelect.append(_sql);
        if (offset >= 0) {
            String endString = offset + "+" + rows;
            pagingSelect.append(" ) row_ ) where rownum_ <= ")
                    .append(endString).append(" and rownum_ > ").append(offset);
        } else {
            pagingSelect.append(" ) where rownum <= ").append(rows);
        }

        return pagingSelect.toString();
    }

    
}
