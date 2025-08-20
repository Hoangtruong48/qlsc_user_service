package com.example.quanlysancau.specification;

import jakarta.persistence.criteria.*;
import org.springframework.data.jpa.domain.Specification;

import java.util.*;

public class BaseSpecificationBuilder<T> {

    private final List<SearchCriteria> criteriaList = new ArrayList<>();

    // == Add condition ==
    public BaseSpecificationBuilder<T> with(String key, SearchOperation operation, Object value) {
        if (value != null) {
            criteriaList.add(new SearchCriteria(key, operation, value));
        }
        return this;
    }

    public BaseSpecificationBuilder<T> with(String key, SearchOperation operation, Object valueFrom, Object valueTo) {
        if (valueFrom != null && valueTo != null && operation == SearchOperation.BETWEEN) {
            criteriaList.add(new SearchCriteria(key, operation, valueFrom, valueTo));
        }
        return this;
    }

    public BaseSpecificationBuilder<T> with(String key, SearchOperation operation, List<Object> values) {
        if (values != null && !values.isEmpty()) {
            criteriaList.add(new SearchCriteria(key, operation, values));
        }
        return this;
    }

    // == Build Specification ==
    public Specification<T> build() {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            for (SearchCriteria criteria : criteriaList) {
                String key = criteria.getKey();
                Path<?> path = root.get(key); // No join — assume flat structure

                switch (criteria.getOperation()) {
                    case EQUAL -> predicates.add(cb.equal(path, criteria.getValue()));
                    case NOT_EQUAL -> predicates.add(cb.notEqual(path, criteria.getValue()));
                    case GREATER_THAN -> predicates.add(cb.greaterThan(path.as(Comparable.class), (Comparable) criteria.getValue()));
                    case GREATER_THAN_OR_EQUAL -> predicates.add(cb.greaterThanOrEqualTo(path.as(Comparable.class), (Comparable) criteria.getValue()));
                    case LESS_THAN -> predicates.add(cb.lessThan(path.as(Comparable.class), (Comparable) criteria.getValue()));
                    case LESS_THAN_OR_EQUAL -> predicates.add(cb.lessThanOrEqualTo(path.as(Comparable.class), (Comparable) criteria.getValue()));
                    case LIKE -> predicates.add(cb.like(cb.lower(path.as(String.class)), "%" + criteria.getValue().toString().toLowerCase() + "%"));
                    case NOT_LIKE -> predicates.add(cb.notLike(cb.lower(path.as(String.class)), "%" + criteria.getValue().toString().toLowerCase() + "%"));
                    case STARTS_WITH -> predicates.add(cb.like(cb.lower(path.as(String.class)), criteria.getValue().toString().toLowerCase() + "%"));
                    case ENDS_WITH -> predicates.add(cb.like(cb.lower(path.as(String.class)), "%" + criteria.getValue().toString().toLowerCase()));
                    case IN -> {
                        CriteriaBuilder.In<Object> inClause = cb.in(path);
                        for (Object val : criteria.getValues()) {
                            inClause.value(val);
                        }
                        predicates.add(inClause);
                    }
                    case NOT_IN -> {
                        CriteriaBuilder.In<Object> notInClause = cb.in(path);
                        for (Object val : criteria.getValues()) {
                            notInClause.value(val);
                        }
                        predicates.add(cb.not(notInClause));
                    }
                    case IS_NULL -> predicates.add(cb.isNull(path));
                    case IS_NOT_NULL -> predicates.add(cb.isNotNull(path));
                    case BETWEEN -> predicates.add(cb.between(
                            path.as(Comparable.class),
                            (Comparable) criteria.getValue(),
                            (Comparable) criteria.getValueTo()
                    ));
                }
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}



