package black.lyg.blog.service;

import black.lyg.blog.po.Blog;
import black.lyg.blog.po.User;
import com.github.pagehelper.Page;

import java.util.List;
import java.util.Map;

public interface BlogService {

    Blog findBlogByBlogId(Integer id);

    Blog addBlog(Blog blog);

    List<Blog> findAllBlogByPage();

    Integer deleteBlog(Integer id);

    Page<Blog> selectBlogByKeyWords(String title, String typeId, String recommend, String username);

    String findTagsByBlogId(Integer blogId);

    List<Blog> findTheLastBlog(int i);

    Page<Blog> findBlogByKeyWords(String key);

    List<Blog> findBlogByTypeId(Integer typeId);

    List<Blog> findBlogByTagId(Integer tagId);

    List<Blog> findBlogByUserId(Integer userId);

    Map<String, List<Blog>> findArchiveBlog();

    Integer findBlogCount();

    void addBlogViews(Blog blog);

    User findUserByBlogId(String id);
}
