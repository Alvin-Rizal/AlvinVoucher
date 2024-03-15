package com.alvin.alvinvoucher.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "m_customer")

public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column( nullable = false)
    private String name;
    @Column( nullable = false, unique = true )
    private String email;
    @Column( nullable = false, unique = true )
    private String phone;
    @OneToOne
    @JoinColumn(name = "user_credential_id")
    private UserCredential userCredential;

}
