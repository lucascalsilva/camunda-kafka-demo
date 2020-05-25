package org.camunda.bpm.demo.consumer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.demo.dto.CamundaMessageDto;
import org.camunda.bpm.engine.RuntimeService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class MessageProcessConsumer {

    private final RuntimeService runtimeService;
    private final MessageService messageService;
    private final static String MESSAGE_START = "MessageKafkaDemo";
    private final static String MESSAGE_INTERMEDIATE = "MessageIntermediate";

    @KafkaListener(topics = "start-process-message-topic")
    public void startMessageProcess(CamundaMessageDto camundaMessageDto){
        messageService.correlateMessage(camundaMessageDto, MESSAGE_START);
    }

    @KafkaListener(topics = "intermediate-message-topic")
    public void listen(CamundaMessageDto camundaMessageDto){
       messageService.correlateMessage(camundaMessageDto, MESSAGE_INTERMEDIATE);
    }
}
