package ru.itis.javalab.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.javalab.models.PaymentCard;

public interface CardsRepository extends JpaRepository<PaymentCard, Long> {
}
