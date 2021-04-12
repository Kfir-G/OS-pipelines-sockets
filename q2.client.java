import java.net.*;
import java.io.*;
public class q2.client
{
    public static final int DEFAULT_PORT = 6007;
    public static void main(String[] args)  {
        check1();
        check2();
    }
    
    public static void check1(){
        try {
            //Could be changed to an IP name or address other than the localhost
            Socket echoSocket = new Socket("127.0.0.1",DEFAULT_PORT);
            System.out.println("Connected to Server");

            InputStream in = echoSocket.getInputStream();
            OutputStream out = echoSocket.getOutputStream();
            BufferedReader bin =  new BufferedReader(new InputStreamReader(in));

            //True here, means the PrintWriter Object is automatically flushed!
            PrintWriter bout =  new PrintWriter(out, true);
            String toSend = "OmerNKfirNIdan";

            //Send and Receive Data:
            bout.println(toSend);
            String toRecieve;
            //read data from the socket
            toRecieve = bin.readLine();
            System.out.println(toRecieve);

            //Check if send or recieved are the same
            if(toSend.equals(toRecieve))
                System.out.println("Sent/Recieved are the same: "+toSend);
            else
                System.out.println("Sent/Recieved not the same");

            System.out.println("Disconnected from Server");

            bin.close();
            bout.close();
            echoSocket.close();
        }
        catch (IOException ioe) {
            System.err.println(ioe);
        }
        }

        public static void check2(){
            try {
                //Changed to an IP name or address other than the localhost
                Socket echoSocket = new Socket("127.0.0.1",DEFAULT_PORT);
                System.out.println("Connected to Server");
                InputStream in = echoSocket.getInputStream();
                OutputStream out = echoSocket.getOutputStream();
                BufferedReader bin =  new BufferedReader(new InputStreamReader(in));

                //true here, means the PrintWriter Object is automatically flushed!
                PrintWriter bout =  new PrintWriter(out, true);

                for (int i = 1; i < 6 ; i++ ) {
                    String toSend = "Idan"+i;

                    //Send and Receive Data:
                    bout.println(toSend);
                    String toRecieve;

                    //Read data from the socket
                    toRecieve = bin.readLine();

                    //Check if send or recieved are the same
                    if(toSend.equals(toRecieve))
                        System.out.println("Sent/Recieved are the same: "+toSend);
                    else
                        System.out.println("Sent/Recieved not the same");
                }
                System.out.println("Disconnected from Server");

                bin.close();
                bout.close();
                echoSocket.close();
            }
            catch (IOException ioe) {
                System.err.println(ioe);
            }
}
}