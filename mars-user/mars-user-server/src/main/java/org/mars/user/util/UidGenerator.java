package org.mars.user.util;

/**
 *
 *
 *
 * -----------------------------------------------
 * | 1 | 34 |  5 |  13 |  11 |
 * -----------------------------------------
 * 1 ： 符号位
 * 34 ： 时间戳 毫秒
 *  5 ： 应用ID
 * 13 ： 自赠数
 *  11： login_name 的hashcode
 *
 *
 * @author yaojian
 * @date 2019/9/10
 */
public class UidGenerator {


    //2016-01-01 00:00:00
    private static final   long EPOCH = 1451577600L;

    private static final  long GEN_BIT = 11L;
    private static final long WORKER_BIT = 5L;
    private static final long SEQ_BIT = 13L;

    private static final long SEQ_SHIFT = GEN_BIT;
    private static final long WORKER_SHIFT = GEN_BIT + SEQ_BIT;
    private static final long TIME_SHIFT = GEN_BIT+ SEQ_BIT+ WORKER_BIT;

    private static final long GEN_MASK = -1L ^ (-1L << GEN_BIT);
    private static final long SEQ_MASK = -1L ^ (-1L << SEQ_BIT);
    private static final long WORKER_MASK = -1L^(-1 << WORKER_BIT);


    private long lastTimestamp = -1;
    private long sequence = 0L;


    private long workerId;

    public UidGenerator(long workerId) {
        this.workerId = workerId;

        if (workerId > WORKER_MASK){
            throw  new RuntimeException(String.format("worker id [%s] larger than %s",workerId,WORKER_MASK));
        }
    }

    public  synchronized long nextId(long gen){

        long timestamp = currentTime();
        if (timestamp < lastTimestamp) {
            throw new RuntimeException(String.format("Clock moved backwards.  Refusing to generate id for %d " +
                    "milliseconds", lastTimestamp - timestamp));
        }


        if (lastTimestamp == timestamp) {
            sequence = (sequence + 1) & SEQ_MASK;
            if (sequence == 0) {
                timestamp = tilNextMillis(lastTimestamp);
            }
        } else {
            sequence = 0L;
        }

        lastTimestamp = timestamp;


        return (timestamp - EPOCH) << TIME_SHIFT | workerId << WORKER_SHIFT | sequence << SEQ_SHIFT | gen & GEN_MASK;

    }



    protected long tilNextMillis(long lastTimestamp) {
        long timestamp = currentTime();
        while (timestamp <= lastTimestamp) {
            timestamp = currentTime();
        }
        return timestamp ;
    }



    protected static long currentTime() {
        return System.currentTimeMillis() / 1000L;
    }


    public static void main(String[] args) {

        UidGenerator generator = new UidGenerator(5);
        System.out.println(currentTime());
        long hash = "tony".hashCode();
        System.out.println("hash="+ (hash& GEN_MASK));
        long uid = generator.nextId(hash);
        System.out.println("uid="+uid);
        long[] result = generator.decodeId(uid);
        System.out.println(result[3]);
        System.out.println(result[0]);
        System.out.println(result[1]);
        System.out.println(result[2]);

    }



    public long[] decodeId(long uid){
        long[] result = new long[4];
        result[0] = (uid >>> TIME_SHIFT) + EPOCH;
        result[1] = (uid >>> WORKER_SHIFT) & WORKER_MASK;
        result[2] = (uid >>> SEQ_SHIFT) & SEQ_MASK;
        result[3] = uid & GEN_MASK;
        return result;
    }






}
