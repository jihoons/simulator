package pe.jihoons.simulator.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.Random;

@Slf4j
@RequiredArgsConstructor
@RestController
public class Controller {
    private final Random random = new Random(System.nanoTime());
    private final Logger logger = LoggerFactory.getLogger("SIMULATOR_LOGGER");
    @GetMapping("/echo/{message}")
    public Mono<String> getName(@PathVariable String message) {
        return Mono.delay(Duration.ofMillis(40))
                .doOnNext(along -> logger.info(message))
                .map(aLong -> {
                    if (random.nextInt(100) < 50)
                        throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
                    return message;
                });
    }
}
