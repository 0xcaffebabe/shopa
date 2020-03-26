package wang.ismy.shopa.api;

import org.springframework.web.bind.annotation.GetMapping;
import wang.ismy.shopa.entity.AppEntity;

/**
 * @author MY
 * @date 2020/3/26 14:39
 */
public interface WeixinService {

    @GetMapping("/run")
    AppEntity run();
}
