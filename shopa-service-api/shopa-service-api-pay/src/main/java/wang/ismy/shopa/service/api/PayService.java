package wang.ismy.shopa.service.api;

import com.alibaba.fastjson.JSONObject;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import wang.ismy.shopa.common.BaseResponse;

/**
 * @author MY
 * @date 2020/4/6 20:14
 */
public interface PayService {
    @GetMapping("/cratePayToken")
    BaseResponse<JSONObject> cratePayToken(@RequestParam Long amount,@RequestParam String orderId,@RequestParam String userId);
}
