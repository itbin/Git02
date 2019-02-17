package com.itbin.service;

import com.itbin.domain.Condition;
import com.itbin.domain.PageBean;
import com.itbin.domain.User;


public interface UserService {

    User login(User user);

    PageBean<User> findByPage(PageBean<User> pb);

    PageBean<User> findUserByPageAndCondition(PageBean<User> pb, Condition condition);

    void saveUser(User user);

    void deleteUser(String id);

    void delSelect(String[] id);

    User findById(Integer id);

    void update(User user);
}
