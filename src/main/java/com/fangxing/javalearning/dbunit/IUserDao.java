package com.fangxing.javalearning.dbunit;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserDao {

    UserVO queryUser(@Param("name") String name);

}
