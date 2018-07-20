package Http;

import java.io.*;
import java.net.Socket;

public class RequestHandler {

	private static final int BUFFER_SIZE = 4096;


	public void handleRequest(Socket sock) {
		OutputStream out = null;
		int value;


		try {

			String request = RequestMethod.getRequest(sock);
			System.out.println(request);
			String method = RequestMethod.getMethod(request);

			switch (method) {
				case "GET":
					String uri = RequestMethod.getRequestUri(request);
					System.out.println("Received request for - " + uri);
					boolean referer = RequestMethod.checkReferer(request);

					ResponeMethod responeMethod = new ResponeMethod();
					responeMethod.methodrespone(sock, uri);
					break;
				case "POST":

					break;
				case "PUT":

					break;
				case "HEAD":

					break;
				case "DELETE":

					break;

				default:

					break;


			}




		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
