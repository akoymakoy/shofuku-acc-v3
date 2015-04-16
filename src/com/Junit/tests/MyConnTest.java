package com.Junit.tests;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

import com.shofuku.accsystem.utils.MySQLConnectionUtil;

public class MyConnTest {
	 private static final Logger logger = Logger.getLogger(MyConnTest.class);
    public static void main(String[] args) {

    	if(logger.isDebugEnabled()){
			logger.debug("Testing .......");
		}
		
    	
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;

        String url = "jdbc:mysql://localhost:3306/shofuku";
        String user = "root";
        String password = "";

        try {
            con = DriverManager.getConnection(url, user, password);
            st = con.createStatement();
            rs = st.executeQuery("SELECT VERSION()");
            	
            int count;
            count = st.executeUpdate (
                        "INSERT INTO t101_suppliers (supplier_id, supplier_name)"
                        + " VALUES"
                        + "(0000000001, 'Mark Test')");
            st.close ();
            System.out.println (count + " rows were inserted");
            
            if (rs.next()) {
                System.out.println("OK ! "+ rs.getString(1));
            }

        } catch (SQLException ex) {

        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (st != null) {
                    st.close();
                }
                if (con != null) {
                    con.close();
                }

            } catch (SQLException ex) {
            }
        }
    }
}
