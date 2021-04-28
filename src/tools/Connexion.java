/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Nizar
 */
public class Connexion {
  final static String URL="jdbc:mysql://127.0.0.1:3306/jobcore";
final static String LOGIN="root";
final static String PWD="";
static Connexion instance =null;
private Connection cnx;
public Connection getConnection;
private Connexion(){
try{
cnx=DriverManager.getConnection(URL,LOGIN,PWD);
System.out.println("Connexion etablie");
} catch (SQLException ex) {
System.out.println("pas de connexion");
}
}
public static Connexion getInstance()
{
if(instance==null)
{
instance= new Connexion();
}
return instance;
}
public Connection getConnection(){
return cnx;
}

}