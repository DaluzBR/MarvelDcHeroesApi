package br.com.daluz.java.marveldcheroesapi;

import br.com.daluz.java.marveldcheroesapi.constans.HeroesConstants;
import br.com.daluz.java.marveldcheroesapi.repository.HeroesRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

@RunWith(SpringRunner.class)
@DirtiesContext
@AutoConfigureWebTestClient
@SpringBootTest
class MarvelDcHeroesApiApplicationTests {

    @Autowired
    WebTestClient webTestClient;

    @Autowired
    HeroesRepository heroesRepository;

    @Test
    public void getHeroById() {
        webTestClient.get().uri(HeroesConstants.HEROES_ENDPOINT_LOCAL_ID, "1")
                .exchange()
                .expectStatus().isOk()
                .expectBody();
    }

    @Test
    public void getHeroByIdNotFound() {
        webTestClient.get().uri(HeroesConstants.HEROES_ENDPOINT_LOCAL_ID, "-99999")
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    public void deleteHeroById() {
        webTestClient.get().uri(HeroesConstants.HEROES_ENDPOINT_LOCAL_ID, "1")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isNotFound()
                .expectBody(Void.class);
    }

}
