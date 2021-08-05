package http;

import model.get.GetCasesResponseBody;
import model.post.PostAuthRequestBody;
import model.post.PostAuthResponseBody;
import model.put.PutTestCaseRequestBody;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static util.HostnameConfig.HOSTNAME;

public class HTECClient extends BaseClient {
    public static final String TESTCASES_ENDPOINT = "/testcases";
    public static final String authenticate = "/login";
    public String Token;

    PostAuthRequestBody postAuthRequestBody = PostAuthRequestBody.builder()
            .email("nikola_nikolik@hotmail.com")
            .password("Test123!")
            .build();

    public HTECClient() {
        this.baseUrl = HOSTNAME;
        authenticateOnTheSite(postAuthRequestBody);
        ResponseEntity<PostAuthResponseBody> responseEntity = authenticateOnTheSite(postAuthRequestBody);
        Token = responseEntity.getBody().getToken();
        this.addHeader("Content-Type", "application/json");
        this.addHeader("Authorization", "Bearer " + Token);
    }

    public ResponseEntity<List<GetCasesResponseBody>> getListOfTestCases() {
        return this.getAllReturnListResponse(TESTCASES_ENDPOINT);
    }

    public ResponseEntity<PostAuthResponseBody> authenticateOnTheSite(PostAuthRequestBody postAuthRequestBody) {
        return this.post(authenticate, postAuthRequestBody, PostAuthResponseBody.class);
    }

    public ResponseEntity<List<GetCasesResponseBody>> postTestCase(String postTestcasePayload) {
        return this.postAndReturnListResponse(TESTCASES_ENDPOINT, postTestcasePayload);
    }

    public ResponseEntity<String> deleteById(Integer id) {
        return this.delete(TESTCASES_ENDPOINT + "/" + id, String.class);
    }

    public ResponseEntity<PutTestCaseRequestBody> getTestCaseById(Integer id) {
        return this.get(TESTCASES_ENDPOINT + "/" + id, PutTestCaseRequestBody.class);
    }

    public ResponseEntity<List<GetCasesResponseBody>> putTestcase(Integer id, String putTestcasePayload) {
        return this.putAndReturnListResponse(TESTCASES_ENDPOINT + "/" + id, putTestcasePayload);
    }
}
