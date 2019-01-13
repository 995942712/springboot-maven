package com.ming.shiro;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ShiroApplicationTests {

	@Test
	public void contextLoads() {
		System.out.println("========================================");
		String password = new SimpleHash("MD5", "123456".toCharArray(), ByteSource.Util.bytes("admin")).toHex();
		System.out.println(password);
		System.out.println("========================================");
		password = new SimpleHash("MD5", "123456".toCharArray(), ByteSource.Util.bytes("admin"), 1024).toString();
		System.out.println(password);
		System.out.println("========================================");
		password = DigestUtils.md5Hex("123456");
		System.out.println(password);
		System.out.println("========================================");
	}

}