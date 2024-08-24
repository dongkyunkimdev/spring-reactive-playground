package com.reactive.playground.demo;

import com.reactive.playground.demo.utils.Logger;
import reactor.core.publisher.Flux;

public class FluxEmit {
    public static void main(String[] args) {
        Flux.just(6, 9, 13)
                .map(num -> num % 2)
                .subscribe(remainder -> Logger.info("# remainder: {}", remainder));
    }
}
