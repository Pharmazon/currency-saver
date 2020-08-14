package ru.shcheglov.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@RequiredArgsConstructor
@NoArgsConstructor
@Table(name = "app_users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "general-generator")
    @SequenceGenerator(name = "general-generator", sequenceName = "SQ_GENERAL", allocationSize = 1)
    @Column(name = "USER_ID", nullable = false, unique = true)
    private Long id;

    @NonNull
    @Column(name = "USERNAME", nullable = false, unique = true)
    private String username;

    @NonNull
    @Column(name = "PASSWORD", nullable = false, unique = true)
    private String password;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "LAST_NAME")
    private String lastName;

    @NonNull
    @Column(name = "EMAIL", unique = true)
    private String email;

    @NonNull
    @Column(name = "ENABLED", nullable = false)
    private Boolean enabled;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "app_users_roles",
            joinColumns = @JoinColumn(name = "USER_ID"),
            inverseJoinColumns = @JoinColumn(name = "ROLE_ID")
    )
    private Set<Role> roles;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "app_users_authorities",
            joinColumns = @JoinColumn(name = "USER_ID"),
            inverseJoinColumns = @JoinColumn(name = "AUTHORITY_ID")
    )
    private Set<Authority> authorities;
}
