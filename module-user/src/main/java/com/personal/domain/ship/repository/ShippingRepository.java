package com.personal.domain.ship.repository;

import com.personal.entity.ship.ShippingStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShippingRepository extends JpaRepository<ShippingStatus , Long> , ShippingDslRepository {
}
