package com.personal.domain.store.repository;

import com.personal.entity.store.Store;
import com.personal.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface StoreRepository extends JpaRepository<Store, Long> {
    List<Store> findAllByUser(User user);


    @Query("select s from Store s where s.user.id=:userId")
    Optional<Store> findByUserId(@Param("userId") Long userId);
}
