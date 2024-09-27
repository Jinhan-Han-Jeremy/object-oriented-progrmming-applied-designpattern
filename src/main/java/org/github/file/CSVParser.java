package org.github.file;

import java.util.List;

public interface CSVParser<T> {
    List<T> parse(List<String[]> data);
}