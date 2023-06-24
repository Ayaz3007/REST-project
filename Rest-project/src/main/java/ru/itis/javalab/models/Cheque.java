package ru.itis.javalab.models;

import io.hypersistence.utils.hibernate.type.basic.PostgreSQLEnumType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "cheques")
public class Cheque {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double price;

    @ManyToOne
    @JoinColumn(name = "executor_id", referencedColumnName = "id")
    private User executor;

    @OneToOne
    @JoinColumn(name = "advertisement_id")
    private Ad advertisement;

    @Temporal(TemporalType.TIMESTAMP)
    private Date paymentTime;

    @Enumerated(EnumType.STRING)
    private State state;

    public enum State {
        ACTIVE,
        DELETED
    }

}
