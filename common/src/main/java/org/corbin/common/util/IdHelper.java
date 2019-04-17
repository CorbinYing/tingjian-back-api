package org.corbin.common.util;
public enum IdHelper {
    /**
     * 生成id的枚举对象
     */
    snowflake;

    private static volatile SnowFlake snowFlake;

    synchronized SnowFlake instanceSnowflake() {
        if (snowFlake == null) {
            snowFlake = new SnowFlake(3, 3);
        }
        return snowFlake;

    }

    /**
     * 产生下一个ID
     *
     * @return
     */
    public synchronized Long nextId2Long() {
        return Long.valueOf(instanceSnowflake().nextId());
    }

    public synchronized String nextId2String() {
        return String.valueOf(instanceSnowflake().nextId());
    }
}
