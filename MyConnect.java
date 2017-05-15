
import java.sql.DriverManager;
import java.sql.Connection;
public class MyConnect {

	static Connection getMyConnection() throws Exception
	{
		Connection cn=null;
		try
		{
			Class.forName("org.sqlite.JDBC");
			//DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
			cn=DriverManager.getConnection("jdbc:sqlite:lab.db");
			if (cn != null) {
                System.out.println("Connected to the database");
            }
		}
		catch(Exception e)
		{
		        System.out.println("CONNECTION ERROR");
		       
		        throw e;
		}
	return cn;
	}
	public static void main(String[] args)throws Exception
	{
		
		System.out.println("Connection " + getMyConnection());
	}
	}


