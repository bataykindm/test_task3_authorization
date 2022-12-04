package bdm.test.controller;

import bdm.test.controller.dto.MessageDTO;
import bdm.test.entity.Message;
import bdm.test.mapper.MessageMapper;
import bdm.test.service.MessageService;
import bdm.test.validator.MessageIdentifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/")
public class MessageRestController {

    private final MessageService messageService;
    private final MessageMapper messageMapper;
    private final MessageIdentifier messageIdentifier;

    @Autowired
    public MessageRestController(MessageService messageService, MessageMapper messageMapper, MessageMapper messageMapper1, MessageIdentifier messageIdentifier) {
        this.messageService = messageService;
        this.messageMapper = messageMapper1;
        this.messageIdentifier = messageIdentifier;
    }

    @PostMapping("messages")
    public ResponseEntity sent(@RequestBody MessageDTO requestDTO) {
        Object result;

        if (messageIdentifier.identify(requestDTO)) {
            result = messageMapper.mapToListMessageDTO(messageService.history10());
        } else {
            result = messageService.sent(messageMapper.mapToMessage(requestDTO));
        }

        return new ResponseEntity(result, HttpStatus.OK);
    }

}
