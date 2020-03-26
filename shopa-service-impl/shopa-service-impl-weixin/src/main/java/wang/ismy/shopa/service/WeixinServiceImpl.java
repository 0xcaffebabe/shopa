package wang.ismy.shopa.service;

import org.springframework.web.bind.annotation.RestController;
import wang.ismy.shopa.api.WeixinService;
import wang.ismy.shopa.entity.AppEntity;

/**
 * @author MY
 * @date 2020/3/26 14:49
 */
@RestController
public class WeixinServiceImpl implements WeixinService {

    @Override
    public AppEntity run() {
        return new AppEntity();
    }
}
