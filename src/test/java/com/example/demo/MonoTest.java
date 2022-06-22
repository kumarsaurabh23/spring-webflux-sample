package com.example.demo;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;

public class MonoTest {

    @Test
    public void testMono() {
        Mono<String> monoStr = Mono.just("mono-sample");
        monoStr.subscribe(System.out::println);
    }

    @Test
    public void testMonoLog() {
        Mono<String> monoStr = Mono.just("mono-sample-log").log();
        monoStr.subscribe(System.out::println);
    }

    @Test
    public void testMonoErrorLog() {
        Mono<?> monoStr = Mono.just("mono-error-log").then(Mono.error(new RuntimeException("Exception occurred"))).log();
        monoStr.subscribe(System.out::println, (e) -> System.out.println(e.getLocalizedMessage()));
    }
}
