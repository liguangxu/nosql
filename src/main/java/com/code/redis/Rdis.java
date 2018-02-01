package com.code.redis;

import java.util.Set;

import org.junit.Test;


import redis.clients.jedis.Jedis;

public class Rdis {

//	@Test	
	public void testPing() {
		Jedis jedis = new Jedis("192.168.168.131",6379);
		System.out.println(jedis.ping());
	}
	

	public void testApi() {
	
			Jedis jedis = new Jedis("192.168.168.131",6379);
			
			jedis.set("k1","v1");
			jedis.set("k2","v2");
			jedis.set("k3","v3");
			
			
			System.out.println(jedis.get("k3"));
			
			Set<String> sets = jedis.keys("*");
			System.out.println(sets.size());

	}
	
	@Test
	public void TestMS() {
		Jedis jedis_M = new Jedis("192.168.168.131",6379);
		Jedis jedis_S = new Jedis("192.168.168.131",6380);
		
		jedis_S.slaveof("192.168.168.131",6379);
		
		jedis_M.set("class","1122V2");
		
		String result = jedis_S.get("class");
		System.out.println(result);
	}
	
	
	
}
