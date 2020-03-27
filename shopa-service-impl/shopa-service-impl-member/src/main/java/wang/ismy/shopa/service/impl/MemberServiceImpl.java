package wang.ismy.shopa.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import wang.ismy.shopa.api.MemberService;
import wang.ismy.shopa.common.BaseResponse;
import wang.ismy.shopa.entity.AppEntity;
import wang.ismy.shopa.service.client.WxServiceClient;

/**
 * @author MY
 * @date 2020/3/26 15:01
 */
@RestController
public class MemberServiceImpl implements MemberService {

    @Autowired
    WxServiceClient wxServiceClient;

    @Override
    public BaseResponse<AppEntity> member() {
        return wxServiceClient.run();
    }
}
