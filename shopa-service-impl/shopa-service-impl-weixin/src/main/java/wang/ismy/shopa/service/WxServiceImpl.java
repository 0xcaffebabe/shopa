package wang.ismy.shopa.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RestController;
import wang.ismy.shopa.api.WeixinService;
import wang.ismy.shopa.common.BaseApiService;
import wang.ismy.shopa.common.BaseResponse;
import wang.ismy.shopa.entity.AppEntity;

/**
 * @author MY
 * @date 2020/3/26 14:49
 */
@RestController
public class WxServiceImpl extends BaseApiService implements WeixinService {

    @Value("${system.version:unknown}")
    private String systemVersion;

    @Override
    public BaseResponse<AppEntity> run() {
        return setResultSuccess(new AppEntity("微服务项目",systemVersion));
    }
}
