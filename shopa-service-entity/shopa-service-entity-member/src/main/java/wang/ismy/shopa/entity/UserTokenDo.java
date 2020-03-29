package wang.ismy.shopa.entity;

import lombok.Data;

import java.util.Date;

/**
 * @author MY
 * @date 2020/3/29 10:55
 */
@Data
public class UserTokenDo {
    /**
     * id
     */
    private Long id;
    /**
     * 用户token
     */
    private String token;
    /**
     * 登陆类型
     */
    private String loginType;

    /**
     * 设备信息
     */
    private String deviceInfor;
    /**
     * 用户userId
     */
    private Long userId;

    /**
     * 注册时间
     */
    private Date createTime;
    /**
     * 修改时间
     *
     */
    private Date updateTime;

}
