package br.unibh.gqs.market_solution;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;

import br.unibh.gqs.market_solution.model.Cliente;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.*;

@SpringBootTest(webEnvironment = RANDOM_PORT)
public class TesteRest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void test01()
    {
        assertTrue(
                this.restTemplate
                        .getForObject("http://localhost:" + port + "/cliente/1", Cliente.class)
                        .getCpf().equals("11111111111"));
    }

    @Test
    public void test02() {
        assertTrue(
                this.restTemplate
                        .getForObject("http://localhost:" + port + "/produtos", List.class).size() == 3);
    }
}

