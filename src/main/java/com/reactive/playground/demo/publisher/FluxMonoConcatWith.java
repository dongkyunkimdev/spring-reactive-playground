package com.reactive.playground.demo.publisher;

import com.reactive.playground.demo.utils.Logger;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class FluxMonoConcatWith {
    public static void main(String[] args) {
        Flux<Object> flux =
                Mono.justOrEmpty(null)
                        .concatWith(Mono.justOrEmpty("Jobs"));
        flux.subscribe(data -> Logger.info("# result: {}", data));
    }
}
