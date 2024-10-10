package org.github.data;

import org.github.tasksstream.TasksHistory;
import org.github.utility.Trie;

import java.util.List;


public class InitialDataStream {
    private final String workStream;

    public InitialDataStream(String workStream) {
        this.workStream = workStream;
    }

    private String name;
    private List<String> teamMembers;
    private List<String> availableJobs;
    private int finishedDays;
    private String state;
    private boolean requirementsSatisfied;

    public List<String> getSummarizedTaskHistories(List<String> summarizedTaskStream){
        return summarizedTaskStream;
    }

    public String getRecoMendedWords(String workStream){
        Trie recommendedWord = new Trie();
        List<String> parsedStreamWords;
        parsedStreamWords = recommendedWord.extractedwords(workStream);


        return parsedStreamWords.get(0);
    }





}
