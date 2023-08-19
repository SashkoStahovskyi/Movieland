package com.stahovskyi.movieland.service;

import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

public interface MovieHandler {

    Mono<ServerResponse> getMovies(ServerRequest serverRequest);


}
