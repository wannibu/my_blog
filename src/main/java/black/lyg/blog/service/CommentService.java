package black.lyg.blog.service;

import black.lyg.blog.po.Comment;

import java.util.List;

public interface CommentService {

    List<Comment> findCommentsByBlogId(Integer blogId);

    void saveComment(Comment comment);
}
