package com.mtf.admin.common.config;

import com.google.common.collect.Maps;

import java.util.Map;

public class RoleGet {

    private static Map<Integer,Role> map = Maps.newHashMap();
    static{
        map.put(1, Role.admin);
        map.put(2, Role.superproxy);
        map.put(3, Role.proxy);
    }

    public static Role get(Integer key){
        return map.get(key);
    }
}
