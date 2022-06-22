package com.example.demo;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class FluxTest {

    @Test
    public void testFlux() {
        Flux<String> fluxStr = Flux.just("Hello","Project","Reactor");
        fluxStr.subscribe(System.out::println);
    }

    @Test
    public void testFluxLog() {
        Flux<String> fluxStr = Flux.just("Hello","Project","Reactor").log();
        fluxStr.subscribe(System.out::println);
    }

    @Test
    public void testFluxErrorLog() {
        Flux<?> fluxStr = Flux.just("Hello","Project","Reactor").concatWith(Flux.error(new RuntimeException("Exception occurred"))).log();
        fluxStr.subscribe(System.out::println, (e) -> System.out.println(e.getLocalizedMessage()));
    }
}
