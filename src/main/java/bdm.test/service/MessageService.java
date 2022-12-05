package bdm.test.service;

import bdm.test.entity.Message;
import bdm.test.repository.MessageRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class MessageService {

    private final MessageRepository messageRepository;
    private final AccountService accountService;

    @Autowired
    public MessageService(MessageRepository messageRepository, AccountService accountService) {
        this.messageRepository = messageRepository;
        this.accountService = accountService;
    }

    public Message send(Message message) {
        accountService.getAccountByUsername(message.getUsername());
        Message savedMessage = messageRepository.save(message);
        log.info("Method 'send' has been invoked. Result: {}", savedMessage);
        return savedMessage;
    }

    public List<Message> history10() {
        List<Message> messages = messageRepository.findLastTen();
        log.info("Method 'history10' has been invoked. Result: {}", messages);
        return messages;
    }
}
