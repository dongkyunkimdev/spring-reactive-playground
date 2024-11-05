package com.reactive.playground.demo.context;

import com.reactive.playground.demo.utils.Logger;
import com.reactive.playground.demo.utils.TimeUtils;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

public class ContextMultipleSubscriber {

    public static void main(String[] args) {
        String key1 = "id";
        Mono<String> mono = Mono.deferContextual(ctx ->
                        Mono.just("ID: " + " " + ctx.get(key1))
                )
                .publishOn(Schedulers.parallel());

        mono.contextWrite(context -> context.put(key1, "itVillage"))
                .subscribe(data -> Logger.onNext("subscriber 1", data));

        mono.contextWrite(context -> context.put(key1, "itWorld"))
                .subscribe(data -> Logger.onNext("subscriber 2", data));

        TimeUtils.sleep(100L);
    }

}
