package com.itbin.mapper;

import com.itbin.domain.Condition;
import com.itbin.domain.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    User login(User user);

    Integer findTotal();

    List<User> findByPage(@Param("start") int start, @Param("rows") Integer rows);

    Integer findTotalCondition(Condition condition);

    List<User> findByPageCondition(Condition condition);

    void saveUser(User user);

    void deleteUser(int id);

    User findById(Integer id);

    void update(User user);
}
