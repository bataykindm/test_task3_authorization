package bdm.test.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "accounts")
@Data
public class Account {

    @Id
    private Long id;
    private String username;
    private String password;
}
