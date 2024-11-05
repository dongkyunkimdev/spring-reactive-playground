package com.reactive.playground.demo.context;

import com.reactive.playground.demo.utils.Logger;
import com.reactive.playground.demo.utils.TimeUtils;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

public class ContextOverwrite {

    public static void main(String[] args) {
        String key1 = "id";
        Mono.deferContextual(ctx ->
                        Mono.just("ID: " + " " + ctx.get(key1))
                )
                .publishOn(Schedulers.parallel())
                .contextWrite(context -> context.put(key1, "itWorld"))
                .contextWrite(context -> context.put(key1, "itVillage"))
                .subscribe(Logger::onNext);

        TimeUtils.sleep(100L);
    }

}
