package com.fangxing.javalearning.dbunit;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import javax.inject.Named;

@Repository
public interface IUserDao {

    String addUser(@Param("name") String name);

}
