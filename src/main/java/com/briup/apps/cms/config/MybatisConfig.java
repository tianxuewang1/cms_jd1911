package com.briup.apps.cms.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.briup.apps.cms.bean.userExample;

@Configuration
@MapperScan(basePackages = {"com.briup.apps.cms.dao","com.briup.apps.cms.bean"})
//sheng cheng 实现类
public class MybatisConfig {
	
	@Bean
	//指明为bean为spring容器中，方法名为容器中bean名字
	public userExample userExample() {
		return new userExample();
	}
}
