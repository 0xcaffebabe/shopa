package wang.ismy.shopa.service.utils;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author MY
 * @date 2020/3/29 10:57
 */
@Component
public class GenerateToken {
    @Autowired
    private StringRedisTemplate redisTemplate;

    /**
     * 生成令牌
     *
     * @param prefix
     *            令牌key前缀
     * @param redisValue
     *            redis存放的值
     * @return 返回token
     */
    public String createToken(String keyPrefix, String redisValue) {
        return createToken(keyPrefix, redisValue, null);
    }

    /**
     * 生成令牌
     *
     * @param prefix
     *            令牌key前缀
     * @param redisValue
     *            redis存放的值
     * @param time
     *            有效期
     * @return 返回token
     */
    public String createToken(String keyPrefix, String redisValue, Long time) {
        if (StringUtils.isEmpty(redisValue)) {
            new Exception("redisValue Not nul");
        }
        String token = keyPrefix + UUID.randomUUID().toString().replace("-", "");
        redisTemplate.opsForValue().set(token,redisValue,time, TimeUnit.SECONDS);
        return token;
    }

    /**
     * 根据token获取redis中的value值
     *
     * @param token
     * @return
     */
    public String getToken(String token) {
        if (StringUtils.isEmpty(token)) {
            return null;
        }
        String value = redisTemplate.opsForValue().get(token);
        return value;
    }

    /**
     * 移除token
     *
     * @param token
     * @return
     */
    public Boolean removeToken(String token) {
        if (StringUtils.isEmpty(token)) {
            return null;
        }
        return redisTemplate.delete(token);

    }

}
