package stock.noti.price_simulator.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import stock.noti.price_simulator.model.StockPrice;

import java.time.Instant;
import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class StockPriceProducer {
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;
    private final List<String> symbols = List.of("VNM", "FPT", "VCB", "HPG");

    @Scheduled(fixedRate = 2000)
    public void sendFakePrice() {
        String symbol = symbols.get(new Random().nextInt(symbols.size()));
        double price =   50000 + new Random().nextDouble() * 50000;

        StockPrice stock = new StockPrice(symbol, price, Instant.now());
        try {
            String json = objectMapper.writeValueAsString(stock);
            kafkaTemplate.send("price_update", symbol, json);
            System.out.println("✔ Sent: " + json);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
