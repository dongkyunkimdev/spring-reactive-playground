package com.reactive.playground.demo.context;

import com.reactive.playground.demo.utils.Logger;
import com.reactive.playground.demo.utils.TimeUtils;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

public class ContextInnerSequence {

    public static void main(String[] args) {
        String key1 = "id";
        Mono.just("Kevin")
                .transformDeferredContextual((stringMono, contextView) -> contextView.get("job"))
                .flatMap(name -> Mono.deferContextual(ctx ->
                                Mono.just(ctx.get(key1) + ", " + name)
                                        .transformDeferredContextual((mono, innerCtx) ->
                                                mono.map(data -> data + ", " + innerCtx.get("job"))
                                        )
                                        .contextWrite(context -> context.put("job", "Software Engineer"))
                        )
                )
                .publishOn(Schedulers.parallel())
                .contextWrite(context -> context.put(key1, "itVillage"))
                .subscribe(Logger::onNext);

        TimeUtils.sleep(100L);
    }

}
