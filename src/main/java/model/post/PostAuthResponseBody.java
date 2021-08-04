package model.post;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PostAuthResponseBody {
    private Boolean success;
    private String token;
    private String refreshToken;
}
