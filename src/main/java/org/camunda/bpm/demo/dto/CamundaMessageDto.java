package org.camunda.bpm.demo.dto;

import lombok.*;
import org.camunda.bpm.demo.dto.MessageProcessDto;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CamundaMessageDto implements Serializable {

    private String correlationId;
    private MessageProcessDto dto;

}
