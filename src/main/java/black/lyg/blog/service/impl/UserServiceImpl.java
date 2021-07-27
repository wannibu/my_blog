package black.lyg.blog.service.impl;

import black.lyg.blog.mapper.UserMapper;
import black.lyg.blog.po.User;
import black.lyg.blog.service.UserService;
import black.lyg.blog.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User checkUser(String username, String password) {
        String pwd = MD5Util.md5(password);
        return userMapper.selectUserNameByNameAndPassWord(username,pwd);
    }

    @Override
    public int addUser(User user) {
        return userMapper.insert(user);
    }

    @Override
    public User checkUserByName(String username) {
        return userMapper.selectUserByUsername(username);
    }

    @Override
    public void updateInfo(User user) {
        userMapper.updateByPrimaryKey(user);
    }
}
