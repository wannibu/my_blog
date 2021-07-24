package black.lyg.blog.service;

import black.lyg.blog.po.Blog;
import com.github.pagehelper.Page;

import java.util.List;
import java.util.Map;

public interface BlogService {

    Blog findBlogByBlogId(Integer id);

    Blog addBlog(Blog blog);

    List<Blog> findAllBlogByPage();

    Integer deleteBlog(Integer id);

    Page<Blog> selectBlogByKeyWords(String title, String typeId, String recommend);

    String findTagsByBlogId(Integer blogId);

    List<Blog> findTheLastBlog(int i);

    Page<Blog> findBlogByKeyWords(String key);

    List<Blog> findBlogByTypeId(Integer typeId);

    List<Blog> findBlogByTagId(Integer tagId);

    Map<String, List<Blog>> findArchiveBlog();

    Integer findBlogCount();

    void addBlogViews(Blog blog);
}
