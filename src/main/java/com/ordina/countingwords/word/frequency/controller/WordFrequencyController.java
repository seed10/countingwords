package com.ordina.countingwords.word.frequency.controller;

import com.ordina.countingwords.word.frequency.exception.InvalidTextException;
import com.ordina.countingwords.word.frequency.service.WordFrequency;
import com.ordina.countingwords.word.frequency.service.WordFrequencyAnalyzer;
import com.ordina.countingwords.word.frequency.domain.WordFrequencyRequest;
import com.ordina.countingwords.word.frequency.domain.WordFrequencyResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class WordFrequencyController {

    private final WordFrequencyAnalyzer wordFrequencyAnalyzer;

    @Autowired
    public WordFrequencyController(WordFrequencyAnalyzer wordFrequencyAnalyzer) {
        this.wordFrequencyAnalyzer = wordFrequencyAnalyzer;
    }

    @PostMapping(value = "/calculateHighestFrequency",consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public WordFrequencyResponse calculateHighestFrequency(@RequestBody WordFrequencyRequest request) {
        int maxOuccrence = wordFrequencyAnalyzer.calculateHighestFrequency(request.getText());
        return  new WordFrequencyResponse("The highest frequency  of a word  in the text " + request.getText() + " is "  + maxOuccrence);
    }

    @PostMapping(value = "/calculateFrequencyForWord",consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public WordFrequencyResponse calculateFrequencyForWord(@RequestBody WordFrequencyRequest request) {
        int maxOuccrence = wordFrequencyAnalyzer.calculateFrequencyForWord(request.getText(),request.getWord());
        return  new WordFrequencyResponse("The frequency of " + request.getWord() + " is " + maxOuccrence);
    }

    @PostMapping(value = "/calculateMostFrequentNWords",consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<WordFrequency> calculateMostFrequentNWords(@RequestBody WordFrequencyRequest request) {
        List<WordFrequency> wordFrequencies = wordFrequencyAnalyzer.calculateMostFrequentNWords(request.getText(),request.getNumber());
        return wordFrequencies;
    }

    @ExceptionHandler({ InvalidTextException.class})
    public String handleException() {
        return "Invalid input text";
    }


}
