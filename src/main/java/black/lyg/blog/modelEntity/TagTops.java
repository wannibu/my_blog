package black.lyg.blog.modelEntity;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TagTops implements Comparable<TagTops> {

    private Integer tagId;
    private String name;
    private Integer BlogNumber;

    @Override
    public int compareTo(TagTops o) {
        return o.BlogNumber - this.BlogNumber;
    }
}
