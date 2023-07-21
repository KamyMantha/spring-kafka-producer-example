package com.tanzu.kafka.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;

@RestController
@AllArgsConstructor
public class KafkaProducerController {

    private final KafkaTemplate<String, String> kafkaTemplate;

    private final ObjectMapper objectMapper;

    @PostMapping("/produce")
    @SneakyThrows
    public ResponseEntity<String> postModelToKafka(@RequestBody Employee emp)
            throws InterruptedException, ExecutionException {
        kafkaTemplate.send("test", objectMapper.writeValueAsString(emp));
        return ResponseEntity.ok().build();
    }
}