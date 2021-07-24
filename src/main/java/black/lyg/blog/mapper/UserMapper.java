package black.lyg.blog.mapper;


import black.lyg.blog.po.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;


public interface UserMapper extends Mapper<User> {

    @Select("select * from user where username = #{username} and password = #{password}")
    User selectUserNameByNameAndPassWord(String username,String password);


    @Select("select * from user where user_id = #{id}")
    @ResultType(User.class)
    User selectUserByUserId(@Param(value = "id") String id);

    @Override
    int insert(User user);
}
