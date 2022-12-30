package com.kitex.kitex;

import com.kitex.kitex.config.RsaKeyProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.ServletComponentScan;


@EnableConfigurationProperties(RsaKeyProperties.class)
@ServletComponentScan
@SpringBootApplication
public class KitexApplication {

	public static void main(String[] args) {
		SpringApplication.run(KitexApplication.class, args);
	}

}
