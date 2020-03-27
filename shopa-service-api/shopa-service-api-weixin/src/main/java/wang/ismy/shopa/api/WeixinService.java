package wang.ismy.shopa.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import wang.ismy.shopa.common.BaseResponse;
import wang.ismy.shopa.entity.AppEntity;

/**
 * @author MY
 * @date 2020/3/26 14:39
 */
@Api(tags = "微信服务接口")
public interface WeixinService {

    @GetMapping("/run")
    @ApiOperation("获取系统信息")
    BaseResponse<AppEntity> run();
}
