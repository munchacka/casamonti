package mm;

import java.io.DataInputStream;
import java.io.PrintStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 *
 * @author casam
 */
public class Client implements Runnable {

  private static Socket clientSocket = null;
  private static PrintStream os = null;
  private static DataInputStream is = null;
  private static BufferedReader inputLine = null;
  private static boolean closed = false;
  
  public static void main(String[] args) {

    int portNumber = 2222;
    String host = "localhost";

    try {
      clientSocket = new Socket(host, portNumber);
      inputLine = new BufferedReader(new InputStreamReader(System.in));
      os = new PrintStream(clientSocket.getOutputStream());
      is = new DataInputStream(clientSocket.getInputStream());
    } catch (UnknownHostException e) {
      System.err.println("Errore! L'host " + host + ": sconosciuto");
    } catch (IOException e) {
      System.err.println("Errore! Eccezione I/O sull'host: "
          + host);
    }
    
    if (clientSocket != null && os != null && is != null) {
      try {

     
        new Thread(new Client()).start();
        while (!closed) {
            
          String text = inputLine.readLine();
            
          os.println(text);
          
          if (text.contains("/start")) {
              System.out.println("hai iniziato il gioco");
          }
          
        }
       

        os.close();
        is.close();
        clientSocket.close();
      } catch (IOException e) {
        System.err.println("IOException:  " + e);
      }
    }
  }

  
  @Override
  public void run() {
      
    String responseLine;
    
    try {
      responseLine = is.readLine();
      while (responseLine != null) {
        System.out.println(responseLine);
        if (responseLine.contains("*** Ciao"))
          break;
        
        
      }
      closed = true;
    } catch (IOException e) {
      System.err.println("IOException:  " + e);
    }
    
  }
}
