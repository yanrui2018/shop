package cn.yanrui.shop.redis;

import org.springframework.stereotype.Component;
import redis.clients.jedis.*;

import java.util.ArrayList;
import java.util.List;
@Component
public class RedisClient {

    private Jedis jedis;//非切片额客户端连接
    private JedisPool jedisPool;//非切片连接池
    private ShardedJedis shardedJedis;//切片额客户端连接
    private ShardedJedisPool shardedJedisPool;//切片连接池

    public RedisClient() {
        initialPool();
//        initialShardedPool();
//        shardedJedis = shardedJedisPool.getResource();
        jedis = jedisPool.getResource();

    }

    public Jedis getJedis(){
        return this.jedis;
    }

    /**
     * 初始化非切片池
     */
    private void initialPool() {
        // 池基本配置
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(20);
        config.setMaxIdle(5);
        config.setMaxWaitMillis(1000l);
        config.setTestOnBorrow(false);

        jedisPool = new JedisPool(config,"127.0.0.1",6379);
    }

    /**
     * 初始化切片池
     */
    private void initialShardedPool() {
        // 池基本配置
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(20);
        config.setMaxIdle(5);
        config.setMaxWaitMillis(1000l);
        config.setTestOnBorrow(false);
        // slave链接
        List<JedisShardInfo> shards = new ArrayList<JedisShardInfo>();
        shards.add(new JedisShardInfo("127.0.0.1", 6379, "master"));

        // 构造池
        shardedJedisPool = new ShardedJedisPool(config, shards);
    }


    private void KeyOperate() {
    }

    private void StringOperate() {
    }


    private void ListOperate() {
    }

    private void SetOperate() {
    }


    private void SortedSetOperate() {
    }

    private void HashOperate() {
    }
}