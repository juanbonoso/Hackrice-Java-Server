/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;

/**
 *
 * @author Juan
 */
public class Probar {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
         Mariadb mb = new Mariadb();
         mb.insertClient("092476657", "Juan", "Bonoso", "12345", "0999999999","juanbonoso@gmail.com","12345");
         mb.insertCreditCard(1, "44444444444444444444444", "243", "23/18", "Mastercard");
    }
    
}
