package hu.api.feladat;

import static org.junit.jupiter.api.Assertions.*;

import java.math.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;
import org.springframework.boot.test.web.client.*;
import org.springframework.boot.test.web.server.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FeladatApplicationTests {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;


    @Test
    public void testMelyiknapWithMissingDATUMParameter() throws Exception {
        assertEquals("Hiányzó 'DATUM' paraméter!", doRequest("/melyiknap", Result.Error.class).message());
    }

    @Test
    public void testMelyiknapWithInvalidDateValue() throws Exception {
        assertEquals("Hibás/érvénytelen dátum!", doRequest("/melyiknap?DATUM=2020-05-", Result.Error.class).message());
    }

    @Test
    public void testMelyiknapWithCorrectDateValue() throws Exception {
        assertEquals("péntek", doRequest("/melyiknap?DATUM=2025-05-23", Result.Ok.class).value());
    }


    @Test
    public void testPrimszamWithMissingSZAMParameter() throws Exception {
        assertEquals("Hiányzó 'SZAM' paraméter!", doRequest("/primszam", Result.Error.class).message());
    }

    @Test
    public void testPrimszamWithNonNumberValue() throws Exception {
        assertEquals("Hibás/érvénytelen érték!", doRequest("/primszam?SZAM=nemszam", Result.Error.class).message());
    }

    @Test
    public void testPrimszamWithOutOfRangeValues() throws Exception {
        assertEquals("Hibás/érvénytelen érték!", doRequest("/primszam?SZAM=" + BigInteger.valueOf(Long.MAX_VALUE).add(BigInteger.ONE).toString(), Result.Error.class).message());
        assertEquals("Hibás/érvénytelen érték!", doRequest("/primszam?SZAM=" + BigInteger.valueOf(Long.MIN_VALUE).subtract(BigInteger.ONE).toString(), Result.Error.class).message());
    }

    @Test
    public void testPrimszamWithAcceptableValues() throws Exception {
        assertEquals(false, doRequest("/primszam?SZAM=1", Result.Ok.class).value());
        assertEquals(false, doRequest("/primszam?SZAM=0", Result.Ok.class).value());
        assertEquals(false, doRequest("/primszam?SZAM=-1", Result.Ok.class).value());

        assertEquals(true, doRequest("/primszam?SZAM=2", Result.Ok.class).value());
        assertEquals(true, doRequest("/primszam?SZAM=3", Result.Ok.class).value());
        assertEquals(true, doRequest("/primszam?SZAM=100003", Result.Ok.class).value());

        assertEquals(false, doRequest("/primszam?SZAM=4", Result.Ok.class).value());
        assertEquals(false, doRequest("/primszam?SZAM=100000", Result.Ok.class).value());

        assertEquals(false, doRequest("/primszam?SZAM=" + Long.MAX_VALUE, Result.Ok.class).value());
        assertEquals(false, doRequest("/primszam?SZAM=" + Long.MIN_VALUE, Result.Ok.class).value());
    }


    @Test
    public void testMaganhangzoWithMissingSZOVEGParameter() throws Exception {
        assertEquals("Hiányzó 'SZOVEG' paraméter!", doRequest("/maganhangzo", Result.Error.class).message());
    }

    @Test
    public void testMaganhangzoWithAcceptableValues() throws Exception {
        assertEquals(0, doRequest("/maganhangzo?SZOVEG=mmmmmmmm", Result.Ok.class).value());
        assertEquals(8, doRequest("/maganhangzo?SZOVEG=aaaaaaaa", Result.Ok.class).value());
        assertEquals(5, doRequest("/maganhangzo?SZOVEG=Az%20alma%20piros.", Result.Ok.class).value());
    }


    private<T> T doRequest(String endpoint, Class<T> resultType) {
        return restTemplate.getForObject("http://localhost:" + port + endpoint, resultType);
    }
}
