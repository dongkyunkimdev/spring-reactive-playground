package com.reactive.playground.demo.scheduler;

import com.reactive.playground.demo.utils.Logger;
import com.reactive.playground.demo.utils.TimeUtils;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

public class NewBoundedElasticScheduler {

    public static void main(String[] args) {
        Scheduler scheduler = Schedulers.newBoundedElastic(2, 2, "I/O-Thread");
        Mono<Integer> mono =
                Mono
                        .just(1)
                        .subscribeOn(scheduler);

        Logger.info("# Start");

        mono.subscribe(data -> {
            Logger.onNext("subscribe 1 doing", data);
            TimeUtils.sleep(3000L);
            Logger.onNext("subscribe 1 done", data);
        });

        mono.subscribe(data -> {
            Logger.onNext("subscribe 2 doing", data);
            TimeUtils.sleep(3000L);
            Logger.onNext("subscribe 2 done", data);
        });

        mono.subscribe(data -> {
            Logger.onNext("subscribe 3 doing", data);
        });

        mono.subscribe(data -> {
            Logger.onNext("subscribe 4 doing", data);
        });

        mono.subscribe(data -> {
            Logger.onNext("subscribe 5 doing", data);
        });

        mono.subscribe(data -> {
            Logger.onNext("subscribe 6 doing", data);
        });

        /*
          Scheduler에 할당된 모든 Thread(2개)가 사용중이고, 각 Thread의 모든 Quere(2개)가 이미 점유된 상태에서 추가로 subscribe 하게 되면 Exception 발생
          Caused by: reactor.core.Exceptions$ReactorRejectedExecutionException: Task capacity of bounded elastic scheduler reached while scheduling 1 tasks (3/2)

          mono.subscribe(data -> {
                      Logger.onNext("subscribe 7 doing", data);
                  });
         */

        TimeUtils.sleep(4000L);
        scheduler.dispose();
    }

}
