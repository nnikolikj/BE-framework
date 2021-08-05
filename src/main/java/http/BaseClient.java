package http;

import model.get.GetCasesResponseBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;
import java.util.List;

public abstract class BaseClient {
    protected static Logger logger = LoggerFactory.getLogger(BaseClient.class);
    protected String baseUrl;
    protected HttpHeaders httpRequestHeaders = new HttpHeaders();
    private RestTemplate restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory());

    protected ResponseEntity handleRequest(String uri, HttpMethod httpMethod, Class clazz, HttpEntity<?> httpEntity, Object... args) {
        try {
            restTemplate.getMessageConverters()
                    .add(0, new StringHttpMessageConverter(Charset.forName("UTF-8")));
            ResponseEntity response = restTemplate.exchange(uri, httpMethod, httpEntity, clazz, args);
            return response;
        } catch (Exception e) {
            logger.info("Unhandled Exception : " + e.getMessage());
            return null;
        }
    }

    private <T, E> ResponseEntity<List<E>> handleRequestReturnList(String route, HttpMethod method, T body) {
        try {
            restTemplate.getMessageConverters()
                    .add(0, new StringHttpMessageConverter(Charset.forName("UTF-8")));
            HttpEntity httpEntity = body != null ? createHttpEntity(body) : createHttpEntity();
            ResponseEntity response = restTemplate.exchange(this.baseUrl + route, method, httpEntity,
                    new ParameterizedTypeReference<List<GetCasesResponseBody>>() {});
            return response;
        } catch (Exception e) {
            logger.info("Unhandled Exception : " + e.getMessage());
            return null;
        }
    }

    protected <T, E> ResponseEntity<List<E>> postAndReturnListResponse(String route, T body) {
        return handleRequestReturnList(route, HttpMethod.POST, body);
    }

    protected <T, E> ResponseEntity<List<E>> putAndReturnListResponse(String route, T body) {
        return handleRequestReturnList(route, HttpMethod.PUT, body);
    }

    protected <T, E> ResponseEntity<List<E>> getAllReturnListResponse(String route) {
       return handleRequestReturnList(route, HttpMethod.GET,null);
    }


    protected <T, E> ResponseEntity<E> post(String route, T body, Class<E> responseType, Object... args) {
        return handleRequest(this.baseUrl + route, HttpMethod.POST, responseType, createHttpEntity(body), args);
    }

    protected <E> ResponseEntity<E> get(String route, Class<E> responseType, Object... args) {
        return handleRequest(this.baseUrl + route, HttpMethod.GET, responseType, createHttpEntity(), args);
    }

    protected <E> ResponseEntity<E> delete(String route, Class<E> responseType, Object... args) {
        return handleRequest(this.baseUrl + route, HttpMethod.DELETE, responseType, createHttpEntity(), args);
    }

    public void addHeader(String key, String value) {
        this.httpRequestHeaders.add(key, value);
    }

    private HttpEntity<?> createHttpEntity(Object body) {
        return new HttpEntity<>(body, this.httpRequestHeaders);
    }

    private HttpEntity<?> createHttpEntity() {
        return new HttpEntity<>(this.httpRequestHeaders);
    }
}
