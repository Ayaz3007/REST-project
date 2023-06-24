package ru.itis.javalab.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.javalab.models.Feedback;

public interface FeedbacksRepository extends JpaRepository<Feedback, Long> {
    Page<Feedback> findAllByStateOrderById(Pageable pageable, Feedback.State state);
}
