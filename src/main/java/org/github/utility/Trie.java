package org.github.utility;
import  org.github.utility.Node;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Trie {
    private Node head;

    // 트라이 생성자
    public Trie() {
        head = new Node(null);
    }

    // 단어 삽입
    public List<String> extractedwords(String insertedWord) {

        List<String> result = new ArrayList<>();
        if (insertedWord == null || insertedWord.isEmpty()) {
            return result; // 입력이 null이거나 빈 문자열일 경우 빈 리스트 반환
        }

        String[] words = insertedWord.split("\\s+"); // 공백을 기준으로 문자열 분할

        // 첫 번째 어절 추가
        if (words.length > 0) {
            result.add(words[0]);
        }

        // 첫 번째와 두 번째 어절을 조합한 문자열 추가
        if (words.length > 1) {
            result.add(words[0] + " " + words[1]);
        }
        // 두 번째 어절을 조합한 문자열 추가
        if (words.length > 1) {
            result.add(words[1]);
        }

        wordAssign(result.get(0));
        wordAssign(result.get(1));
        wordAssign(result.get(2));
        return result;
    }

    private void wordAssign(String insertedWord){

        Node currNode = head;
        for (char word : insertedWord.toCharArray()) {
            currNode.next.putIfAbsent(word, new Node(String.valueOf(word)));
            currNode = currNode.next.get(word);
        }
        currNode.fin = true;
    }

    // 자동완성 기능
    public void autoComplete(String string) {
        Node currNode = head;
        System.out.println("입력 정보: " + string);
        List<String> result = new ArrayList<>();

        // 입력된 문자열에 맞는 경로 찾기
        for (char word : string.toCharArray()) {
            if (!currNode.next.containsKey(word)) {
                System.out.println("추천 검색어가 없습니다.");
                return;
            } else {
                currNode = currNode.next.get(word);
            }
        }

        // 입력된 문자열 자체가 완성된 단어일 경우 추가
        if (currNode.fin) {
            result.add(string);
        }

        // 스택을 사용하여 다음 노드를 탐색
        Stack<Node> stack = new Stack<>();
        stack.addAll(currNode.next.values());

        while (!stack.isEmpty()) {
            Node v = stack.pop();
            if (v.fin) {
                result.add(collectWord(v, string));
            }
            stack.addAll(v.next.values());
        }

        // 결과 출력
        System.out.println(result);
    }

    // 검색기록 출력
    public void showList() {
        List<String> result = new ArrayList<>();
        Stack<Node> stack = new Stack<>();
        stack.addAll(head.next.values());

        while (!stack.isEmpty()) {
            Node v = stack.pop();
            if (v.fin) {
                result.add(collectWord(v, ""));
            }
            stack.addAll(v.next.values());
        }

        System.out.println(result);
    }

    // 단어 수집 (루트에서부터 단어를 만들어내는 도우미 함수)
    private String collectWord(Node node, String prefix) {
        StringBuilder word = new StringBuilder(prefix);
        Node currNode = node;
        while (currNode != null && currNode.value != null) {
            word.append(currNode.value);
            currNode = null;
        }
        return word.toString();
    }


    // 트라이 초기화
    public static void main(String[] args) {
        Trie T = new Trie();
        System.out.println(" 삽입 기능 string, str, stress, star, singer, sign, starcraft");
        // 분석할 텍스트
        String input_text = "회사는 새로운 소프트웨어 개발 프로젝트를 시작하며, 프로젝트 목표와 범위 설정으로 명확한 목표와 작업 범위를 정의한 뒤, 일정과 예산 계획 수립을 통해 필요한 자원과 시간을 산정합니다. 이어 팀 구성 및 역할 할당으로 인력을 배치하고, 프로젝트 킥오프 미팅에서 모든 계획을 공유하여 프로젝트를 본격적으로 시작합니다.";

        List<String> wordLists ;
        wordLists = T.extractedwords(input_text);

        // 단어 삽입
        T.wordAssign("string");
        T.wordAssign("str");
        T.wordAssign("stress");
        T.wordAssign("star");
        T.wordAssign("singer");
        T.wordAssign("sign");
        T.wordAssign("starcraft");
        T.wordAssign("southkorea");
        T.wordAssign("south korea");

        // 자동완성 기능
        System.out.println(" 자동완성 기능 ");
        T.autoComplete("soo");
        T.autoComplete("st");
        T.autoComplete("star");
        T.autoComplete("south");

        // 검색기록 출력
        System.out.println("검색기록 출력");
        T.showList();
    }
}