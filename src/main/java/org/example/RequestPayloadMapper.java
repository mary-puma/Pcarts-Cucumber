//campos lookup solo aceptan ID y se tiene que respetar la dependencia entre campos
package org.example;
import org.json.JSONObject;

import java.util.Map;

public class RequestPayloadMapper {
    TestDataProvider testDataProvider = new TestDataProvider();

    public  JSONObject generateOpportunityJsonPayload() {
        Map<String, Object> opportunity = testDataProvider.createOpportunityMap();
        return new JSONObject(opportunity);
    }
    public  JSONObject generateOpportunityProductJsonPayload(String id) {
        Map<String, Object> opportunityProduct = testDataProvider.createOpportunityProductMap(id);
        return new JSONObject(opportunityProduct);
    }

    public  JSONObject generateUpdatedOpportunityJsonPayload(Map<String, Object> opp) {
        return new JSONObject(opp);
    }

}
