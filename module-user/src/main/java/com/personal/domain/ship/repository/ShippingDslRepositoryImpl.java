package com.personal.domain.ship.repository;

import com.personal.domain.ship.dto.ShippingRequest;
import com.personal.domain.ship.dto.ShippingResponse;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;

import static com.personal.entity.ship.QShippingStatus.shippingStatus1;

@Slf4j
@RequiredArgsConstructor
public class ShippingDslRepositoryImpl implements ShippingDslRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<ShippingResponse.Info> getShippingStatus(Long userId, ShippingRequest.GetShipping getShipping, Pageable pageable) {
        List<ShippingResponse.Info> result = queryFactory
                .select(Projections.constructor(ShippingResponse.Info.class ,
                        shippingStatus1.shippingStatus,
                        shippingStatus1.shipStartDate,
                        shippingStatus1.shipCompleteDate,
                        shippingStatus1.trackingNumber,
                        shippingStatus1.carrier,
                        shippingStatus1.trackingUrl,
                        shippingStatus1.createdAt,
                        shippingStatus1.updatedAt
                        ))
                .from(shippingStatus1)
                .where(
                        shippingStatus1.orders.user.id.eq(userId),
                        searchStartOrderDate(getShipping.startDate()),
                        searchEndOrderDate(getShipping.endDate())
                )
                .orderBy(shippingStatus1.updatedAt.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long totalCount = queryFactory
                .select(shippingStatus1.count())
                .from(shippingStatus1)
                .where(
                        shippingStatus1.orders.user.id.eq(userId),
                        searchStartOrderDate(getShipping.startDate()),
                        searchEndOrderDate(getShipping.endDate())
                )
                .fetchOne();;

        return new PageImpl<>(result, pageable, totalCount);
    }

    private BooleanExpression searchStartOrderDate(LocalDate startDate) {
        return startDate != null ? shippingStatus1.createdAt.goe(startDate.atStartOfDay()) : null;
    }

    private BooleanExpression searchEndOrderDate(LocalDate endDate) {
        return endDate != null ? shippingStatus1.createdAt.loe(endDate.atStartOfDay()) : null;
    }
}
