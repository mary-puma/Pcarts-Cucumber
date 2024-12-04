package org.example;

import java.util.HashMap;
import java.util.Map;

public class TestDataProvider {

    SOQLQuery soqlQuery = new SOQLQuery();
    public  Map<String, Object> createOpportunityMap(){
        Map<String, Object> opportunity = new HashMap<String, Object>();

        String cuentaReseller = soqlQuery.getIdAccountByName("GAIDO MARCELO ALEJANDRO");
        String contactoDelReseller = soqlQuery.getIdContactByName("Marcelo Gaido");
        String cuentaFinal = soqlQuery.getIdAccountByName("SOLMAC OBRAS Y SERVICIOS S A");
        String opportunityName = "test 5";
        String condicionDePago = "Y071 - Pago Contado Pesos/Dólares (Depósito-Cheque)";
        String closeDate = "2023-8-25";
        String validezDeOferta = "15";
        String stageName = "Committed";
        String modalidadDeVenta = "Directa";
        String priceBook = soqlQuery.getIdPriceBookByName("Standard Price Book");
        String contact= soqlQuery.getIdContactByName("Test Mary");

        opportunity.put("Nombre_de_la_cuenta_RESELLER__c", cuentaReseller);
        opportunity.put("Contacto_del_reseller__c", contactoDelReseller);
        opportunity.put("Nombre_de_la_cuenta_FINAL__c", cuentaFinal);
        //opportunity.put("Name", opportunityName);
        opportunity.put("Condici_n_de_pago__c", condicionDePago);
        opportunity.put("CloseDate", closeDate);
        opportunity.put("Validez_de_la_oferta__c", validezDeOferta);
        opportunity.put("StageName", stageName);
        opportunity.put("Modalidad_ade_venta__c", modalidadDeVenta);
        opportunity.put("Pricebook2Id", priceBook);
        opportunity.put("Contacto__c",contact);
        return opportunity;
    }

    public  Map<String, Object> createOpportunityProductMap(String opportunityId){
        Map<String, Object> opportunityProduct = new HashMap<String, Object>();

        String product = soqlQuery.getIdProductByName("CONSUMO CONSUMO ADVANTEK");//CONSUMO CONSUMO ADVANTEK, ENTERPRISE RUNRATE ASUS OTROS
        String quantity = "3";
        String salesPrice = "4";
        String costoUnitario = "2";

        opportunityProduct.put("OpportunityId", opportunityId);
        opportunityProduct.put("Product2Id",product );
        opportunityProduct.put("Quantity", quantity);
        opportunityProduct.put("UnitPrice", salesPrice);
        opportunityProduct.put("Costo_unitario__c", costoUnitario);
        return opportunityProduct;
    }
}
