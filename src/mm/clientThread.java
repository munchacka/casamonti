package mm;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.PrintStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;


/**
 *
 * @author casam
 */
class clientThread extends Thread {

  private DataInputStream is = null;
  private PrintStream os = null; // comunicazione tra client
  
  private Socket clientSocket = null;
  private final ArrayList<clientThread> threads;
  private int c;
  private Strikeball mm;
  
  

  public clientThread(Socket clientSocket, ArrayList<clientThread> threads, int c, 
          Strikeball mm) {
    this.clientSocket = clientSocket;
    this.threads = threads;
    this.mm = mm;
    this.c = c;
    

  }

  @Override
  public void run() {
    try {
        
      // Creazione stream i&o per il client
      is = new DataInputStream(clientSocket.getInputStream());
      os = new PrintStream(clientSocket.getOutputStream());
      int tent = 0;
      
      os.println("Nome: ");
      String name = is.readLine().trim();
      os.println("Benvenuto " + name
          + " su Strikeball.\nPer iniziare a giocare: /start\nPer chattare: /chat\nPer uscire: /quit");
      
      for (clientThread t : threads) {
            if (t != null && t != this) {
                t.os.println( name + " è entrato");
            }
        }
      
      while (true) {
          
        String startLine = is.readLine();
                     
        if (startLine.startsWith("/quit")) {
          break;
        } 
        
        if (startLine.startsWith("/start")) {
          String seq = "0 0 0 0";
          os.println( " GAME HAS STARTED \n La tua sequenza: " + seq);
          seq = is.readLine();

            
        }
        
        if (startLine.startsWith("/chat")) {
            String line = is.readLine();
           // manda il msg a tutti i client connessi
            for (clientThread t : threads) {
                if (t != null) 
                    t.os.println("[" + name + "]: " + line); 
            } 
        }
      }
      
      for (clientThread t : threads) {
            if (t != null && t != this) 
                t.os.println( name + " e' uscito");
      } 
      
      os.println("*** Ciao " + name + " ***");
   
      // Una volta finito setta il thread a null in modo che un nuovo client puo' essere accettato
      for (clientThread t : threads) {
            if (t == this) 
                t = null;
      }

      // chiude tutto why not
      is.close();
      os.close();
      clientSocket.close();
    } catch (IOException e) {
        System.out.println(e);
    }
  }
}
