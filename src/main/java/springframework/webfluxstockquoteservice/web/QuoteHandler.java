package springframework.webfluxstockquoteservice.web;

import springframework.webfluxstockquoteservice.model.Quote;
import springframework.webfluxstockquoteservice.service.QuoteGeneratorService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.time.Duration;

import static org.springframework.web.reactive.function.server.ServerResponse.ok;


/**
 * Created by VenD-Sehrish on 09/09/18.
 */
@Component
public class QuoteHandler {
    private final QuoteGeneratorService quoteGeneratorService;

    public QuoteHandler(QuoteGeneratorService quoteGeneratorService) {
        this.quoteGeneratorService = quoteGeneratorService;
    }

    public Mono<ServerResponse> fetchQuotes(ServerRequest request) {
        int size = Integer.parseInt(request.queryParam("size").orElse("10"));

        return ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(this.quoteGeneratorService.fetchQuoteStream(Duration.ofMillis(100))
                        .take(size), Quote.class);
    }

    public Mono<ServerResponse> streamQuotes(ServerRequest request){
        return ok().contentType(MediaType.APPLICATION_STREAM_JSON)
                .body(this.quoteGeneratorService.fetchQuoteStream(Duration.ofMillis(200)), Quote.class);
    }
}
