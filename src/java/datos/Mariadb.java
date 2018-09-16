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
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Mariadb {
    // JDBC driver name and database URL

    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    //static final String DB_URL = "jdbc:mariadb://localhost/inclusion";
    static final String DB_URL = "jdbc:mysql://node39346-creditpool.jl.serv.net.mx/joao?useSSL=false";
    


    //  Database credentials
    /*
    static final String USER = "root";
    static final String PASS ="";*/ 
    static final String USER = "root";
    static final String PASS ="LMTvyq50623";
    Connection conn = null;
    Statement stmt = null;
    public Mariadb() {
        
        try{
            //STEP 2: Register JDBC driver
            //Class.forName("org.mariadb.jdbc.Driver");
            Class.forName(JDBC_DRIVER);
            //STEP 3: Open a connection
            System.out.println("Connecting to a selected database...");
            conn = DriverManager.getConnection(
                    DB_URL, USER, PASS);
            System.out.println("Connected database successfully...");

            
         } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } 
    }
    
    public void insertClient(String scn,String name,String last_name,String address,String mobile,String email,String password ){
        
        try {
            PreparedStatement ppst = null;
            String sql ="Insert into joao.client (SCN,NAME,LAST_NAME,ADDRESS,MOBILE,EMAIL,PASSWORD) values (?,?,?,?,?,?,?)";
            
            ppst = conn.prepareStatement(sql);           
            ppst.setString(1, scn);
            ppst.setString(2, name);
            ppst.setString(3, last_name);
            ppst.setString(4, address);
            ppst.setString(5, mobile);
            ppst.setString(6, email);
            ppst.setString(7, password);
            ppst.executeUpdate();
            System.out.println(name + " Was created ");
            } //end main
        catch (SQLException ex) {
            Logger.getLogger(Mariadb.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(name + " Not created ");
        }
    }
    
    public void insertCreditCard(int id_client, String credit_card,String ccv, String exp_date, String network ){
        
        try {
            PreparedStatement ppst = null;
            String sql ="Insert into joao.credit_card_list (ID_CLIENT,CREDIT_CARD,CCV,EXP_DATE,NETWORK,CUPO) values (?,?,?,?,?,?)";
            
            ppst = conn.prepareStatement(sql);           
            ppst.setInt(1, id_client);
            ppst.setString(2, credit_card);
            ppst.setString(3, ccv);
            ppst.setString(4, exp_date);
            ppst.setString(5, network);
            Double numeroAleatorio = (Math.random()* 10000+1);
            while(numeroAleatorio<1000){
                numeroAleatorio = (Math.random()* 10000+1);
            }
            BigDecimal bd = new BigDecimal(numeroAleatorio);
            bd = bd.setScale(2, RoundingMode.HALF_UP);
            double limit = bd.doubleValue();
            ppst.setDouble(6, limit);            
            ppst.executeUpdate();
            System.out.println(network + " acepted");
            } //end main
        catch (SQLException ex) {
            Logger.getLogger(Mariadb.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(network + " denied");
        }
    }
     public ArrayList<CardList> selectCreditCard(int id_client){
        ArrayList<CardList> lista = new ArrayList<>();
        try {            
        
            PreparedStatement ppst = null;
            String sql ="Select * from credit_card_list where id_client = ?";
            
            ppst = conn.prepareStatement(sql);           
            ppst.setInt(1, id_client);            
            ResultSet rs = ppst.executeQuery();            
                while (rs.next()) {
                    String network   = rs.getString("NETWORK");
                    String credit_card = rs.getString("CREDIT_CARD");
                    credit_card = credit_card.substring(11, 15);
                    Double cupo = rs.getDouble("CUPO");
                    CardList cd = new CardList(network,credit_card,cupo);
                    System.out.println(cd);
                    lista.add(cd);
                }           
            
            }//end main
        catch (SQLException ex) {
            Logger.getLogger(Mariadb.class.getName()).log(Level.SEVERE, null, ex);
            } 
            return lista;
        }
    public String yaFue(int id_client){
        String mensaje = null;
        try {            
        
            PreparedStatement ppst = null;
            String sql ="Select * from credit_card_list where id_client = ?";
            
            ppst = conn.prepareStatement(sql);           
            ppst.setInt(1, id_client);            
            ResultSet rs = ppst.executeQuery();            
                while (rs.next()) {
                    String network   = rs.getString("NETWORK");
                    String credit_card = rs.getString("CREDIT_CARD");
                    credit_card = credit_card.substring(11, 15);
                    Double cupo = rs.getDouble("CUPO");
                    CardList cd = new CardList(network,credit_card,cupo);
                    System.out.println(cd);
                    mensaje += cd.getCredit_card() + "," + cd.getNetwork() + "," + cd.getCupon() + "@";
                }           
            
            }//end main
        catch (SQLException ex) {
            Logger.getLogger(Mariadb.class.getName()).log(Level.SEVERE, null, ex);
            } 
            return mensaje;
        }
    
}//end JDBCExample
