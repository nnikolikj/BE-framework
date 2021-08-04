package model.post;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class TestStepsRequestBody {
    private Integer id;
    private String value;
}
