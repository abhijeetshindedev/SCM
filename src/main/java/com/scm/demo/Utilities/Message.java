package com.scm.demo.Utilities;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Message {

    private String msgContent;
    @Builder.Default
    private MessageType type = MessageType.blue;

}
