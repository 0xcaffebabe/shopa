package wang.ismy.shopa.service;

import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpMessageHandler;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutTextMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import wang.ismy.shopa.service.client.MemberServiceClient;

import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @author MY
 * @date 2020/3/27 16:59
 */
@Component
public class WxMsgHandler implements WxMpMessageHandler {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private MemberServiceClient memberServiceClient;

    @Override
    public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage, Map<String, Object> context, WxMpService wxMpService, WxSessionManager sessionManager) throws WxErrorException {
        // 判断消息是否符合邮箱正则，是就返回验证码
        if (wxMessage.getContent().matches("^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$")) {
            if (memberServiceClient.existEmail(wxMessage.getContent()).getData()){
                return WxMpXmlOutMessage.TEXT()
                        .content("该邮箱已被注册")
                        .fromUser(wxMessage.getToUser())
                        .toUser(wxMessage.getFromUser())
                        .build();
            }
            String code = createCode();
            redisTemplate.opsForValue().set("WEIXIN_REGISTER_" + wxMessage.getContent(), code,1800, TimeUnit.SECONDS);
            return WxMpXmlOutMessage.TEXT()
                    .content("您的验证码:" + code)
                    .fromUser(wxMessage.getToUser())
                    .toUser(wxMessage.getFromUser())
                    .build();
        } else {
            return WxMpXmlOutMessage.TEXT()
                    .content("不知道回复什么呢")
                    .fromUser(wxMessage.getToUser())
                    .toUser(wxMessage.getFromUser())
                    .build();
        }
    }

    private String createCode() {
        Random random = new Random();
        return random.nextInt(10) + "" + random.nextInt(10) + random.nextInt(10) + random.nextInt(10);
    }
}
