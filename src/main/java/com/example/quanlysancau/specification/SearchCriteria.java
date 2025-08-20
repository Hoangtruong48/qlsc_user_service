package com.example.quanlysancau.specification;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchCriteria {
    private String key;
    private SearchOperation operation;
    private Object value;
    private Object valueTo; // dùng cho BETWEEN
    private List<Object> values; // dùng cho IN/NOT_IN


    public SearchCriteria(String key, SearchOperation operation, Object value) {
        this.key = key;
        this.operation = operation;
        this.value = value;
    }

    public SearchCriteria(String key, SearchOperation operation, Object valueFrom, Object valueTo) {
        this.key = key;
        this.operation = operation;
        this.value = valueFrom;
        this.valueTo = valueTo;
    }
}

