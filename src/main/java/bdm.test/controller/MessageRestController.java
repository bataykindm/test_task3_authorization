package bdm.test.controller;

import bdm.test.controller.dto.MessageDTO;
import bdm.test.entity.Message;
import bdm.test.mapper.MessageMapper;
import bdm.test.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/messages/")
public class MessageRestController {

    private final MessageService messageService;
    private final MessageMapper messageMapper;

    @Autowired
    public MessageRestController(MessageService messageService, MessageMapper messageMapper) {
        this.messageService = messageService;
        this.messageMapper = messageMapper;
    }

    @PostMapping("sent")
    public ResponseEntity sent(@RequestBody MessageDTO requestDTO){
        Message message = messageService.sent(messageMapper.mapToMessage(requestDTO));
        return new ResponseEntity(message, HttpStatus.OK);
    }
}
