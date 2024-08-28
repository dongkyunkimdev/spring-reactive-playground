package com.reactive.playground.demo.sinks;

import com.reactive.playground.demo.utils.Logger;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

import static reactor.core.publisher.Sinks.EmitFailureHandler.FAIL_FAST;

public class SinkManyUnicast {
    public static void main(String[] args) {
        Sinks.Many<Integer> unicastSink = Sinks.many().unicast().onBackpressureBuffer();
        Flux<Integer> fluxView = unicastSink.asFlux();

        unicastSink.emitNext(1, FAIL_FAST);
        unicastSink.emitNext(2, FAIL_FAST);
        fluxView.subscribe(data -> Logger.onNext("Subscriber1", data));

        unicastSink.emitNext(3, FAIL_FAST);
        fluxView.subscribe(data -> Logger.onNext("Subscriber2", data));
    }
}
