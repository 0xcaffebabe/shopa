package wang.ismy.shopa.service.client;

import org.springframework.cloud.openfeign.FeignClient;
import wang.ismy.shopa.api.WeixinService;

/**
 * @author MY
 * @date 2020/3/26 15:03
 */
@FeignClient("app-weixin")
public interface WeixinServiceClient extends WeixinService { }
