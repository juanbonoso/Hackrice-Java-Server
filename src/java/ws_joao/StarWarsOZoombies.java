/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws_joao;

import datos.CardList;
import datos.Mariadb;
import java.util.ArrayList;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author Juan
 */
@WebService(serviceName = "StarWarsOZoombies")
public class StarWarsOZoombies {
Mariadb mb;
    public StarWarsOZoombies() {
        mb = new Mariadb();
    }
    

    /**
     * This is a sample web service operation
     */
    @WebMethod(operationName = "hello")
    public String hello(@WebParam(name = "name") String txt) {
        return "Hello " + txt + " !";
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "InsertClient")
    public void InsertClient(@WebParam(name = "scn") String scn, @WebParam(name = "name") String name, @WebParam(name = "last_name") String last_name, @WebParam(name = "address") String address, @WebParam(name = "mobile") String mobile, 
            @WebParam(name = "email") String email, @WebParam(name = "password") String password) {
        //TODO write your implementation code here:
        
        mb.insertClient(scn, name, last_name, address, mobile,email,password);
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "InsertCreditCard")
    public void InsertCreditCard(@WebParam(name = "id_client") int id_client, @WebParam(name = "credit_card") String credit_card, @WebParam(name = "ccv") String ccv, @WebParam(name = "exp_date") String exp_date, @WebParam(name = "network") String network) {
        //TODO write your implementation code here:
        mb.insertCreditCard(id_client, credit_card, ccv, exp_date, network);
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "SelectCardList")
    public ArrayList<CardList> SelectCardList(@WebParam(name = "id_client") int id_client) {
        //TODO write your implementation code here:
        return mb.selectCreditCard(id_client);
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "yaFue")
    public String yaFue(@WebParam(name = "id_client") int id_client) {
        //TODO write your implementation code here:
        return mb.yaFue(id_client);
    }

   

   
   
    
    
   
    
    
    
}
