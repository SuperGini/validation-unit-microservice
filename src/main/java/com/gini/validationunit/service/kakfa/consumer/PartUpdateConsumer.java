package com.gini.validationunit.service.kakfa.consumer;


import com.gini.avro.data.PartPriceUpdateWithCurrency;
import com.gini.validationunit.service.services.postgres.part.PartWishListService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class PartUpdateConsumer {

    private final PartWishListService partWishListService;

    @KafkaListener(topics = "topic_update.price.validation.unit")
    public void consumeMessage(ConsumerRecord<String, PartPriceUpdateWithCurrency> message) {
        int numberOfUpdatedParts = partWishListService.updateAllPartPrices(message.value());

        log.debug("Message received from topic: {} <--> with key: {} and message: {}",message.topic(), message.key(), message.value());
        log.info("There were {} prices updates -> for part {} -> with partId {} and partNumber {} ",
                numberOfUpdatedParts, message.value().getPartName(), message.value().getPartId(), message.value().getPartNumber());
    }

}
