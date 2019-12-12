package cn.yanrui.shop.repository;

import cn.yanrui.shop.entity.User;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository {
    @Select("select * from user where phone = #{phone}")
    User getUser(String phone);

}
