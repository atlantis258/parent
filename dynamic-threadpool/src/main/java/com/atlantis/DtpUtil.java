package com.atlantis;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class DtpUtil {

    public static Map<String, DtpExecutor> map = new HashMap<>();

    public static Collection<DtpExecutor> getAllDtpExecutor(){
        return map.values();
    }

    public static Collection<String> getAllDtpExecutorNames(){
        return map.keySet();
    }

    public static DtpExecutor getDtpExecutor(String poolName) {
        return map.get(poolName);
    }

    public static void put(String name, DtpExecutor dtpExecutor) {
        map.put(name, dtpExecutor);
    }
}
