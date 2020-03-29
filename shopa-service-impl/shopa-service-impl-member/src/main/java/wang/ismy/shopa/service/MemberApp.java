package wang.ismy.shopa.service;

import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import com.spring4all.swagger.EnableSwagger2Doc;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * @author MY
 * @date 2020/3/26 15:01
 */
@SpringBootApplication
@EnableFeignClients
@EnableEurekaClient
@EnableSwagger2Doc
@EnableApolloConfig
@MapperScan("wang.ismy.shopa.service.mapper")
@ComponentScan(basePackages = "wang.ismy.shopa")
@EnableRedisHttpSession
public class MemberApp {
    public static void main(String[] args) {
        SpringApplication.run(MemberApp.class,args);
    }
}
