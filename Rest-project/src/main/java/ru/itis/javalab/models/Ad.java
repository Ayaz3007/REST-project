package ru.itis.javalab.models;

import javax.persistence.*;

import lombok.*;
import org.hibernate.annotations.Type;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
public class Ad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private User user;

    private String header;

    @ManyToOne
    @JoinColumn(name = "performer_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private User performer;

    private String description;

    private Integer price;

    private String address;

    @Enumerated(value = EnumType.STRING)
    private Category category;

    @Enumerated(value = EnumType.STRING)
    private State state;

    @ManyToMany(mappedBy = "ads")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<User> users = new HashSet<>();

    public enum Category {
        REPAIR,
        PLUMBING,
        CLEANING,
        WEIGHT_DRAWING,
        OTHER
    }

    public enum State {
        ACTIVE,
        ACCEPTED,
        DRAFT,
        BLOCKED,
        DELETED
    }
}
