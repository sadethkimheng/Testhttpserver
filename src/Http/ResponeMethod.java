package Http;

import java.io.*;
import java.net.Socket;

public class ResponeMethod {

    int value;
    ResourceLoader resourceLoader = new ResourceLoader();


    OutputStream out = null;

   public void methodrespone(Socket sock, String uri) throws IOException {

    System.out.println("Sending response ");

        out = sock.getOutputStream();
       InputStream input = resourceLoader.getResource(uri);
       InputStreamReader inet = new InputStreamReader(input);
       BufferedReader in = new BufferedReader(inet);

        PrintWriter pw = new PrintWriter(out);
				pw.println("HTTP/1.0 200 OK");
                pw.println("Content-Type: text/html");
                pw.println("");


       pw.println();
				pw.flush();

       String line;
       while ((line = in.readLine()) != null) {
           if (line.length() == 0)
               break;
           pw.print(line + "\r\n");
       }







       in.close();
       pw.close();
   }

    }

