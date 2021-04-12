import java.net.*;
import java.io.*;

public class q2.server
{
    public static final int DEFAULT_PORT = 6007;
    public static final int BUFFER_SIZE = 256;
    private static ServerSocket sock = null;

    public static void main(String[] args)  {
        try {
            int numBytes;
            sock = new ServerSocket(DEFAULT_PORT);
            byte[] buffer = new byte[BUFFER_SIZE];

            //Listening for connections
            System.out.println("Server is listening ....");
            while(true){
                Socket client = sock.accept(); //client connected
                System.out.println("Client Connected");

                BufferedInputStream fromClient = new BufferedInputStream(client.getInputStream());
                BufferedOutputStream toClient = new BufferedOutputStream(client.getOutputStream());

                while ( (numBytes = fromClient.read(buffer)) != -1)
                {
                    System.out.println("Recieving Input from Client");

                    //Write back to socket’s output stream
                    toClient.write(buffer,0,numBytes);                    
                    System.out.println("Sending input to Client");

                    //socket’s output stream must be flushed
                    //that’s how data is sent back!
                    toClient.flush();
                }

                client.close();
                System.out.println("Client Disconnected");
            }
        }
        catch (IOException ioe) {
                System.err.println(ioe);
        }
        finally {
            try{
                if (sock != null)
                    sock.close();
            }
            catch (IOException ioe) {
                System.err.println(ioe);
            }
        }
    }
}
  