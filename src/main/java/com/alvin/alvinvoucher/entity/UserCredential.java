package com.alvin.alvinvoucher.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "m_user_credential")
public class UserCredential {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String username;
    private String password;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;
}
