package black.lyg.blog.modelEntity;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TypeTops implements Comparable<TypeTops> {
    private Integer typeId;
    private String name;
    private Integer blogNumber;

    @Override
    public int compareTo(TypeTops o) {
        return o.blogNumber - this.blogNumber;
    }
}
