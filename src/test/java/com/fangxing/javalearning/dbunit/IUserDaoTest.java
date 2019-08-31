package com.fangxing.javalearning.dbunit;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class, DbUnitTestExecutionListener.class})
public class IUserDaoTest {

    @Autowired
    IUserDao userDao;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    @DatabaseSetup({"classpath:table/user.xml"})
    public void testAddUser() {
        UserVO user = userDao.queryUser("suxiaolong666");
        System.out.println(user.getUserClass());
    }


    @After
    public void tearDown() throws Exception {
    }
}