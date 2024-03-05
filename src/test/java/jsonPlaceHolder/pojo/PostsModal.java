package jsonPlaceHolder.pojo;

import lombok.*;

@Getter
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor

public class PostsModal {
    int userId;
    int id;
    String title;
    String body;
}
