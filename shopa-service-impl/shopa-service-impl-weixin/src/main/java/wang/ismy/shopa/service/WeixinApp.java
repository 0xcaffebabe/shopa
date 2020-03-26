package wang.ismy.shopa.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author MY
 * @date 2020/3/26 14:53
 */
@SpringBootApplication
@EnableEurekaClient
public class WeixinApp {
    public static void main(String[] args) {
        SpringApplication.run(WeixinApp.class,args);
    }
}
