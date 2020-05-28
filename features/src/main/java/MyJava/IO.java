package MyJava;

import com.github.tomakehurst.wiremock.WireMockServer;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.configureFor;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class IO {
    private static final String BAELDUNG_PATH = "/baeldung";

    private static WireMockServer wireMockServer = new WireMockServer();

    public static void main(String[] args) {
        my();

        
        
    }

    private static void my(){
        wireMockServer.start();
        System.out.println("Hey Wiremock - 1");
        configureFor("localhost", 8080);
        System.out.println("Hey Wiremock - 2");
        stubFor(get(urlEqualTo("/aashay")).willReturn(aResponse().withBody("Introduction to WireMock")));
        System.out.println("Hey Wiremock - 3");
        // try {
        //     Thread.sleep(30000);
        // } catch (InterruptedException e) {
        //     e.printStackTrace();
        // }
        test();
        long time = System.currentTimeMillis();
        javaIO();
        System.out.println(System.currentTimeMillis() - time);
        time = System.currentTimeMillis();
        javaNIO();
        System.out.println(System.currentTimeMillis() - time);

        
        wireMockServer.stop();
        System.out.println("Hey Wiremock - 4");
    }
    
    public static void baeldung(){
        wireMockServer.start();
        
        configureFor("localhost", 8080);
        stubFor(get(urlEqualTo(BAELDUNG_PATH)).willReturn(aResponse().withBody("Welcome to Baeldung!")));
        try {
            Thread.sleep(30000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        wireMockServer.stop();
    }
    
    public static void test() {
        CloseableHttpClient client = HttpClients.createDefault();
        HttpGet get = new HttpGet("http://localhost:8080/aashay");
        HttpResponse response = null;
        try {
            response = client.execute(get);
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(response != null){
            String res = response.toString();
            try {
                res = convertResponseToString(response);
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println(res);
        }
    }
    private static String convertResponseToString(HttpResponse response) throws IOException {
        InputStream responseStream = response.getEntity().getContent();
        Scanner scanner = new Scanner(responseStream, "UTF-8");
        String responseString = scanner.useDelimiter("\n").next();
        scanner.close();
        return responseString;
    }
    
    public static void javaIO(){
        try {
            Socket socket = new Socket("localhost", 8080);
            OutputStream oStream = socket.getOutputStream();
            PrintWriter oWriter = new PrintWriter(oStream);
            oWriter.print("GET /aashay  HTTP/1.0\r\n\r\n");
            oWriter.flush();
            InputStream serverInput = socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(serverInput));
            StringBuilder ourStore = new StringBuilder();
            for (String line; (line = reader.readLine()) != null;) {
            ourStore.append(line);
            ourStore.append(System.lineSeparator());
            }
            System.out.println(ourStore.toString());
            reader.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void javaNIO(){
        StringBuilder ourStore = new StringBuilder();
        
        try {
            InetSocketAddress remote = new InetSocketAddress("localhost", 8080);
            SocketChannel socketChannel = SocketChannel.open(remote);
            socketChannel.write(StandardCharsets.UTF_8.encode("GET " + "/aashay" + " HTTP/1.0\r\n\r\n"));
            ByteBuffer buffer = ByteBuffer.allocate(800);
            CharsetDecoder decoder = StandardCharsets.UTF_8.newDecoder();
            CharBuffer charBuffer = CharBuffer.allocate(8192);
            socketChannel.read(buffer);
            while (socketChannel.read(buffer) != -1 || buffer.position() > 0) {
                buffer.flip();
                storeBufferContents(buffer, charBuffer, decoder, ourStore);
                buffer.compact();
            }
            socketChannel.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(ourStore.toString());
        
    }
    static void storeBufferContents(ByteBuffer byteBuffer, CharBuffer charBuffer, 
        CharsetDecoder charsetDecoder, StringBuilder ourStore) {
        charsetDecoder.decode(byteBuffer, charBuffer, true);
        charBuffer.flip();
        ourStore.append(charBuffer);
        charBuffer.clear();
    }
}