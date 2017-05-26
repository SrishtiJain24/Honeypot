import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.*;
 
public class Client
{ 
    public static Socket socket; 
    public static void sendserver(String uname, String pwd)
    {
        Scanner one=new Scanner(System.in);
        System.out.println(uname+" "+pwd);
        try
        {
            uname=uname+"\n";
            pwd=pwd +"\n";

            String host = "192.168.137.17";
            int port = 25000;
            InetAddress address = InetAddress.getByName(host);
            socket = new Socket(address, port);
 
            //Send the message to the server
            OutputStream os = socket.getOutputStream();
            OutputStreamWriter osw = new OutputStreamWriter(os);
            BufferedWriter bw = new BufferedWriter(osw);
                        
            bw.write(uname);
             bw.write(pwd);
            bw.flush();
            System.out.println("Message sent to the server : "+uname);
            

            System.out.println("Enter querry");
            String q=one.nextLine();

            q=q+"\n";
             OutputStream osq = socket.getOutputStream();
            OutputStreamWriter oswq = new OutputStreamWriter(osq);
            BufferedWriter bwq = new BufferedWriter(oswq);
                        
            bwq.write(q);
            bwq.flush();
            System.out.println("Message sent to the server : "+q);


            //Get the return message from the server
            InputStream is = socket.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            String message = br.readLine();
            System.out.println("Message received from the server : " +message);

           // sc.close();
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
        }
        finally
        {
            //Closing the socket
            try
            {
                socket.close();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
    }


    public static void main(String args[])
    {
        sendserver("Srishti", "1234");
    }
}