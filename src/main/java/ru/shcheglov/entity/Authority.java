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
@Table(name = "app_authorities")
public class Authority {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "general-generator")
    @SequenceGenerator(name = "general-generator", sequenceName = "SQ_GENERAL", allocationSize = 1)
    @Column(name = "AUTHORITY_ID", nullable = false, unique = true)
    private Long id;

    @NonNull
    @Column(name = "NAME", nullable = false, unique = true)
    private String name;

    @Column(name = "DESCRIPTION")
    private String description;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "app_users_authorities",
            joinColumns = @JoinColumn(name = "AUTHORITY_ID"),
            inverseJoinColumns = @JoinColumn(name = "USER_ID")
    )
    private Set<User> users;
}
