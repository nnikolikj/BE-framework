package model.get;

import lombok.*;

import java.util.*;

@Data
@NoArgsConstructor
public class GetCasesResponseBody {
    private Integer id;
    private Integer candidate_id;
    private String title;
    private String expected_result;
    private String description;
    private Boolean automated;
    private Integer candidate_scenario_id;
    private List<TestStepsResponseBody> result;
}
