package com.reactive.playground.demo.context;

import com.reactive.playground.demo.utils.Logger;
import com.reactive.playground.demo.utils.TimeUtils;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;
import reactor.util.context.Context;

public class ContextGetOrDefault {

    public static void main(String[] args) {
        String key1 = "id";
        String key2 = "name";

        Mono.deferContextual(ctx ->
                        Mono.just("ID: " + " " + ctx.get(key1) + ", "
                                + "Name: " + ctx.get(key2) + ", "
                                + "Job: " + ctx.getOrDefault("job", "Software Engineer"))
                )
                .publishOn(Schedulers.parallel())
                .contextWrite(Context.of(key1, "itVillage", key2, "Kevin"))
                .subscribe(Logger::onNext);

        TimeUtils.sleep(100L);
    }

}
