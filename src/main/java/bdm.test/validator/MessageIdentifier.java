package bdm.test.validator;

import bdm.test.controller.dto.MessageDTO;
import bdm.test.mapper.MessageMapper;
import org.springframework.stereotype.Component;

@Component
public class MessageIdentifier {

    private final MessageMapper messageMapper;

    public MessageIdentifier(MessageMapper messageMapper) {
        this.messageMapper = messageMapper;
    }

    public boolean identify(MessageDTO messageDTO) {
        return messageDTO.getText().equals("history 10");
    }
}
