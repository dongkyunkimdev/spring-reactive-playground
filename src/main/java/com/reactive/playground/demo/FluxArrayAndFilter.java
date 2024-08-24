package com.reactive.playground.demo;

import com.reactive.playground.demo.utils.Logger;
import reactor.core.publisher.Flux;

public class FluxArrayAndFilter {
    public static void main(String[] args) {
        Flux.fromArray(new Integer[]{3, 6, 7, 9})
                .filter(num -> num > 6)
                .map(num -> num * 2)
                .subscribe(multiply -> Logger.info("# multiply: {}", multiply));
    }
}
