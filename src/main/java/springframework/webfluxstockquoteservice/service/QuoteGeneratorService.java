package springframework.webfluxstockquoteservice.service;

import springframework.webfluxstockquoteservice.model.Quote;
import reactor.core.publisher.Flux;

import java.time.Duration;

/**
 * Created by VenD-Sehrish on 09/09/18.
 */
public interface QuoteGeneratorService {

    Flux<Quote> fetchQuoteStream(Duration period);
}
