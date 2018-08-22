import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.URI;
import java.net.URLDecoder;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Main {
    static final int port = 8000;

    public static void main(String[] args) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);
        System.out.println("server started at " + port);
        server.createContext("/", new RootHandler());
        server.createContext("/image", new ImageHandler());
        server.setExecutor(null);
        System.out.println("server adress " + server.getAddress());
        server.start();
    }
    public static class RootHandler implements HttpHandler {

        @Override
        public void handle(HttpExchange he) throws IOException {
            System.out.println("I got here");
            File file = new File(Main.class.getResource("index.html").getPath());
            System.out.println("Loaded file");
            byte[] array = new byte[(int) file.length()];
            System.out.println("Made byte array");
            FileInputStream fis = new FileInputStream(file);
            System.out.println("Fileinputstream done");
            BufferedInputStream bis = new BufferedInputStream(fis);
            System.out.println("Bufferedoutputstream done");
            System.out.println("bytes in file " + file.length() + "bytes read " + bis.read(array, 0, array.length));

            he.sendResponseHeaders(200, file.length());
            OutputStream os = he.getResponseBody();
            os.write(array, 0, array.length);
            os.close();
        }
    }

    public static class ImageHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange ex) throws IOException {
            System.out.println("I GOT HERE");
            var query = queryToMap(ex.getRequestURI().getRawQuery());
            System.out.println(Arrays.toString(query.values().toArray()));
        }
    }

    public static Map<String, String> queryToMap(String query){
        Map<String, String> result = new HashMap<String, String>();
        for (String param : query.split("&")) {
            String pair[] = param.split("=");
            if (pair.length>1) {
                result.put(pair[0], pair[1]);
            }else{
                result.put(pair[0], "");
            }
        }
        return result;
    }

    public static void parseQuery(String query, Map<String, Object> parameters) throws UnsupportedEncodingException {

        if (query != null) {
            String pairs[] = query.split("[&]");
            for (String pair : pairs) {
                String param[] = pair.split("[=]");
                String key = null;
                String value = null;
                if (param.length > 0) {
                    key = URLDecoder.decode(param[0],
                            System.getProperty("file.encoding"));
                }

                if (param.length > 1) {
                    value = URLDecoder.decode(param[1],
                            System.getProperty("file.encoding"));
                }

                if (parameters.containsKey(key)) {
                    Object obj = parameters.get(key);
                    if (obj instanceof List<?>) {
                        List<String> values = (List<String>) obj;
                        values.add(value);

                    } else if (obj instanceof String) {
                        List<String> values = new ArrayList<String>();
                        values.add((String) obj);
                        values.add(value);
                        parameters.put(key, values);
                    }
                } else {
                    parameters.put(key, value);
                }
            }
        }
    }
}

