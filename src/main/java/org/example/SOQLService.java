package org.example;

import io.restassured.http.ContentType;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class SOQLService {
    public  Map<String, Object> getSoqlResult(String soql) {

        List<Map<String, Object>> recordsList =
                given().
                        contentType(ContentType.JSON).
                        header(Constants.AUTHORIZATION, Constants.BEARER + Constants.ACCESS_TOKEN).
                        when().
                        get("https://demopcarts--developer.sandbox.my.salesforce.com/services/data/v61.0/query?q=" + soql + "").
                        then().log().ifValidationFails().statusCode(200).extract().path("records");
        //elimino el elemento attributes del mapa
        recordsList.get(0).remove("attributes");
        //System.out.println("Respuesta de la query: " + recordsList);
        return recordsList.get(0);
    }


}
