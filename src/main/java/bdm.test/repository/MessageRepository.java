package bdm.test.repository;

import bdm.test.entity.Message;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {

    @Query(nativeQuery = true, value = "SELECT * FROM messages ORDER BY id DESC LIMIT 10")
    List<Message> findLastTen();
}
