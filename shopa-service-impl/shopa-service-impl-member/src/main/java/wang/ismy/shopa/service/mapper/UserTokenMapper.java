package wang.ismy.shopa.service.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import wang.ismy.shopa.entity.UserTokenDo;

/**
 * @author MY
 * @date 2020/3/29 10:55
 */
public interface UserTokenMapper {

    @Select("SELECT id as id ,token as token ,login_type as LoginType, device_infor as deviceInfor ,is_availability as isAvailability,user_id as userId"
            + "" + ""
            + " FROM tb_user_token WHERE user_id=#{userId} AND login_type=#{loginType} and is_availability ='0'; ")
    UserTokenDo selectByUserIdAndLoginType(@Param("userId") Long userId, @Param("loginType") String loginType);

    @Update("    update tb_user_token set is_availability ='1'  where user_id=#{userId} and login_type =#{loginType} ")
    int updateTokenAvailability(@Param("userId") Long userId, @Param("loginType") String loginType);

    // INSERT INTO `meite_user_token` VALUES ('2', '1', 'PC', '苹果7p', '1', '1');

    @Insert("    INSERT INTO `tb_user_token`(token,login_type,device_infor,is_availability,user_id) VALUES (#{token},#{loginType}, #{deviceInfor}, 0, #{userId}); ")
    int insertUserToken(UserTokenDo userTokenDo);
}
