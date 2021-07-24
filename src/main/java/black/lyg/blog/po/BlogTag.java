package black.lyg.blog.po;

import lombok.*;

import javax.persistence.Id;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BlogTag {
    @Id
    private Integer blogTagId;
    private Integer blogId;
    private Integer TagId;
}
