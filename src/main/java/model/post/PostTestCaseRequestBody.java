package model.post;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class PostTestCaseRequestBody {
    private String title;
    private String description;
    private String expected_result;
    private List<TestStepsRequestBody> test_steps;
    private boolean automated;
}
