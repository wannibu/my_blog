package black.lyg.blog.service;

import black.lyg.blog.modelEntity.TagTops;
import black.lyg.blog.po.Tag;
import com.github.pagehelper.Page;

import java.util.List;

public interface TagService {

    Tag saveTag(Tag tag);

    Tag getTagByTagName(String name);

    Tag getTagById(Integer id);

    Page<Tag> getTagByPage();

    int deleteTagById(Integer id);

    Tag updateTag(Tag tag);

    List<Tag> finaAllTags();

    List<Tag> listTag(String ids);

    List<TagTops> findSeveralTopTags(int number);

    List<Tag> findTagsByBlogId(Integer blogId);

    Integer findCount();
}
