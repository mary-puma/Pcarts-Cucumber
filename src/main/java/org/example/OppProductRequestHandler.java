package org.example;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;

import static io.restassured.RestAssured.given;

public class OppProductRequestHandler {
    TestEnvironment testEnvironment = new TestEnvironment();
    public Response postOpportunityProductRequest(JSONObject product) {
        return
                given().
                        contentType(ContentType.JSON).
                        accept(ContentType.JSON).
                        header(Constants.AUTHORIZATION, Constants.BEARER + Constants.ACCESS_TOKEN).
                        header(Constants.CONTENT_TYPE, Constants.APPLICATION_JSON).
                        body(product.toString()).
                        when().post(testEnvironment.getEndpoint() + "/services/data/v61.0/sobjects/OpportunityLineItem");
    }
}
