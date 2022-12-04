package bdm.test.mapper;

import bdm.test.controller.dto.MessageDTO;
import bdm.test.entity.Message;
import org.springframework.stereotype.Component;

@Component
public class MessageMapper {

    public Message mapToMessage(MessageDTO messageDTO){
        Message message = new Message();
        message.setUsername(messageDTO.getUsername());
        message.setText(messageDTO.getText());

        return message;
    }

    public MessageDTO mapToMessageDTO(Message message){
        MessageDTO messageDTO = new MessageDTO();
        messageDTO.setUsername(message.getUsername());
        messageDTO.setText(message.getText());

        return messageDTO;
    }
}
