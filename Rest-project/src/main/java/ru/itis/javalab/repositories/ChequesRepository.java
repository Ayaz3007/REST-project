package ru.itis.javalab.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.javalab.models.Cheque;
import ru.itis.javalab.models.User;

public interface ChequesRepository extends JpaRepository<Cheque, Long> {

    Page<Cheque> findAllByStateOrderById(Pageable pageable, Cheque.State state);
    Page<Cheque> findAllByExecutorAndStateOrderById(Pageable pageable, User user, Cheque.State state);
}
