package com.reactive.playground.demo.scheduler;

import com.reactive.playground.demo.utils.Logger;
import reactor.core.publisher.Flux;

public class ParallelWithOutScheduler {

    public static void main(String[] args) {
        Flux.fromArray(new Integer[]{1, 3, 5, 7, 9, 11, 13, 15})
                .parallel()
                .subscribe(Logger::onNext);
    }

}
