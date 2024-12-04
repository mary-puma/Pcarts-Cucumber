package org.example;

import java.util.Map;

public class SOQLQuery {

    SOQLService soqlService = new SOQLService();

    public String getIdAccountByName(String nameAccount) {
        String idAccount = (String) soqlService.getSoqlResult("SELECT id FROM Account WHERE Name = '" + nameAccount + "'").get("Id");
        return idAccount;
    }

    public String getIdContactByName(String nameContact) {
        String idContact = (String) soqlService.getSoqlResult("SELECT id FROM Contact WHERE Name = '" + nameContact + "'").get("Id");
        return idContact;
    }

    public String getIdProductByName(String nameProduct) {
        String idProduct = (String) soqlService.getSoqlResult("SELECT id FROM Product2 WHERE Name = '" + nameProduct + "'").get("Id");
        return idProduct;
    }
    public String getIdPriceBookByName(String namePriceBook) {
        String idPriceBook = (String) soqlService.getSoqlResult("SELECT id FROM Pricebook2 WHERE Name = '" + namePriceBook + "'").get("Id");
        return idPriceBook;
    }

    public Map<String, Object> getRecordHistoricByOppId(String oppId) {

        return soqlService.
                getSoqlResult("SELECT Producto__c, Segmento__c, Categoria__c, Jerarquia__c, Segmento_de_marca__c, PM_Responsable__c, Cantidad__c, Precio_de_venta__c, Costo_Unitario__c  " +
                        "FROM Historico_P_Oportunidades__c " +
                        "WHERE Oportunidad__c = '" + oppId + "'");
    }

    public Map<String, Object> getRecordProductByProdId(String prodId) {

        return soqlService.
                getSoqlResult("SELECT Segmento__c, Categoria__c, Jerarquia__c, Segmento_de_marca__c, PM_Responsable__c " +
                        "FROM Product2 " +
                        "WHERE id = '" + prodId + "'");
    }
    public Map<String, Object> getRecordOppProductByOppProdId(String oppProdId) {

        return soqlService.
                getSoqlResult("SELECT Quantity, UnitPrice, Costo_unitario__c " +
                        "FROM OpportunityLineItem  " +
                        "WHERE id = '" + oppProdId + "'");
    }


}
