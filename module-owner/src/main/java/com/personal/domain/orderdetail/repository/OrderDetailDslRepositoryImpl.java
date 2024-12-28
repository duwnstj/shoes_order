package com.personal.domain.orderdetail.repository;

import com.personal.domain.orderdetail.dto.OrderDetailRequest;
import com.personal.domain.orderdetail.dto.OrderDetailResponse;
import com.personal.entity.order.OrderStatus;
import com.personal.entity.product.ProductType;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.JPQLQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;

import static com.personal.entity.order.QOrdersDetail.ordersDetail;

@RequiredArgsConstructor
public class OrderDetailDslRepositoryImpl implements OrderDetailDslRepository {
    private final JPQLQueryFactory queryFactory;

    @Override
    public Page<OrderDetailResponse.GetInfos> getOrders(Pageable pageable, Long storeId, OrderDetailRequest.GetOrders getOrders) {

        List<OrderDetailResponse.GetInfos> results = queryFactory
                .select(Projections.constructor(OrderDetailResponse.GetInfos.class,
                        ordersDetail.orders.id,
                        ordersDetail.orders.store.id,
                        ordersDetail.orders.store.name,
                        ordersDetail.orders.orderNo,
                        ordersDetail.orders.orderDate,
                        ordersDetail.orders.recipi,
                        ordersDetail.orders.tel,
                        ordersDetail.orders.request,
                        ordersDetail.orders.orderStatus,
                        ordersDetail.orders.paymentMethod,
                        ordersDetail.orders.totalAmt,
                        ordersDetail.orders.totalTax,
                        ordersDetail.orders.zip,
                        ordersDetail.orders.address,
                        ordersDetail.orders.addressDetail,
                        ordersDetail.product.id,
                        ordersDetail.productName,
                        ordersDetail.length,
                        ordersDetail.width,
                        ordersDetail.qty,
                        ordersDetail.customYN,
                        ordersDetail.customPrice,
                        ordersDetail.basePrice,
                        ordersDetail.amt,
                        ordersDetail.tax
                ))
                .from(ordersDetail)
                .where(
                        ordersDetail.orders.store.id.eq(storeId),
                        ordersDetail.product.type.eq(ProductType.PRODUCT),
                        ordersDetail.product.isDeleted.eq(false),
                        ordersDetail.product.isSold.eq(true),
                        searchStatus(getOrders.orderStatus()),
                        searchOrderDate(getOrders.startDate(), getOrders.endDate())
                )
                .orderBy(getOrders.sort().equals("asc") ? ordersDetail.orders.orderDate.asc() : ordersDetail.orders.orderDate.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long totalCount = queryFactory
                .select(ordersDetail.count())
                .from(ordersDetail)
                .where(
                        ordersDetail.orders.store.id.eq(storeId),
                        ordersDetail.product.type.eq(ProductType.PRODUCT),
                        ordersDetail.product.isDeleted.eq(false),
                        ordersDetail.product.isSold.eq(true),
                        searchStatus(getOrders.orderStatus()),
                        searchOrderDate(getOrders.startDate(), getOrders.endDate())
                )
                .fetchOne();

        return new PageImpl<>(results, pageable, totalCount);

    }

    private BooleanExpression searchOrderDate(LocalDate startDate, LocalDate endDate) {
        return startDate != null && endDate != null ? ordersDetail.orders.orderDate.between(startDate, endDate) : null;
    }

    private BooleanExpression searchStatus(OrderStatus status) {
        return status != null ? ordersDetail.orders.orderStatus.eq(status) : null;
    }
}
