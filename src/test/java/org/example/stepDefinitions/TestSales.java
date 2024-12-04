package org.example.stepDefinitions;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.example.*;
import org.json.JSONObject;
import org.testng.Assert;

import java.util.*;

public class TestSales {
    SOQLQuery soqlQuery = new SOQLQuery();
    RequestPayloadMapper requestPayloadMapper = new RequestPayloadMapper();
    OpportunityRequestHandler opportunityRequest = new OpportunityRequestHandler();
    OppProductRequestHandler insertOppProduct = new OppProductRequestHandler();
    Map<String, Object> oppHistorico;
    Map<String, Object> product;
    Map<String, Object> oppProduct;
    String oppId, oppProdId, prodId;

    @Before
    public void establishSalesforceConnection() {
        EstablishSalesforceConnection.establishConnection();
    }

    @Given("que el usuario ha creado una nueva oportunidad con todos los datos requeridos")
    public void creacionDeUnaOportunidad() {
        JSONObject opportunityJson = requestPayloadMapper.generateOpportunityJsonPayload();
        Response response = opportunityRequest.postOpportunityRequest(opportunityJson);
        oppId = response.then().log().ifValidationFails().statusCode(201).extract().path("id");
    }

    @Given("que el usuario ha añadido un producto a la oportunidad con la información correspondiente")
    public void creacionProductoOportunidad() {
        JSONObject oppProductJson = requestPayloadMapper.generateOpportunityProductJsonPayload(oppId);
        Response responseOppProduct = insertOppProduct.postOpportunityProductRequest(oppProductJson);
        oppProdId = responseOppProduct.then().log().ifValidationFails().statusCode(201).extract().path("id");
    }

    @When("el usuario actualiza la etapa de la oportunidad a 'Invoiced'")
    public void cambiarEtapaAInvoiced() {
        Map<String, Object> opp = new HashMap<>();
        opp.put("StageName", "Invoiced");
        JSONObject opportunity2 = requestPayloadMapper.generateUpdatedOpportunityJsonPayload(opp);
        opportunityRequest.pathOpportunityRequest(opportunity2, oppId).then().log().ifValidationFails().statusCode(204);
    }

    @Then("se debe generar un registro histórico por cada producto relacionado a la oportunidad")
    public void generacionDelRegHistorico() {
        // obtener datos del registro historico relacionado a la oportunidad
        oppHistorico =  soqlQuery.getRecordHistoricByOppId(oppId);
        Assert.assertFalse(oppHistorico.isEmpty());
    }

    @Then("los campos del registro histórico deben autocompletarse con los datos correspondientes")
    public void AutocompletadoDeCamposRegHistorico() {
        //obtener datos del producto relacionado al registro historico
        prodId = (String) oppHistorico.get("Producto__c");
        product = soqlQuery.getRecordProductByProdId(prodId);
        Assert.assertFalse(product.isEmpty());

        // obtener datos del registro de producto de oportunidad
        oppProduct = soqlQuery.getRecordOppProductByOppProdId(oppProdId);
        Assert.assertFalse(oppProduct.isEmpty());

        for (String keyOppProd : oppProduct.keySet()) {
            String keyNew;
            switch (keyOppProd) {
                case "Quantity":
                    keyNew = "Cantidad__c";
                    break;
                case "UnitPrice":
                    keyNew = "Precio_de_venta__c";
                    break;
                case "Costo_unitario__c":
                    keyNew = "Costo_Unitario__c";
                    break;
                default:
                    keyNew = keyOppProd;
                    break;
            }

            product.put(keyNew, oppProduct.get(keyOppProd));

        }
        //verificar el autocompleatado de campos en el registro historico
        List<Object> fieldsNoCoincidentes = new ArrayList<>();
        boolean coincide = true;
        for (String keyProd : product.keySet()) {
            Object valorAtributoHist = oppHistorico.get(keyProd);
            Object valorAtributoProd = product.get(keyProd);
            coincide &= (Objects.equals(valorAtributoProd,valorAtributoHist));

            if(!Objects.equals(valorAtributoProd,valorAtributoHist)){
                    fieldsNoCoincidentes.add(keyProd);
            }

        }
        System.out.println("Campos no coincidentes en el registro historico" + fieldsNoCoincidentes);
        Assert.assertTrue(coincide);

    }


}
