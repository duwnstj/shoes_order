package com.personal.domain.product.repository;

import com.personal.domain.product.dto.ProductRequest;
import com.personal.domain.product.dto.ProductResponse;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static com.personal.entity.product.QProduct.product;

@RequiredArgsConstructor
public class ProductDslRepositoryImpl implements ProductDslRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<ProductResponse.Infos> getProducts(ProductRequest.GetProducts getProducts, Pageable pageable, Long storeId) {
        // 메인쿼리: 조건을 모두 처리하고 페이징 처리
        List<ProductResponse.Infos> results = queryFactory
                .select(Projections.constructor(ProductResponse.Infos.class,
                        product.id,
                        product.type,
                        product.name,
                        product.category,
                        product.material,
                        product.basePrice,
                        product.customPrice,
                        product.store.id
                ))
                .from(product)
                .where(
                        product.store.id.eq(storeId),
                        product.isDeleted.eq(false),
                        searchProducts(getProducts.type(), getProducts.value()),
                        applyIsSoldCondition(getProducts.isSold())
                )
                .orderBy(getProducts.sort().equals("ASC") ? product.updatedAt.asc() : product.updatedAt.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
        // 총 개수 계산
        Long totalCount = queryFactory
                .select(product.count())
                .from(product)
                .where(
                        product.store.id.eq(storeId),
                        product.isDeleted.eq(false),
                        searchProducts(getProducts.type(), getProducts.value()),
                        applyIsSoldCondition(getProducts.isSold())
                )
                .fetchOne();
        return new PageImpl<>(results, pageable, totalCount);
    }

    private BooleanExpression searchProducts(String type, String value) {

        return switch (type) {
            case "name" -> value != null ? product.name.contains(value) : null;
            case "category" -> value != null ? product.category.contains(value) : null;
            default -> null;
        };
    }

    private BooleanExpression applyIsSoldCondition(Boolean isSold) {
        return isSold != null ? product.isSold.eq(isSold) : null;
    }

}
