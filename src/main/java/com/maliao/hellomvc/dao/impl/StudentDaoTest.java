package com.maliao.hellomvc.dao.impl;

import com.maliao.hellomvc.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import javax.annotation.Resource;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by minjj on 2016/9/16 0016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:conf/spring-db.xml"})
public class StudentDaoTest {

    private StudentDao studentDao;
@Autowired
    public void setStudentDao(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    @org.junit.Test
    public void testFindUserByNameJdbc() throws Exception {

        User user = studentDao.findUserByNameJdbc("jack");

        System.out.println(user.toString());


    }


    /**
     * 插入user记录
     * @throws Exception
     */
    @Test
    public void testSaveUser() throws Exception {
        User user = new User();
        user.setGender(1);
        user.setUserName("nick");
//        Assert.isNull(user, "user is not null");
        studentDao.saveUser(user);
        System.out.println("hahahah");
    }


    @Test
    public void testSave() throws Exception {
        User user = new User();
        user.setGender(1);
        user.setUserName("nick12");
//        Assert.isNull(user, "user is not null");
        studentDao.saveUser(user);
        System.out.println("h1ahahah");
    }

    @Test
    public void testFindUser() throws Exception {

    }

    @Test
    public void testFindAllUser() throws Exception {
        List list = studentDao.findAllUser();
        System.out.println(list);
    }

    @Test
    public void uodate() throws Exception {
        User user = new User();
        user.setGender(1);
        user.setUserName("nick22");
        user.setId(2);
//        Assert.isNull(user, "user is not null");
        studentDao.updateUser(user);
        System.out.println("h1ahahah");
    }

    @Test
    public void delete() throws Exception {

//        Assert.isNull(user, "user is not null");
        studentDao.delete(2);
        System.out.println("h1ahahah");
    }
}