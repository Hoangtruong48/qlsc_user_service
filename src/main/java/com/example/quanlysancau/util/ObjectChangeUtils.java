package com.example.quanlysancau.util;

import lombok.Getter;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class ObjectChangeUtils {
    public record FieldDiff(String field, Object oldValue, Object newValue) {

        @Override
        public String toString() {
            return String.format("Field '%s' changed from [%s] to [%s]", field, oldValue, newValue);
        }

    }

    public static List<FieldDiff> compareObjects(Object before, Object after) {
        if (before == null || after == null) {
            throw new IllegalArgumentException("Objects must not be null.");
        }

        if (!before.getClass().equals(after.getClass())) {
            throw new IllegalArgumentException("Objects must be of the same class.");
        }

        List<FieldDiff> differences = new ArrayList<>();
        Class<?> clazz = before.getClass();

        while (clazz != null) { // support parent fields
            for (Field field : clazz.getDeclaredFields()) {
                field.setAccessible(true);

                // Bỏ qua static hoặc transient nếu cần
                if (java.lang.reflect.Modifier.isStatic(field.getModifiers()) ||
                        java.lang.reflect.Modifier.isTransient(field.getModifiers())) {
                    continue;
                }

                try {
                    Object value1 = field.get(before);
                    Object value2 = field.get(after);

                    if (!Objects.equals(value1, value2)) {
                        differences.add(new FieldDiff(field.getName(), value1, value2));
                    }

                } catch (IllegalAccessException e) {
                    throw new RuntimeException("Error accessing field: " + field.getName(), e);
                }
            }
            clazz = clazz.getSuperclass(); // move to parent class
        }

        return differences;
    }

    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(3, 2, 3);
        List<Integer> list1 = Arrays.asList(1, 2, 3);
        System.out.println(Objects.equals(list1, list));
    }
}
