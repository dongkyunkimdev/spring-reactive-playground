package com.reactive.playground.demo.publisher;

import com.reactive.playground.demo.utils.Logger;
import reactor.core.publisher.Flux;

public class FluxConcatMultipleFlux {
    public static void main(String[] args) {
        Flux.concat(
                Flux.just("Venus"),
                Flux.just("Earth"),
                Flux.just("Mars"))
            .collectList()
            .subscribe(planetList -> Logger.info("# Solar System: {}", planetList));
    }
}
