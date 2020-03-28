package wang.ismy.shopa.service;

import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import com.spring4all.swagger.EnableSwagger2Doc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author MY
 * @date 2020/3/26 14:53
 */
@SpringBootApplication
@EnableEurekaClient
@EnableSwagger2Doc
@EnableApolloConfig
@EnableFeignClients
public class WxApp {
    public static void main(String[] args) {
        SpringApplication.run(WxApp.class,args);
    }
}
