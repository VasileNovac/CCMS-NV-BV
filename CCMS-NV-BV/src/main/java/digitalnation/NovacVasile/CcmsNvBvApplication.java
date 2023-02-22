package digitalnation.NovacVasile;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CcmsNvBvApplication {

	private static String sql ;
	public static String luna, an, denLuna ;
//JDBC driver name and database URL 
	static final String JDBC_DRIVER = "org.h2.Driver";   
//	static final String DB_URL = "jdbc:h2:~/test;IFEXISTS=TRUE;DB_CLOSE_ON_EXIT=FALSE"; 
	static final String DB_URL = "jdbc:h2:~/test;IFEXISTS=TRUE"; 

//Database credentials 
	static final String USER = "sa"; 
	static final String PASS = ""; 

	public static Connection conn = null; 
	public static Statement stmt = null; 

	public static void main(String[] args) {
		SpringApplication.run(CcmsNvBvApplication.class, args);

		try { 
//			Register JDBC driver 
		        Class.forName(JDBC_DRIVER); 
//			Open a connection 
		        conn = DriverManager.getConnection(DB_URL,USER,PASS);

//				Drop table. 
//		        stmt = conn.createStatement();
//		        sql = "DROP TABLE IF EXISTS initF" ;
//		        stmt.executeUpdate(sql) ;
//			Create table
		        stmt = conn.createStatement() ;
		        sql = "CREATE TABLE IF NOT EXISTS initF " +
		        		"( cif varchar(20)," +
		        		"an varchar(4)," +
		        		"denUnitate varchar(30)," +
		        		"nrORC varchar(15)," +
		        		"contB varchar(24)," +
		        		"banca varchar(30)," +
		        		"cotaTVA integer," +
		        		"PRIMARY KEY (cif) )" ;
		        stmt.executeUpdate(sql) ;

//				Drop table. 
//		        stmt = conn.createStatement();
//		        sql = "DROP TABLE IF EXISTS initC" ;
//		        stmt.executeUpdate(sql) ;
//			Create table
		        stmt = conn.createStatement() ;
		        sql = "CREATE TABLE IF NOT EXISTS initC " +
		        		"( cif varchar(20)," +
		        		"sezon varchar(2)," +
		        		"an varchar(4)," +
		        		"dela varchar(10)," +
		        		"pila varchar(10)," +
		        		"cort integer," +
		        		"cortPers integer," +
		        		"cortEE integer," +
		        		"rulota integer," +
		        		"rulotaPers integer," +
		        		"rulotaEE integer," +
		        		"auto integer," +
		        		"moto integer," +
		        		"PRIMARY KEY (cif, an, sezon) )" ;
		        stmt.executeUpdate(sql) ;

//			Drop table. 
//		        stmt = conn.createStatement();
//		        sql = "DROP TABLE IF EXISTS client" ;
//		        stmt.executeUpdate(sql) ;
//			Create table
	        	stmt = conn.createStatement(); 
		       	sql = "CREATE TABLE IF NOT EXISTS client " + 
		       			 "( cif varchar(20)," +
		        		 "actId varchar(4)," +
		        		 "serieActId varchar(4)," +
		        		 "nrActId varchar(10)," +
		        		 "dataExpActId varchar(10)," +
		        		 "nume varchar(30)," +
		        		 "prenume varchar(30)," +
		        		 "nrIAuto varchar(10)," +
		        		 "dataNastere varchar(10)," +
		        		 "locNastere varchar(30)," +
		        		 "cetatenie varchar(20)," +
		        		 "loc varchar(30)," +
		        		 "str varchar(30)," +
		        		 "nr varchar(5)," +
		        		 "jud varchar(15)," +
		        		 "tara varchar(30)," +
		        		 "scopCal varchar(30)," +
		        		 "PRIMARY KEY (cif, actId, serieActId, nrActId)) " ;
		        stmt.executeUpdate(sql);

//			Drop table. 
//		        stmt = conn.createStatement();
//		        sql = "DROP TABLE IF EXISTS sejur" ;
//		        stmt.executeUpdate(sql) ;
//			Create table
	        	stmt = conn.createStatement(); 
		        sql = "CREATE TABLE IF NOT EXISTS sejur " + 
		        		 "( cif varchar(20)," +
		        		 "actId varchar(4)," +
		        		 "serieActId varchar(4)," +
		        		 "nrActId varchar(10)," +
		        		 "dtSosit varchar(10)," +
		        		 "dtPlecat varchar(10)," +
		        		 "nrCort integer," +
		        		 "nrPCort integer," +
		        		 "nrPersC integer," +
		        		 "nrEEC integer," +
		        		 "nrRulota integer," +
		        		 "nrPersR integer," +
		        		 "nrEER integer," +
		        		 "nrAuto integer," +
		        		 "nrMoto integer," +
		        		 "PRIMARY KEY (cif, actId, serieActId, nrActId, dtSosit, dtPlecat) )";  
		        stmt.executeUpdate(sql);

/*
//			Drop table. 
		        stmt = conn.createStatement();
		        sql = "DROP TABLE IF EXISTS factura" ;
		        stmt.executeUpdate(sql) ;
//			Create table
	        	stmt = conn.createStatement(); 
		        sql = "CREATE TABLE IF NOT EXISTS factura " + 
		        		"( cif varchar(20)," +
		        		"serie varchar(10)," +
		        		"numar integer," +
		        		"dataFact varchar(10)," +
		        		"denUnitate varchar(30)," +
		        		"nrORC varchar(15)," +
		        		"contB varchar(24)," +
		        		"banca varchar(30)," +
		        		"nume varchar(30)," +
		        		"prenume varchar(30)," +
		        		"actId varchar(4)," +
		        		"serieActId varchar(4)," +
		        		"nrActId varchar(10)," +
		        		"valoare float," +
		        		"cnp varchar(13)," + 
		        		"PRIMARY KEY (cif, serie, numar) )" ;
		        stmt.executeUpdate(sql);
*/
			} catch(SQLException se) { 
//			Handle errors for JDBC 
		        se.printStackTrace(); 
		    } catch(Exception e) { 
//			Handle errors for Class.forName 
		        e.printStackTrace(); 
		    } finally { }
		}
}
