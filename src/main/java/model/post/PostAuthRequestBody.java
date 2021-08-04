package model.post;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PostAuthRequestBody {
    private String email;
    private String password;
}
