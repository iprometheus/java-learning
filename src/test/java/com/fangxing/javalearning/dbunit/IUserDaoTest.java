package com.fangxing.javalearning.dbunit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:database.xml"})
@Transactional
public class IUserDaoTest {

    @Autowired
    IUserDao userDao;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void testAddUser() {
        userDao.addUser("abc");
    }


    @After
    public void tearDown() throws Exception {
    }
}