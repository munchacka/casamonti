package mm;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.util.ArrayList;
import java.io.IOException;
import java.net.Socket;
import java.net.ServerSocket;

/*
 * 
 * @author casam
 */
public class Server {

  // Arraylist che verra' condiviso da tutti i thread per il passaggio dei dati per questo 'static'
  private static final ArrayList<clientThread> threads = new ArrayList<clientThread>();
  private static ServerSocket serverSocket = null;
  private static Socket clientSocket = null;
  private static int c = 0;                            // per accedere ai client 
  private static Strikeball mm;
  

  

  public static void main(String args[]) {
      
    mm = new Strikeball();

    // Apre il server nella porta 2222
    try {
      serverSocket = new ServerSocket(2222);
    } catch (IOException e) {
      System.out.println(e);
    }

    // Una volta stabilita la connessione viene passato il socket a un nuovo thread che si occupera' della gestione
    while (true) {
        
      try {
          
        clientSocket = serverSocket.accept();
        
        
       
        
        
        clientThread t = new clientThread(clientSocket, threads, c, mm);
        
      
        
        threads.add(t);
        t.start();
        c++;
        
      } catch (IOException e) {
        System.out.println(e);
      }
    }
  }
}
