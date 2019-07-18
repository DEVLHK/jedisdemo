package jdedisdemo;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.junit.Test;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisTtest {
	@Test
	public void test() {
		Jedis jedis=new Jedis("192.168.3.101",6379);
		jedis.auth("123456");
		jedis.set("k1","v2");
		System.out.println(jedis.get("k1"));
		jedis.close();
	}
	
	@Test
	public void pooltest() {
		GenericObjectPoolConfig poolConfig=new JedisPoolConfig();
		poolConfig.setMaxTotal(100);
		poolConfig.setMaxWaitMillis(200000);
		poolConfig.setMinIdle(5);
		JedisPool pool =new JedisPool(poolConfig,"192.168.3.101",6379);
		Jedis jedis=pool.getResource();
		jedis.auth("123456");
		jedis.set("k1","v3");
		System.out.println(jedis.get("k1"));
		jedis.close();
	}
}
