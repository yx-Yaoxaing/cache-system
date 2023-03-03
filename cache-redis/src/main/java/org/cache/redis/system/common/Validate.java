package org.cache.redis.system.common;


public class Validate {

    public static <T> void parmValidate(T ... args){
        for (T arg : args) {
            if (arg == null) {
                throw new NullPointerException("Your parameter is empty:"+arg);
            }
        }

    }

}
