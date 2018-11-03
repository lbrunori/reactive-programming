package com.lucasbrunori.stock;

import com.lucasbrunori.stock.model.Quote;
import com.lucasbrunori.stock.service.QuoteGeneratorServiceImpl;
import java.time.Duration;
import java.util.concurrent.CountDownLatch;
import java.util.function.Consumer;
import org.junit.Before;
import org.junit.Test;
import reactor.core.publisher.Flux;

public class QuoteGeneratorServiceImplTest {

  private QuoteGeneratorServiceImpl quoteGeneratorService = new QuoteGeneratorServiceImpl();

  @Before
  public void setUp() {

  }

  @Test
  public void fetchQuoteStream() throws Exception {

    Flux<Quote> quoteFlux = quoteGeneratorService.fetchQuoteStream(Duration.ofMillis(1));

    quoteFlux.take(22000)
        .subscribe(System.out::println);
  }

  @Test
  public void fetchQuoteStreamCountDown() throws Exception {
    Flux<Quote> quoteFlux = quoteGeneratorService.fetchQuoteStream(Duration.ofMillis(100L));

    Consumer<Quote> println = System.out::println;

    Consumer<Throwable> errorHandler = e -> System.out.println("Some Error Ocurred");

    CountDownLatch countDownLatch = new CountDownLatch(1);

    Runnable allDone = () -> countDownLatch.countDown();

    quoteFlux.take(30)
        .subscribe(println, errorHandler, allDone);

    countDownLatch.await();

  }

}
