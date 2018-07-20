package Http;

import java.io.InputStream;
import java.net.Socket;
import java.util.StringTokenizer;

public class HttpUtils {
	
	public static String getRequest(Socket sock) throws Exception {
		
		StringBuilder requestString = new StringBuilder();
		
		byte [] requestBytes = new byte[40000];
		
		InputStream in = sock.getInputStream();
		int n = in.read(requestBytes);

		String requestPart1 = null;
		
		if (n != -1) {
			requestPart1 = new String(requestBytes, 0, n);
			requestString.append(requestPart1);
		}
		
		if (requestPart1 != null && requestPart1.contains("multipart/form-data")) {
			n = in.read(requestBytes);
			if (n != -1) {
				requestString.append( new String(requestBytes, 0, n));
			}
		}
		
		return requestString.toString();
	}

	public static String getRequestUri(String request) {
		StringTokenizer stk = new StringTokenizer(request);
		String method = stk.hasMoreTokens() ? stk.nextToken() : null;
		String uri = stk.hasMoreTokens() ? stk.nextToken() : null;



		return uri;



	}

	public static Boolean checkReferer(String request) {
		StringTokenizer stk = new StringTokenizer(request);
		while (stk.hasMoreTokens())
		{
			if(stk.nextToken().equals("Referer:"))
			{
				return true;
			}

		}
		return false;
	}

	}

