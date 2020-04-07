package wang.ismy.shopa.service.impl;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RestController;
import wang.ismy.shopa.common.BaseApiService;
import wang.ismy.shopa.common.BaseResponse;
import wang.ismy.shopa.service.api.PayService;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author MY
 * @date 2020/4/6 20:26
 */
@RestController
public class PayServiceImpl extends BaseApiService implements PayService {

    private Map<String,Map<String,Object>> data = new ConcurrentHashMap<>();

    @Override
    public BaseResponse<JSONObject> cratePayToken(Long amount, String orderId, String userId) {
        if (StringUtils.isEmpty(orderId)) {
            return setResultError("订单号码不能为空!");
        }

        if (amount == null) {
            return setResultError("金额不能为空!");
        }
        if (userId == null) {
            return setResultError("userId不能为空!");
        }
        String token = UUID.randomUUID().toString().replaceAll("-","");
        data.put(token,Map.of("amount",amount,"order",orderId,"user",userId));
        JSONObject dataResult = new JSONObject();
        dataResult.put("token", token);
        return setResultSuccess(dataResult);
    }
}
