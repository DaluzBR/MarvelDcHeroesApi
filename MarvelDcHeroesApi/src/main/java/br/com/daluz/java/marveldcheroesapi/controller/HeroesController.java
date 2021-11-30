package br.com.daluz.java.marveldcheroesapi.controller;

import br.com.daluz.java.marveldcheroesapi.constans.HeroesConstants;
import br.com.daluz.java.marveldcheroesapi.document.Heroes;
import br.com.daluz.java.marveldcheroesapi.repository.HeroesRepository;
import br.com.daluz.java.marveldcheroesapi.service.HeroesService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController // Controller da API.
@NoArgsConstructor
//@Slf4j // Reporte de logs a cada requisição.
public class HeroesController {

    @Autowired
    HeroesService heroesService;
    @Autowired
    HeroesRepository heroesRepository;

    private static final Logger log = LoggerFactory.getLogger(HeroesController.class);

    public HeroesController(HeroesService heroesService, HeroesRepository heroesRepository) {
        this.heroesService = heroesService;
        this.heroesRepository = heroesRepository;
    }

    @GetMapping(HeroesConstants.HEROES_ENDPOINT_LOCAL)
    @ResponseStatus(HttpStatus.OK)
    public Flux<Heroes> getAllHeroes(){
        log.info("Requesting the list of all heroes.");
        return  heroesService.findAllHeroes();
    }

    @GetMapping(HeroesConstants.HEROES_ENDPOINT_LOCAL_ID)
    public Mono<ResponseEntity<Heroes>> getHeroById(@PathVariable String id){
        log.info("Requesting the hero with id {}.", id);
        return  heroesService.findHeroById(id)
                .map((item) -> new ResponseEntity<>(item, HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping(HeroesConstants.HEROES_ENDPOINT_LOCAL)
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Heroes> saveHero(@RequestBody Heroes heroes) {
        log.info("A new Hero was created.");
        return heroesService.saveHero(heroes);

    }

    @DeleteMapping(HeroesConstants.HEROES_ENDPOINT_LOCAL_ID)
    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    public Mono<HttpStatus> deleteHeroById(@PathVariable String id) {
        heroesService.deleteHeroById(id);
        log.info("Deleting the hero with id {}", id);
        return Mono.just(HttpStatus.NOT_FOUND);
    }

}
