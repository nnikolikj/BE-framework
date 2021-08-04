package model.put;

import lombok.Builder;
import lombok.Data;
import model.post.PostTestCaseRequestBody;
import model.post.TestStepsRequestBody;

import java.util.List;

@Data
@Builder
public
class PutTestCaseRequestBody {
    private String title;
    private String description;
    private String expected_result;
    private List<TestStepsRequestBody> test_steps;
    private boolean automated;
    private Integer candidate_scenario_id;
    private Integer testcaseId;
}
