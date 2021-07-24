package black.lyg.blog.po;

import lombok.*;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Comment implements Comparable<Comment> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer commentId;
    private Integer blogId;
    private String nickName;
    private String email;
    private String content;
    private String avatar;
    private Date createTime;
    private Integer parentCommentId;

    @Override
    public int compareTo(Comment o) {
        return this.commentId - o.commentId;
    }
}
