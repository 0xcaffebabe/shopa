package wang.ismy.shopa.web.pay;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author MY
 * @date 2020/3/31 10:26
 */
@SpringBootApplication
@EnableEurekaClient
public class PayPortalApp {
    public static void main(String[] args) {
        SpringApplication.run(PayPortalApp.class, args);
    }
}
