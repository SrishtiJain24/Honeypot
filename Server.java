
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.*;

public class Server {
	
	
	

public boolean validate (String un, String pw) throws SQLException
	{
		Connection cn=null;
		//System.out.println("hello");
		boolean st=false;
		try
		{
			Class.forName("org.sqlite.JDBC");
			cn=DriverManager.getConnection("jdbc:sqlite:lab.db");
			if (cn != null) {
                System.out.println("Connected to the database");
            }
		
			Statement stmt=cn.createStatement();
			//stmt.execute("USE Users");
			String str="select * from userinfo where username='"+un+"' and password='"+pw+"'";		
		
		ResultSet rs =stmt.executeQuery(str);
		System.out.println(rs);
		st = rs.next();

		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
	return st;		
		
}


    private static Socket socket;
    public static void main(String[] args)
    {
    	int c=0;
    	Server one=new Server();
        try
        { 
            int port = 25000;
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("Server Started and listening to the port 25000");
 
            //Server is running always. This is done using this while(true) loop
            while(true)
            {
                //Reading the message from the client
                socket = serverSocket.accept();
                InputStream is = socket.getInputStream();
                InputStreamReader isr = new InputStreamReader(is);
                BufferedReader br = new BufferedReader(isr);
                String uname = br.readLine();
                System.out.println("Message received from client is "+uname);
 
                //Multiplying the number by 2 and forming the return message
                String ss="GO to HoneyPot";
                        

                //socket = serverSocket.accept();
                InputStream is2 = socket.getInputStream();
                InputStreamReader isr2 = new InputStreamReader(is2);
                BufferedReader br2 = new BufferedReader(isr2);
                String pwd = br.readLine();
                System.out.println("Message received from client 2 is "+pwd);
                 boolean s = one.validate(uname,pwd);
                 System.out.println(s);
                                      
                 if(!s)
                 {
                	 c++;
                	 
                 }
                 if(c==3)
                 {
                	 OutputStream os2 = socket.getOutputStream();
                     OutputStreamWriter osw2 = new OutputStreamWriter(os2);
                     BufferedWriter bw2 = new BufferedWriter(osw2);
                     bw2.write(ss);
                     System.out.println("Message sent to the client is "+ ss);
                     bw2.flush(); 
                 }
                 
              
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                socket.close();
            }
            catch(Exception e){}
        }
    }	
		
	
	

}
