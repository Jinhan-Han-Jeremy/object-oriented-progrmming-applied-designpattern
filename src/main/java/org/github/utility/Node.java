package org.github.utility;

import java.util.HashMap;
import java.util.Map;

class Node {
    String value;
    boolean fin; // 단어가 끝났을 경우 true로 설정
    Map<Character, Node> next;

    // 노드 생성자
    public Node(String value) {
        this.value = value;
        this.fin = false;
        this.next = new HashMap<>();
    }
}