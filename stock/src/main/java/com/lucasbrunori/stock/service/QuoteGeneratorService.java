package com.lucasbrunori.stock.service;

import com.lucasbrunori.stock.model.Quote;
import java.time.Duration;
import reactor.core.publisher.Flux;

public interface QuoteGeneratorService {

  Flux<Quote> fetchQuoteStream(Duration period);

}
