package ru.itis.javalab.models;

import javax.persistence.*;

import lombok.*;

import java.util.HashSet;
import java.util.List;

import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity(name = "users")
public class User {
    public enum State {
        DELETED,
        CONFIRMED,
        NOT_CONFIRMED,
        BANNED
    }

    public enum Role {
        USER, ADMIN
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;
    private String email;
    private String hashPassword;

    @OneToMany(mappedBy = "performer")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<Ad> responses;

    @OneToOne
    private PaymentCard card;

    @Enumerated(value = EnumType.STRING)
    private State state;

    @Enumerated(value = EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "user")
    private List<Feedback> feedbacks;


    @ManyToMany
    @JoinTable(
            name = "favorite_ads",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "ad_id")}
    )
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<Ad> ads = new HashSet<>();

    public boolean isConfirmed() {
        return this.state.equals(State.CONFIRMED);
    }

    public boolean isBanned() {
        return this.state.equals(State.BANNED);
    }


}
