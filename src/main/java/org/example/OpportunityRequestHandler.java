package org.example;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;

import static io.restassured.RestAssured.given;

public class OpportunityRequestHandler {
    TestEnvironment testEnvironment = new TestEnvironment();
    public Response postOpportunityRequest(JSONObject opportunity) {
        return
                given().
                        contentType(ContentType.JSON).
                        accept(ContentType.JSON).
                        header(Constants.AUTHORIZATION, Constants.BEARER + Constants.ACCESS_TOKEN).
                        header(Constants.CONTENT_TYPE, Constants.APPLICATION_JSON).
                        body(opportunity.toString()).
                        when().post(testEnvironment.getEndpoint() + "/services/data/v61.0/sobjects/Opportunity");
    }

    public Response pathOpportunityRequest(JSONObject opportunity, String oppId) {
        return
                given().
                        contentType(ContentType.JSON).
                        accept(ContentType.JSON).
                        header(Constants.AUTHORIZATION, Constants.BEARER + Constants.ACCESS_TOKEN).
                        header(Constants.CONTENT_TYPE, Constants.APPLICATION_JSON).
                        body(opportunity.toString()).
                        when().patch(testEnvironment.getEndpoint() + "/services/data/v61.0/sobjects/Opportunity/" + oppId + "");
    }
}
