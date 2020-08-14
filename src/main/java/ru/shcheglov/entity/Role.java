package ru.shcheglov.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@ToString(exclude = "users")
@NoArgsConstructor
@RequiredArgsConstructor
@Table(name = "app_roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "general-generator")
    @SequenceGenerator(name = "general-generator", sequenceName = "SQ_GENERAL", allocationSize = 1)
    @Column(name = "ROLE_ID", nullable = false, unique = true)
    private Long id;

    @NonNull
    @Column(name = "NAME", nullable = false)
    private String name;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "app_users_roles",
            joinColumns = @JoinColumn(name = "ROLE_ID"),
            inverseJoinColumns = @JoinColumn(name = "USER_ID")
    )
    private Set<User> users;
}
