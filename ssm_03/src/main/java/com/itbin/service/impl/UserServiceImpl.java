package com.itbin.service.impl;


import com.itbin.domain.Condition;
import com.itbin.domain.PageBean;
import com.itbin.domain.User;
import com.itbin.mapper.UserMapper;
import com.itbin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper mapper;
    @Override
    public User login(User user) {
        return mapper.login(user) ;
    }

    @Override
    public PageBean<User> findByPage(PageBean<User> pb) {
        PageBean<User> findPb = new PageBean<>();
//          总记录数
        Integer totalCount = mapper.findTotal();
        findPb.setTotalCount(totalCount);
//        总页数
        Integer totalPage = (int) Math.ceil(totalCount * 1.0 / pb.getRows());
        findPb.setTotalPage(totalPage);
//        每页数据
        int start = (pb.getCurrentPage()-1)*pb.getRows();
        List<User> list = mapper.findByPage(start,pb.getRows());
        findPb.setList(list);
//
        findPb.setRows(pb.getRows());
        findPb.setCurrentPage(pb.getCurrentPage());
return findPb;


    }

    @Override
    public PageBean<User> findUserByPageAndCondition(PageBean<User> pb, Condition condition) {
        PageBean<User> findPb = new PageBean<>();
//          总记录数
        Integer totalCount = mapper.findTotalCondition(condition);
        findPb.setTotalCount(totalCount);
//        总页数
        Integer totalPage = (int) Math.ceil(totalCount * 1.0 / pb.getRows());
        findPb.setTotalPage(totalPage);
//        每页数据
        int start = (pb.getCurrentPage()-1)*pb.getRows();
        condition.setStart(start);
        condition.setRows(pb.getRows());
        List<User> list = mapper.findByPageCondition(condition);
        findPb.setList(list);
//
        findPb.setRows(pb.getRows());
        findPb.setCurrentPage(pb.getCurrentPage());
        return findPb;

    }

    @Override
    public void saveUser(User user) {
        mapper.saveUser(user);
    }

    @Override
    public void deleteUser(String id) {
        mapper.deleteUser(Integer.parseInt(id));
    }

    @Override
    public void delSelect(String[] uid) {
        if(uid!=null&&uid.length>0){
            for (int i = 0; i < uid.length; i++) {
                mapper.deleteUser(Integer.parseInt(uid[i]));
            }
        }
    }

    @Override
    public User findById(Integer id) {
        return mapper.findById(id);
    }

    @Override
    public void update(User user) {
        mapper.update(user);
    }

}
