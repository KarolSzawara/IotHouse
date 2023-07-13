package pl.szawara.authservice.Users.Model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity(name = "Users")
@Table(name = "users",uniqueConstraints = @UniqueConstraint(name = "email_unique",columnNames = "email"))
public class Users {
    @Id
    @SequenceGenerator(
            name = "user_sequence",
            sequenceName = "user_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_squence"
    )
    @Column(name = "id",updatable = false)
    private Long id;
    @Column(name = "email",nullable = false)
    private String email;
    @Enumerated(EnumType.STRING)
    @Column(name = "Roles",nullable = false)
    private Roles roles;
    @ToString.Exclude
    @Column(name = "password",nullable = false,columnDefinition = "text")
    private String password;
    @Enumerated
    @Column
    private UserStatus status;
}
