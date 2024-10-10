package org.github.data;

import org.github.task.Task;

import java.util.ArrayList;
import java.util.List;

public class DataManagerContext {
    private DataManagerStrategy strategy;

    // 전략 설정 메서드
    public void setStrategy(DataManagerStrategy strategy) {
        this.strategy = strategy;
    }

    // 전략 실행 메서드
    public void executeParseData() {
        if (strategy != null) {
            strategy.parseData();
        } else {
            System.out.println("전략이 설정되지 않았습니다.");
        }
    }

    // 데이터를 가져오는 메서드
    public List<Task> getData() {
        if (strategy != null) {
            return strategy.getData();
        } else {
            System.out.println("전략이 설정되지 않았습니다.");
            return new ArrayList<>();
        }
    }
}