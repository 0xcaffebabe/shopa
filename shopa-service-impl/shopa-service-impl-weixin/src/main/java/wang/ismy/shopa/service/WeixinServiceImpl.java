package wang.ismy.shopa.service;

import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RestController;
import wang.ismy.shopa.api.WeixinService;
import wang.ismy.shopa.entity.AppEntity;

/**
 * @author MY
 * @date 2020/3/26 14:49
 */
@RestController
public class WeixinServiceImpl implements WeixinService {

    @Value("${system.version:unknown}")
    private String systemVersion;

    @Autowired
    private WxMpService wxMpService;

    @Override
    public AppEntity run() {
        return new AppEntity("微服务项目",systemVersion);
    }
}
