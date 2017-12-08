package com.example.demo.dao;

import com.example.demo.util.ConnectionDB;
import redis.clients.jedis.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kawano on 2017/12/4 14:24.
 */
public class RedisDao {

    private static ShardedJedisPool pool = null;


    public static ShardedJedisPool getPool() {
        if (pool == null) {
            JedisPoolConfig config = new JedisPoolConfig();
            //控制一个pool可分配多少个jedis实例，通过pool.getResource()来获取；
            //如果赋值为-1，则表示不限制；如果pool已经分配了maxActive个jedis实例，则此时pool的状态为exhausted(耗尽)。
          // config.setMaxActive(500);
            //控制一个pool最多有多少个状态为idle(空闲的)的jedis实例。
            config.setMaxIdle(5);
            //表示当borrow(引入)一个jedis实例时，最大的等待时间，如果超过等待时间，则直接抛出JedisConnectionException；
           // config.setMaxWait(1000 * 100);
            //在borrow一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的；
            config.setTestOnBorrow(true);


            pool = new ShardedJedisPool(config, getJedisShardInfo());
        }
        return pool;
    }


    public static void set(String key, String value) {

        ShardedJedis jedis = null;
        ShardedJedisPool pool = null;
        try {
            pool = getPool();
            jedis = pool.getResource();
            jedis.set(key, value);
        } catch (Exception e) {
            //释放redis对象
            pool.returnBrokenResource(jedis);
            e.printStackTrace();
        } finally {
            //返还到连接池
            close(pool,jedis);
        }
    }

    /**
     * 获取数据
     *
     * @param key
     * @return
     */
    @SuppressWarnings("deprecation")
    public static String get(String key){
        String value = null;

        ShardedJedisPool pool = null;
        ShardedJedis jedis = null;
        try {
            pool = getPool();
            jedis = pool.getResource();
            value = jedis.get(key);
        } catch (Exception e) {
            //释放redis对象
            pool.returnBrokenResource(jedis);
            e.printStackTrace();
        } finally {
            //返还到连接池
            close(pool,jedis);
        }

        return value;
    }

    public static void close(ShardedJedisPool pool,ShardedJedis jedis) {
        try {
            if (jedis != null)
            {
                pool.returnResource(jedis);
            }
        } catch (Exception e) {

            jedis.disconnect();

        }
    }

    public static List<JedisShardInfo> getJedisShardInfo() {
        String address = ConnectionDB.address;
        String port = ConnectionDB.port;
        String[] addresses=address.split(";");
        String[] ports=port.split(";");
        List<JedisShardInfo> jedisShardInfos= new ArrayList<>();
        for (int i=0;i<addresses.length;i++) {
            jedisShardInfos.add(new JedisShardInfo(addresses[i],Integer.valueOf(ports[i])));
        }



        return jedisShardInfos;

    }


}
