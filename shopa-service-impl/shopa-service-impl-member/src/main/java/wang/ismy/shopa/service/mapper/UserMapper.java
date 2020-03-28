package wang.ismy.shopa.service.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import wang.ismy.shopa.entity.UserEntity;

/**
 * @author MY
 * @date 2020/3/28 8:38
 */
public interface UserMapper {

    @Insert("INSERT INTO `tb_user` VALUES (null,#{mobile}, #{email}, #{password}, #{userName}, null, null, null, '1', null, null, null);")
    int register(UserEntity userEntity);

    @Select("SELECT * FROM tb_user WHERE MOBILE=#{mobile};")
    UserEntity existMobile(@Param("mobile") String mobile);

    @Select("SELECT * FROM tb_user WHERE EMAIL=#{email};")
    UserEntity existEmail(@Param("email") String email);
}
