package MyJava;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.configureFor;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;

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
        try {
            Thread.sleep(30000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
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
}