package bdm.test.service;

import bdm.test.entity.Message;
import bdm.test.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {

    private final MessageRepository messageRepository;

    @Autowired
    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public Message sent(Message message) {
        return messageRepository.save(message);
    }

    public List<Message> history10() {
        return messageRepository.findLastTen();
    }
}
