package com.personal.domain.store.repository;

import com.personal.entity.store.Store;
import com.personal.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StoreRepository extends JpaRepository<Store, Long> {
    List<Store> findAllByUser(User user);
}
