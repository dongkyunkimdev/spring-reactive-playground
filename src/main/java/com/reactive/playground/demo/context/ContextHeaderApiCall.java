package com.reactive.playground.demo.context;

import com.reactive.playground.demo.utils.Logger;
import reactor.core.publisher.Mono;
import reactor.util.context.Context;

public class ContextHeaderApiCall {

    public static final String HEADER_NAME_AUTH_TOKEN = "authToken";

    public static void main(String[] args) {
        Mono<String> mono =
                postBook(Mono.just(
                        new Book("abcd-1111-3533-2809"
                                , "Reactor's Bible"
                                , "Kevin"))
                )
                        .contextWrite(Context.of(HEADER_NAME_AUTH_TOKEN, "eyJhbGciOiJIUzUxMiJ9.eyJzdWI"));

        mono.subscribe(Logger::onNext);
    }

    private static Mono<String> postBook(Mono<Book> book) {
        return Mono.zip(book, Mono.deferContextual(ctx -> Mono.just(ctx.get(HEADER_NAME_AUTH_TOKEN))))
                .flatMap(tuple -> Mono.just(tuple))  // 외부 API 서버로 HTTP POST request를 전송한다고 가정
                .flatMap(tuple -> {
                    String response = "POST the book(" + tuple.getT1().getBookName() +
                            "," + tuple.getT1().getAuthor() + ") with token: " +
                            tuple.getT2();
                    return Mono.just(response); // HTTP response를 수신했다고 가정
                });
    }

}
