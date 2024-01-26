package com.intech.msseguridad.message;

import java.util.List;

import com.intech.msseguridad.dtos.AuthRequest;
import com.intech.msseguridad.dtos.AuthResponse;
import com.intech.msseguridad.models.UsuarioLoginModel;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.header.Header;
import org.apache.kafka.common.header.internals.RecordHeader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class TransactionMessagePublish {

    @Value("${spring.kafka.template.default-topic}")
    String topicName;

    private Logger log = LoggerFactory.getLogger(TransactionMessagePublish.class);

    @Autowired
    KafkaTemplate<String, String> kafkaTemplate;
    @Autowired
    ObjectMapper objectMapper;

    private ProducerRecord<String, String> buildProducerRecord(String value, String topic) {
        List<Header> recordHeaders = List.of(new RecordHeader("login-subscription-source", "scanner".getBytes()));
        return new ProducerRecord<>(topic, null, value, value, recordHeaders); // Cambiado el tipo de clave a String
    }

    public ListenableFuture<SendResult<String, String>> sendLoginEvent(AuthRequest authRequest)
            throws JsonProcessingException {
        String value = objectMapper.writeValueAsString(authRequest);
        ProducerRecord<String, String> producerRecord = buildProducerRecord(value, topicName);
        ListenableFuture<SendResult<String, String>> listenableFuture = kafkaTemplate.send(producerRecord);

        listenableFuture.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
            @Override
            public void onSuccess(SendResult<String, String> result) {
                try {
                    handleSuccess(value, result);
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Throwable ex) {
                handleFailure(value, ex);
            }
        });

        return listenableFuture;
    }

    private void handleFailure(String value, Throwable ex) {
        log.error("Error: send message and the error is {}", ex.getMessage());
        try {
            // Handle failure logic if needed
        } catch (Throwable throwable) {
            log.error("Error on OnFailure {}", throwable.getMessage());
        }
    }

    private void handleSuccess(String value, SendResult<String, String> result)
            throws JsonMappingException, JsonProcessingException {
        log.info("Se ha Logeado el usuario con credenciales de . Value: {}, Partition: {}", value,
                result.getRecordMetadata().partition());
    }
}
