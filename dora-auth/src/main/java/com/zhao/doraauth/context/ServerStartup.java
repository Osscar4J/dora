package com.zhao.doraauth.context;

import com.zhao.common.utils.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class ServerStartup implements ApplicationRunner {

	@Value("${jwt.refreshExp}")
	private Integer refreshTokenExp;
	@Value("${jwt.tokenExp}")
	private Integer tokenExp;
	@Value("${jwt.secret}")
	private String secret;

	@Override
	public void run(ApplicationArguments args) {
		JwtTokenUtil.initConfig(secret, tokenExp, refreshTokenExp);
	}

}
