package wang.ismy.shopa.service.client;

import org.springframework.cloud.openfeign.FeignClient;
import wang.ismy.shopa.api.WxCodeService;

/**
 * @author MY
 * @date 2020/3/28 8:33
 */
@FeignClient("app-weixin")
public interface WxCodeServiceClient extends WxCodeService {
}
