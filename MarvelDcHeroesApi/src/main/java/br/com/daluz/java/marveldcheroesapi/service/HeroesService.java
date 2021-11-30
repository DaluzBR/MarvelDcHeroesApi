package br.com.daluz.java.marveldcheroesapi.service;

import br.com.daluz.java.marveldcheroesapi.document.Heroes;
import br.com.daluz.java.marveldcheroesapi.repository.HeroesRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class HeroesService {

    private final HeroesRepository heroesRepository;

    public HeroesService(HeroesRepository heroesRepositories) {
        this.heroesRepository = heroesRepositories;
    }

    public Flux<Heroes> findAllHeroes(){
        return  Flux.fromIterable(this.heroesRepository.findAll());
    }

    public Mono <Heroes> findHeroById(String id){
        return Mono.justOrEmpty(this.heroesRepository.findById(id));
    }

    public Mono <Heroes> saveHero(Heroes heroes){
        return  Mono.justOrEmpty(this.heroesRepository.save(heroes));
    }

    public Mono <Boolean> deleteHeroById(String id){
        heroesRepository.deleteById(id);
        return  Mono.just(true);
    }
}
