package com.ordina.countingwords.word.frequency.service.impl;

import com.ordina.countingwords.word.frequency.exception.InvalidTextException;
import com.ordina.countingwords.word.frequency.service.WordFrequency;
import com.ordina.countingwords.word.frequency.service.WordFrequencyAnalyzer;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class WordFrequencyAnalyzerImpl implements WordFrequencyAnalyzer {


    public static final String REPLACMENT_PATTERN = "[^A-Za-z]";
    public static final String INVALID_INPUT_EXCEPTION = "Invalid input text";

    @Override
    public int calculateHighestFrequency(String text) {
        if (text != null && !text.trim().isEmpty()) {
            String[] wordArr = manipuluateString(text);

            Map<String, Integer> frequencyMap = addWordsToFrequencyMap(wordArr);
            int maxOccurence = Collections.max(frequencyMap.values());

            return maxOccurence;
        }
        throw new InvalidTextException(INVALID_INPUT_EXCEPTION);
    }

    @Override
    public int calculateFrequencyForWord(String text, String word) {
        if (text != null && !text.trim().isEmpty() && word != null && !word.trim().isEmpty()) {
            String[] wordArr = manipuluateString(text);
            String lowerCaseWord = word.toLowerCase();

            Map<String, Integer> frequencyMap = addWordsToFrequencyMap(wordArr);
            int frequency = frequencyMap.get(lowerCaseWord);

            return frequency;
        }

        throw new InvalidTextException(INVALID_INPUT_EXCEPTION);

    }

    @Override
    public List<WordFrequency> calculateMostFrequentNWords(String text, int n) {
        List<WordFrequency> wordFrequencies = new ArrayList<>();
        if (text != null && !text.trim().isEmpty() && n != 0) {
            String[] wordArr = manipuluateString(text);

            Map<String, Integer> frequencyMap = addWordsToFrequencyMap(wordArr);

            Map<String, Integer> sortedWords = frequencyMap.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())).limit(n)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2,
                    LinkedHashMap::new));


            sortedWords.forEach((key, value) -> wordFrequencies.add(new WordFrequencyImpl(key, value)));
            return wordFrequencies.stream().limit(n).collect(Collectors.toList());
        } else if (text != null && !text.trim().isEmpty() && n == 0) {
            return wordFrequencies;
        }
        throw new InvalidTextException(INVALID_INPUT_EXCEPTION);
    }

    private static Map<String, Integer> addWordsToFrequencyMap(String[] wordArr) {
        Map<String, Integer> frequencyMap = new HashMap<>();
        for (String w : wordArr) {
            if (frequencyMap.containsKey(w)) {
                int count = frequencyMap.get(w) + 1;
                frequencyMap.put(w, count);
            } else {
                frequencyMap.put(w, 1);
            }
        }
        return frequencyMap;
    }

    private static String[] manipuluateString(String text) {
        String lowerCaseText = text.toLowerCase();
        String replaceAllSeperators = lowerCaseText.replaceAll(REPLACMENT_PATTERN, " ");
        String[] wordArr = replaceAllSeperators.split(" ");
        return wordArr;
    }
}
