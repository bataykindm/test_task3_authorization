package bdm.test.mapper;

import bdm.test.controller.dto.MessageDTO;
import bdm.test.entity.Message;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

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

    public List<MessageDTO> mapToListMessageDTO(List<Message> messages){
        return messages.stream().map(this::mapToMessageDTO).collect(Collectors.toList());
    }
}
