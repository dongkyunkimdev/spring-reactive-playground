package com.reactive.playground.demo.scheduler;

import com.reactive.playground.demo.utils.Logger;
import reactor.core.publisher.Flux;

public class WithOutSchedulerOperator {

    public static void main(String[] args) {
        Flux.fromArray(new Integer[]{1, 3, 5, 7})
                .filter(data -> data > 3)
                .map(data -> data * 10)
                .subscribe(Logger::onNext);
    }

}
