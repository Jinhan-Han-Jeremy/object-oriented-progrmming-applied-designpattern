package org.github.data;

import org.github.task.Task;

import java.util.ArrayList;
import java.util.List;

// 제네릭을 사용하는 DataManagerContext 클래스
public class DataManagerContext<T> {
    private DataManagerStrategy<T> strategy;

    // 전략 설정 메서드
    public void setStrategy(DataManagerStrategy<T> strategy) {
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
    public List<T> getData() {
        if (strategy != null) {
            return strategy.getData();
        } else {
            System.out.println("전략이 설정되지 않았습니다.");
            return new ArrayList<>();
        }
    }
}