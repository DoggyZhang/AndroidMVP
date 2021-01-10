package com.example.androidmvp.base;

import java.util.Collection;

public interface OnAdapterOperator<T> {

    void add(T item);

    void add(int index, T item);

    void add(Collection<T> items);

    void remove(T item);

    void remove(int index);

    void clear();
}
