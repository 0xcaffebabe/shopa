package wang.ismy.shopa.service.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;
import wang.ismy.shopa.api.WxCodeService;
import wang.ismy.shopa.common.BaseApiService;
import wang.ismy.shopa.common.BaseResponse;

/**
 * @author MY
 * @date 2020/3/27 20:48
 */
@RestController
public class WxCodeServiceImpl extends BaseApiService implements WxCodeService {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public BaseResponse<Boolean> verify(String email, String code) {

        if (StringUtils.isEmpty(email)){
            return setResultError("邮箱不得为空");
        }
        if (StringUtils.isEmpty(code)){
            return setResultError("验证码不得为空");
        }
        String result = redisTemplate.opsForValue().get("WEIXIN_REGISTER_"+email);
        if (code.equals(result)){
            return setResultSuccess(true);
        }else{
            return setResultSuccess(false);
        }
    }
}
