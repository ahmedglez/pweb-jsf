package cu.edu.cujae.pweb.utils;

import java.net.URI;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import cu.edu.cujae.pweb.config.ApplicationProperties;

@Service
public class RestService {
	
	private RestTemplate restTemplate;
	
	private ApplicationProperties properties = new ApplicationProperties();
	
    public ResponseEntity GET(String endpoint, MultiValueMap<String,String> queryParams, Class clss) {
        return this.GET(endpoint, queryParams, clss, null);
    }

    public ResponseEntity GET(String endpoint, MultiValueMap<String,String> queryParams, Class clss, String jwt) {
        try {
            return buildRestTemplate().exchange(
            		getUri(getUrlBackend() + endpoint, queryParams),
                    HttpMethod.GET,
                    HttpEntity(jwt),
                    clss);
        } catch (HttpServerErrorException e) {
            return handleRequestException(e);
        }
    }
    
    public ResponseEntity POST(String endpoint, Object body, Class clss) {
        return this.POST(endpoint, body, clss, null);
    }

    public ResponseEntity POST(String endpoint, Object body, Class clss, String jwt) {
        try {
            return buildRestTemplate().exchange(
            		getUrlBackend() + endpoint,
                    HttpMethod.POST,
                    new HttpEntity(body, HttpHeadersForm(jwt)),
                    clss);
        } catch (HttpServerErrorException e) {
            return handleRequestException(e);
        }
    }

    public ResponseEntity PUT(String endpoint, MultiValueMap<String, String> queryParams, Object body, Class clss) {
        return this.PUT(endpoint, queryParams, body, clss, null);
    }

    public ResponseEntity PUT(String endpoint, MultiValueMap<String, String> queryParams, Object body, Class clss, String jwt) {
        try {
            return buildRestTemplate().exchange(
                    getUri(getUrlBackend() + endpoint, queryParams),
                    HttpMethod.PUT,
                    new HttpEntity(body, HttpHeadersForm(jwt)),
                    clss);
        } catch (HttpServerErrorException e) {
            return handleRequestException(e);
        }
    }
    
    public ResponseEntity DELETE(String endpoint, MultiValueMap<String,String> queryParams, Class clss) {
        return this.DELETE(endpoint, queryParams, clss, null);
    }

    public ResponseEntity DELETE(String endpoint, MultiValueMap<String,String> queryParams, Class clss, String jwt) {
        try {
            return buildRestTemplate().exchange(
            		getUri(getUrlBackend() + endpoint, queryParams),
                    HttpMethod.DELETE,
                    HttpEntity(jwt),
                    clss);
        } catch (HttpServerErrorException e) {
            return handleRequestException(e);
        }
    }

    public ResponseEntity PATCH(String endpoint, MultiValueMap<String, String> queryParams, String body, Class clss) {
        return this.PATCH(endpoint, queryParams, body, clss, null);
    }

    public ResponseEntity PATCH(String endpoint, MultiValueMap<String, String> queryParams, Object body, Class clss, String jwt) {
        try {
            return buildRestTemplate().exchange(
                    getUri(getUrlBackend() + endpoint, queryParams),
                    HttpMethod.PATCH,
                    new HttpEntity(body, HttpHeadersForm(jwt)),
                    clss);
        } catch (HttpServerErrorException e) {
            return handleRequestException(e);
        }
    }

    private String getUri(String endpoint, MultiValueMap<String, String> queryParams){
        return UriComponentsBuilder
                .fromUriString(endpoint)
                .queryParams(queryParams)
                .toUriString();
    }
    
    private URI getURI(String endpoint, MultiValueMap<String, String> queryParams){
        return UriComponentsBuilder
                .fromUriString(endpoint)
                .queryParams(queryParams)
                .build(false).toUri();
    }

    private HttpEntity HttpEntity(String jwt){
        return new HttpEntity(HttpHeadersJson(jwt));
    }

    private HttpEntity HttpEntity(String jwt, String body){
        return new HttpEntity(body, HttpHeadersForm(jwt));
    }

    private HttpHeaders HttpHeadersJson(String jwt){
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
        headers = setAuth(headers, jwt);
        return headers;
    }

    private HttpHeaders HttpHeadersForm(String jwt){
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
        headers = setAuth(headers, jwt);
        return headers;
    }

    private HttpHeaders setAuth(HttpHeaders headers, String jwt) {
        if (jwt != null) {
            headers.set(HttpHeaders.AUTHORIZATION, "Bearer " + jwt);
        }
        return headers;
    }

    private RestTemplate buildRestTemplate(){
    	restTemplate = new RestTemplate();
    	restTemplate.setErrorHandler(new RestTemplateResponseErrorHandler());
    	return restTemplate;
    }

    private ResponseEntity handleRequestException(HttpServerErrorException exception){
        HttpStatus status = exception.getStatusCode();
        String message = exception.getLocalizedMessage();
        return ResponseEntity
                .status(status)
                .body(message);
    }
    
    private String getUrlBackend() {
        String backendHost = properties.readProperty("backend-host");
        String backendSchema = properties.readProperty("backend-schema");
        String backendPort = properties.readProperty("backend-port");
        String contextPath = properties.readProperty("backend-context-path");
        
		String back = backendSchema + "://" + backendHost + ":" + backendPort + contextPath;
		return back;
	}

}
