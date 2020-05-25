package org.camunda.bpm.demo.controller;

import lombok.RequiredArgsConstructor;
import org.camunda.bpm.demo.dto.CamundaMessageDto;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/message-process")
@RequiredArgsConstructor
public class MessageProcessRestController {

    private final KafkaTemplate<String, CamundaMessageDto> kafkaTemplate;

    @PostMapping("/start")
    public void startMessageProcess(@RequestBody CamundaMessageDto camundaMessageDto){
        kafkaTemplate.send("start-process-message-topic", camundaMessageDto);
    }

    @PostMapping("/intermediate")
    public void correlateIntermediateMessage(@RequestBody String correlationId){
        kafkaTemplate.send("intermediate-message-topic", CamundaMessageDto.builder().correlationId(correlationId).build());
    }
}
