package Http;

import java.io.InputStream;
import java.net.Socket;
import java.util.*;

public class RequestMethod {

    public static final String GET_REQUEST_TYPE = "GET";

    public static final String POST_REQUEST_TYPE = "POST";

    public static final String HEAD_REQUEST_TYPE = "HEAD";

    public static final String DELETE_REQUEST_TYPE = "DELETE";

    public static final String PUT_REQUEST_TYPE = "PUT";


    private Map<String, String> params = new HashMap<>();

    private List<String> splitPath = new ArrayList<>();

    private String fullPath;

    public List<String> getSplitPath() {
        return splitPath;
    }



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

    public static String getMethod(String request) {
        StringTokenizer stk = new StringTokenizer(request);
        String method = stk.hasMoreTokens() ? stk.nextToken() : null;

        return method;
    }


    public static String getRequestUri
            (String request) {
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
