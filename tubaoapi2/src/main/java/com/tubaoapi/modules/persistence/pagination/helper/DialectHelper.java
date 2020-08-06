package com.tubaoapi.modules.persistence.pagination.helper;

import java.util.HashMap;
import java.util.Map;

import com.tubaoapi.modules.persistence.pagination.dialect.Dialect;
import com.tubaoapi.modules.persistence.pagination.dialect.DialectFactory;

public abstract class DialectHelper {
    private static Map<Dialect.Type, Dialect> MAPPERS = new HashMap<Dialect.Type, Dialect>();

    public static Dialect getDialect(Dialect.Type dialectType){
        if(MAPPERS.containsKey(dialectType)){
            return MAPPERS.get(dialectType);
        }else{
            Dialect dialect = DialectFactory.buildDialect(dialectType);
            MAPPERS.put(dialectType, dialect);
            return dialect;
        }
    }
}
