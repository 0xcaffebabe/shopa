package wang.ismy.shopa.service;

import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.mp.api.WxMpMessageRouter;
import me.chanjar.weixin.mp.api.WxMpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author MY
 * @date 2020/3/27 16:57
 */
@Configuration
public class WxConfig {

    @Autowired
    private WxMpService wxMpService;

    @Autowired
    private WxMsgHandler wxMsgHandler;

    @Bean
    public WxMpMessageRouter wxMpMessageRouter(){
        WxMpMessageRouter router = new WxMpMessageRouter(wxMpService);
        router
                .rule()
                .msgType(WxConsts.XmlMsgType.TEXT)
                .handler(wxMsgHandler)
                .end()
        ;
        return router;
    }
}
