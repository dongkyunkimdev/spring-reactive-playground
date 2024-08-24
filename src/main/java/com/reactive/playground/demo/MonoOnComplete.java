package com.reactive.playground.demo;

import com.reactive.playground.demo.utils.Logger;
import reactor.core.publisher.Mono;

public class MonoOnComplete {
    public static void main(String[] args) {
        Mono.empty()
                .subscribe(
                        data -> Logger.info("# emitted data: {}", data),
                        error -> {},
                        () -> Logger.info("# emitted onComplete signal")
                );
    }
}
