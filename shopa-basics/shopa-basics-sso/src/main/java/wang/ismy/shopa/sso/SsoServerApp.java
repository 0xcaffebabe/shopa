package wang.ismy.shopa.sso;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author xuxueli 2018-03-22 23:41:47
 */
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
public class SsoServerApp {

	public static void main(String[] args) {
        SpringApplication.run(SsoServerApp.class, args);
	}

}