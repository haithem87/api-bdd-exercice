package com.example.api.cucumbersteps;

import io.cucumber.java.en.*;
import org.springframework.http.*;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Collections;
import java.util.Map;
import static org.junit.Assert.*;

public class UserSteps {

    private RestTemplate restTemplate;
    private String baseUrl = "https://fakestoreapi.com/users";
    private Map<String, Object> payload;
    private ResponseEntity<Map> response;
    private Integer userId;



    public UserSteps() {
        restTemplate = new RestTemplate();
        restTemplate.setMessageConverters(
                Collections.singletonList(new MappingJackson2HttpMessageConverter())
        );
    }

    @Given("I create the user from {string}")
    public void loadUserPayload(String jsonFileName) throws IOException {
        File jsonFile = new File("src/test/java/ressouces/data/"+jsonFileName+".json");
        String json = new String(Files.readAllBytes(jsonFile.toPath()));

        HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<String> request =
            new HttpEntity<>(json, headers);

		response = restTemplate.postForEntity(
            baseUrl,
            request,
            Map.class
    );
    }



    @When("I get the user by id {int}")
    public void getUserById(int userId) {
        this.userId = userId;

        response = restTemplate.getForEntity(baseUrl + "/" + userId, Map.class);
        assertNotNull(response.getBody());
        Map<String, Object> body = response.getBody();

        assertEquals(userId, body.get("id"));

        Map<String, String> name = (Map<String, String>) body.get("name");
        assertNotNull(name);
        assertNotNull(name.get("firstname"));
        assertNotNull(name.get("lastname"));

        assertNotNull(body.get("email"));
        assertNotNull(body.get("username"));
    }

    @When("I check the API status {int}")
    public void checkApiStatus(int expectedStatus) {
        // On récupère uniquement le code HTTP, pas le body
        int healthStatus;
        ResponseEntity<Void> response = restTemplate.getForEntity(baseUrl, Void.class);
        healthStatus = response.getStatusCodeValue();
        assertEquals("API status code mismatch", expectedStatus, healthStatus);

    }
	
	@Then("the response status should be {int}")
	public void validateStatus(int expectedStatus) {
    assertEquals(expectedStatus, response.getStatusCodeValue());
	}

	@Then("the response should contain an id")
	public void validateIdExists() {

    Map<String, Object> body = response.getBody();
    assertNotNull(body);
    assertNotNull(body.get("id"));
	}


}
