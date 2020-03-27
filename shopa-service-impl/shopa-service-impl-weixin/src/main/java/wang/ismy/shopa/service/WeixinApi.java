package wang.ismy.shopa.service;

import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import me.chanjar.weixin.mp.config.WxMpConfigStorage;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author MY
 * @date 2020/3/27 16:45
 */
@RestController
@Slf4j
public class WeixinApi {

    @Autowired
    private WxMpService wxMpService;

    @Autowired
    private WxMpConfigStorage wxMpConfigStorage;

    @Autowired
    private WxMsgHandler wxMsgHandler;

    /**
     * 微信服务器消息总入口
     *
     * @return
     */
    @RequestMapping("")
    public String auth(@RequestParam String signature,
                       @RequestParam String timestamp,
                       @RequestParam String nonce,
                       @RequestParam(required = false) String echostr,
                       HttpServletRequest request,
                       HttpServletResponse response) {
        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);

        if (!wxMpService.checkSignature(timestamp, nonce, signature)) {
            // 消息签名不正确，说明不是公众平台发过来的消息
            return "非法请求";
        }
        if (StringUtils.isNotBlank(echostr)) {
            // 说明是一个仅仅用来验证的请求，回显echostr
            return echostr;
        }

        String encryptType = StringUtils.isBlank(request.getParameter("encrypt_type")) ?
                "raw" :
                request.getParameter("encrypt_type");

        WxMpXmlMessage inMessage;
        try {
            if ("raw".equals(encryptType)) {
                // 明文传输的消息
                inMessage = WxMpXmlMessage.fromXml(request.getInputStream());
            } else if ("aes".equals(encryptType)) {
                // 是aes加密的消息
                String msgSignature = request.getParameter("msg_signature");
                inMessage = WxMpXmlMessage.fromEncryptedXml(request.getInputStream(), wxMpConfigStorage, timestamp, nonce, msgSignature);
            } else {
                return "不可识别的加密类型";
            }

            WxMpXmlOutMessage outMessage = wxMsgHandler.handle(inMessage, null, wxMpService, null);
            return outMessage.toXml();
        } catch (Exception e) {
            log.error("微信服务消息处理发生错误:{}", e.getMessage());
        }
        return "";
    }

}
