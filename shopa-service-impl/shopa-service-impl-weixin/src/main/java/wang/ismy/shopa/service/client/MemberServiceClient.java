package wang.ismy.shopa.service.client;

import org.springframework.cloud.openfeign.FeignClient;
import wang.ismy.shopa.api.MemberService;

/**
 * @author MY
 * @date 2020/3/28 9:12
 */
@FeignClient("app-member")
public interface MemberServiceClient extends MemberService { }
