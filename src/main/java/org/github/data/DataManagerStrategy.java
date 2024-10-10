package org.github.data;

import org.github.task.Task;

import java.util.List;

public interface DataManagerStrategy<T> {
    void parseData();
    List<T> getData();
}