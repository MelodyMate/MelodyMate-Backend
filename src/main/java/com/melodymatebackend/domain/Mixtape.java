package com.melodymatebackend.domain;

import com.melodymatebackend.users.domain.User;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Entity
@Table(name = "MIXTAPES")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Mixtape {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, columnDefinition = "NUMERIC(19, 0)")
    private Long id;

    @Column(name = "name", nullable = false, length = 200)
    private String name;

    @Column(name = "description", length = 400)
    private String description;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "users_id", nullable = false, columnDefinition = "NUMERIC(19, 0)")
    private User users;

}