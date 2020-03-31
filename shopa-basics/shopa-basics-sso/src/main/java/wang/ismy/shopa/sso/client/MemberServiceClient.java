package wang.ismy.shopa.sso.client;

import org.springframework.cloud.openfeign.FeignClient;
import wang.ismy.shopa.api.MemberService;

/**
 * @author MY
 * @date 2020/3/31 11:08
 */
@FeignClient("app-member")
public interface MemberServiceClient extends MemberService { }
