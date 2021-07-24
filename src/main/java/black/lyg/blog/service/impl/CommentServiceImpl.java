package black.lyg.blog.service.impl;

import black.lyg.blog.mapper.CommentMapper;
import black.lyg.blog.po.Comment;
import black.lyg.blog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.*;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Override
    public List<Comment> findCommentsByBlogId(Integer blogId) {
        Example example = new Example(Comment.class);
        example.createCriteria().andEqualTo("blogId", blogId);
        example.setOrderByClause("comment_id desc");
        List<Comment> comments = commentMapper.selectByExample(example);
        return comments;
    }

    @Transactional
    @Override
    public void saveComment(Comment comment) {
        comment.setCreateTime(new Date());
        int insert = commentMapper.insert(comment);
        if (insert == 1) {
        }
    }
}
