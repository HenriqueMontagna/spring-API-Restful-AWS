package com.montagna.apirestful.aws.service.util;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class HashUtilTests {
	
	@Test
	public void getSecureHashTest() {
		String hash = HashUtil.getSecureHash("123");
		System.out.println(hash);
		assertThat(hash.length()).isEqualTo(64);
	}
	
}
