import com.google.gson.Gson;
import http.HTECClient;
import model.get.GetCasesResponseBody;
import model.post.PostTestCaseRequestBody;
import model.post.TestStepsRequestBody;
import model.put.PutTestCaseRequestBody;
import org.junit.*;
import org.junit.runners.MethodSorters;
import org.springframework.http.ResponseEntity;

import java.util.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestCasesTests {

    private Gson gson = new Gson();
    private HTECClient htecClient;
    private String title,description,expected_result;
    private static List<Integer> listCasesIds = new ArrayList<>();

    @Before
    public void before() {
        htecClient = new HTECClient();
    }

    @Test
    public void test1DeleteAllTestCases() {
        ResponseEntity<List<GetCasesResponseBody>> responseEntity = htecClient.getListOfTestCases();
        responseEntity.getBody().forEach(i -> listCasesIds.add(i.getId()));
        listCasesIds.forEach(id -> {
            ResponseEntity<String> message = htecClient.deleteById(id);
            Assert.assertEquals("{\"success\":\"Test case successfully removed\"}", message.getBody());
        });
        listCasesIds.clear();
    }

    @Test
    public void test2PostValidRequest() {
        title = "Send a valid request";
        description = "The purpose of this test is to validate if we send a valid request we can login on the Page";
        expected_result = "User can successfully login on the page";

        PostTestCaseRequestBody postTestCaseRequestBody = PostTestCaseRequestBody.builder()
            .title(title)
            .description(description)
            .expected_result(expected_result)
            .automated(true)
            .test_steps(Arrays.asList(
                    new TestStepsRequestBody(1, "Go to qa-sandbox.ni.htec.rs/login"),
                    new TestStepsRequestBody(2,"Enter a valid email"),
                    new TestStepsRequestBody(3,"Enter a valid password"),
                    new TestStepsRequestBody(4,"Click on login button")))
            .build();

        String jsonString = gson.toJson(postTestCaseRequestBody);
        ResponseEntity<List<GetCasesResponseBody>> responseEntity = htecClient.postTestCase(jsonString);
        GetCasesResponseBody getCasesResponseBody = responseEntity.getBody().get(0);
        String title = getCasesResponseBody.getTitle();
        listCasesIds.add(getCasesResponseBody.getId());
        Assert.assertEquals(title, postTestCaseRequestBody.getTitle());

        title = "Send a invalid request with empty email and password";
        description = "The purpose of this test is to validate if we send an invalid request with empty email we cannot login on the Page";
        expected_result = "User can't login on the page and an error will be shown (Email is required + Password is required)";

        PostTestCaseRequestBody postTestCaseRequestBody1 = PostTestCaseRequestBody.builder()
                .title(title)
                .description(description)
                .expected_result(expected_result)
                .automated(true)
                .test_steps(Arrays.asList(
                        new TestStepsRequestBody(1, "Go to qa-sandbox.ni.htec.rs/login"),
                        new TestStepsRequestBody(2,"Leave email field as empty"),
                        new TestStepsRequestBody(3,"Leave password field as empty"),
                        new TestStepsRequestBody(4,"Click on login button")))
                .build();

        String jsonString1 = gson.toJson(postTestCaseRequestBody1);
        ResponseEntity<List<GetCasesResponseBody>> responseEntity1 = htecClient.postTestCase(jsonString1);
        GetCasesResponseBody getCasesResponseBody1 = responseEntity1.getBody().get(0);
        listCasesIds.add(getCasesResponseBody1.getId());
        String title1 = getCasesResponseBody1.getTitle();
        Assert.assertEquals(title1, postTestCaseRequestBody1.getTitle());

        title = "Send a invalid request with wrong email";
        description = "The purpose of this test is to validate if we send an invalid request with wrong email we cannot login on the Page";
        expected_result = "User can't login on the page and an error will be shown";

        PostTestCaseRequestBody postTestCaseRequestBody2 = PostTestCaseRequestBody.builder()
                .title(title)
                .description(description)
                .expected_result(expected_result)
                .automated(true)
                .test_steps(Arrays.asList(
                        new TestStepsRequestBody(1, "Go to qa-sandbox.ni.htec.rs/login"),
                        new TestStepsRequestBody(2,"Enter a invalid email (without @)"),
                        new TestStepsRequestBody(3,"Enter a valid password"),
                        new TestStepsRequestBody(4,"Click on login button")))
                .build();

        String jsonString2 = gson.toJson(postTestCaseRequestBody2);
        ResponseEntity<List<GetCasesResponseBody>> responseEntity2 = htecClient.postTestCase(jsonString2);
        GetCasesResponseBody getCasesResponseBody2 = responseEntity2.getBody().get(0);
        listCasesIds.add(getCasesResponseBody2.getId());
        String title2 = getCasesResponseBody2.getTitle();
        Assert.assertEquals(title2, postTestCaseRequestBody2.getTitle());

        title = "Send a invalid request with wrong password";
        description = "The purpose of this test is to validate if we send an invalid request with wrong password we cannot login on the Page";
        expected_result = "User can't login on the page and an error will be shown";

        PostTestCaseRequestBody postTestCaseRequestBody3 = PostTestCaseRequestBody.builder()
                .title(title)
                .description(description)
                .expected_result(expected_result)
                .automated(true)
                .test_steps(Arrays.asList(
                        new TestStepsRequestBody(1, "Go to qa-sandbox.ni.htec.rs/login"),
                        new TestStepsRequestBody(2,"Enter a valid email"),
                        new TestStepsRequestBody(3,"Enter a wrong password"),
                        new TestStepsRequestBody(4,"Click on login button")))
                .build();

        String jsonString3 = gson.toJson(postTestCaseRequestBody3);
        ResponseEntity<List<GetCasesResponseBody>> responseEntity3 = htecClient.postTestCase(jsonString3);
        GetCasesResponseBody getCasesResponseBody3 = responseEntity3.getBody().get(0);
        listCasesIds.add(getCasesResponseBody3.getId());
        String title3 = getCasesResponseBody3.getTitle();
        Assert.assertEquals(title3, postTestCaseRequestBody3.getTitle());

        title = "Send a invalid request with empty password";
        description = "The purpose of this test is to validate if we send an invalid request with empty password we cannot login on the Page";
        expected_result = "User can't login on the page and an error will be shown (Password is required)";

        PostTestCaseRequestBody postTestCaseRequestBody4 = PostTestCaseRequestBody.builder()
                .title(title)
                .description(description)
                .expected_result(expected_result)
                .automated(true)
                .test_steps(Arrays.asList(
                        new TestStepsRequestBody(1, "Go to qa-sandbox.ni.htec.rs/login"),
                        new TestStepsRequestBody(2,"Enter a valid email"),
                        new TestStepsRequestBody(3,"Leave password field as empty"),
                        new TestStepsRequestBody(4,"Click on login button")))
                .build();

        String jsonString4 = gson.toJson(postTestCaseRequestBody4);
        ResponseEntity<List<GetCasesResponseBody>> responseEntity4 = htecClient.postTestCase(jsonString4);
        GetCasesResponseBody getCasesResponseBody4 = responseEntity4.getBody().get(0);
        listCasesIds.add(getCasesResponseBody4.getId());
        String title4 = getCasesResponseBody4.getTitle();
        Assert.assertEquals(title4, postTestCaseRequestBody4.getTitle());
    }

    @Test
    public void test3PutValidRequest() {

        for (Integer id: listCasesIds) {
            PutTestCaseRequestBody putTestCase = htecClient.getTestCaseById(id).getBody();

            putTestCase.setTitle("Edited_"+putTestCase.getTitle());
            putTestCase.setDescription("Edited_"+putTestCase.getDescription());
            putTestCase.setExpected_result("Edited_"+putTestCase.getExpected_result());
            putTestCase.getTest_steps().forEach(
                    i -> i.setValue("Edited_"+i.getValue())
            );
            String jsonString = gson.toJson(putTestCase);
            ResponseEntity<List<GetCasesResponseBody>> responseEntity = htecClient.putTestcase(id, jsonString);
            GetCasesResponseBody getCasesResponseBody = responseEntity.getBody().stream()
                    .filter(i -> i.getId().equals(id))
                    .findAny()
                    .get();
            Assert.assertEquals(putTestCase.getTitle(), getCasesResponseBody.getTitle());
            Assert.assertEquals(putTestCase.getDescription(), getCasesResponseBody.getDescription());
            Assert.assertEquals(putTestCase.getExpected_result(), getCasesResponseBody.getExpected_result());

        }
    }

    @Test
    public void test4DeleteAllCreatedAndEdited() {
        listCasesIds.forEach(id -> {
            ResponseEntity<String> message = htecClient.deleteById(id);
            Assert.assertEquals("{\"success\":\"Test case successfully removed\"}", message.getBody());
        });
        listCasesIds.clear();
    }
}
