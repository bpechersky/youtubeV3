package com.youtube;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;

public class YouTubeProfileTest {

    private static final String BASE_URI = "https://www.googleapis.com/youtube/v3";

    @Test
    public void getAuthenticatedUserChannel() {
        String token = System.getenv("YOUTUBE_ACCESS_TOKEN");

        RestAssured.baseURI = BASE_URI;

        given()
                .header("Authorization", "Bearer " + token)
                .queryParam("part", "snippet,contentDetails,statistics")
                .queryParam("mine", "true")
                .when()
                .get("/channels")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("items[0].snippet.title", notNullValue())
                .body("items[0].id", notNullValue());
    }
}
