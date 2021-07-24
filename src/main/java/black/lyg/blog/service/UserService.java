package black.lyg.blog.service;

import black.lyg.blog.po.User;

public interface UserService {

    User checkUser(String username,String password);

    User checkUserByName(String username);

    int addUser(User user);
}
