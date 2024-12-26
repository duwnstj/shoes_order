package com.personal.domain.product.repository;

import com.personal.domain.product.dto.ProductRequest;
import com.personal.domain.product.dto.ProductResponse;
import com.personal.entity.product.ProductType;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static com.personal.entity.product.QProduct.product;
import static com.personal.entity.store.QStore.store;

@RequiredArgsConstructor
public class ProductDslRepositoryImpl implements ProductDslRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<ProductResponse.Infos> getProducts(ProductRequest.GetProducts getProducts, Pageable pageable) {

        JPQLQuery<Long> productCount = JPAExpressions
                .select(product.count())
                .from(product)
                .where(product.store.id.eq(store.id),
                        product.type.eq(ProductType.PRODUCT),
                        product.isDeleted.eq(false),
                        product.isSold.eq(true));
        List<ProductResponse.Infos> results = queryFactory
                .select(Projections.constructor(ProductResponse.Infos.class,
                        product.id,
                        product.type,
                        product.name,
                        product.category,
                        product.material,
                        product.basePrice,
                        product.customPrice,
                        productCount
                ))
                .from(product)
                .where(searchProducts(getProducts.type(), getProducts.value()),
                        product.isDeleted.eq(false)
                )
                .orderBy(getProducts.sort().equals("ASC") ? product.updatedAt.asc() : product.updatedAt.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long totalCount = queryFactory
                .select(product.count())
                .from(product)
                .where(
                        searchProducts(getProducts.type(), getProducts.value()),
                        product.isDeleted.eq(false)
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
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
}
