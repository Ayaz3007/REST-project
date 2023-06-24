package ru.itis.javalab.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.itis.javalab.models.Ad;

import java.util.List;

public interface AdsRepository extends JpaRepository<Ad, Long> {

    Page<Ad> findAllByStateOrderById(Pageable pageable, Ad.State state);

    @Query(value = "select * from ad where header like concat('%', :query, '%') and state = :state", nativeQuery = true)
    Page<Ad> findAllByHeaderLikeAndState(Pageable pageable, @Param("query") String query, String state);

    Page<Ad> findAllByCategory(Pageable pageable, Ad.Category category);

    @Query(value = "select * from ad where header like concat('%', :query, '%') and category = :category and " +
            "state = :state", nativeQuery = true)
    Page<Ad> findAllByHeaderLikeAndStateAndCategory(Pageable pageable, String query, String category, String state);

    Page<Ad> findByUsers_Id(Pageable pageable, Long userId);
}
