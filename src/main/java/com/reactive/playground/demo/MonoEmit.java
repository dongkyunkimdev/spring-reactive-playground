package com.reactive.playground.demo;

import com.reactive.playground.demo.utils.Logger;
import reactor.core.publisher.Mono;

public class MonoEmit {
    public static void main(String[] args) {
        Mono.just("Hello Reactor!")
                .subscribe(data -> Logger.info("# emitted data: {}", data));
    }
}
