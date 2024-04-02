package ru.test.prime.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import ru.test.prime.model.enums.UserStatus;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@ToString
@Entity
@Table(name = "users")
public class User extends BaseEntity {

    @Column(name = "login")
    private String login;
    @Column(name = "full_name")
    private String fullName;
    @Column(name = "user_status")
    private UserStatus userStatus;

    @OneToMany(mappedBy = "responsible")
    @ToString.Exclude
    private List<Task> userTask;

}
