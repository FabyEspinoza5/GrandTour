package controlador.SQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionSQL_tests {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String connectionURL=""
            + "jdbc:sqlserver://GUSTAVASHO:1433;"
            + "databaseName=PRODUCTS;"
            + "user=usuarioSQL;"
            + "password=321";
        Connection con=DriverManager.getConnection(connectionURL);
        System.out.println("Successful Connection");

        Statement st=con.createStatement();
        ResultSet rs=st.executeQuery("SELECT* FROM PAQUETE");
        
        while (rs.next()){
            String codigo=rs.getString(1);
            String img=rs.getString(2);
            String name=rs.getString(3);
            String description=rs.getString(4);
            double cost=rs.getDouble(5);
            String link=rs.getString(6);
            System.out.println(codigo+"\t"+img+"\t"+name+"\t"+description+"\t"+cost+"\t"+link);
        }
        
    }
    
}
