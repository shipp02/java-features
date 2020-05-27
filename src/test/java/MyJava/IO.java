package MyJava;

import com.github.tomakehurst.wiremock.junit.WireMockRule;

import org.junit.Before;
import org.junit.Rule;

public class IO {
    @Rule WireMockRule wireMockRule = new WireMockRule(8080);
    
    @Before
    public void setup(){
    }

}
