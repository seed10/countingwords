package com.ordina.countingwords.word.frequency.analyzer;

import com.ordina.countingwords.word.frequency.service.WordFrequency;
import com.ordina.countingwords.word.frequency.service.impl.WordFrequencyAnalyzerImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest()
public class WordFrequencyAnalyzerTest {

    @Test
    void calculateHighestFrequency() {
        WordFrequencyAnalyzerImpl wordFrequencyAnalyzer = new WordFrequencyAnalyzerImpl();
        int frequency = wordFrequencyAnalyzer.calculateHighestFrequency("Hello my name is Yusuf");
        assertEquals(frequency,1);

        int frequency1 = wordFrequencyAnalyzer.calculateHighestFrequency("Yusuf Seedat is my  full name. Yusuf is my first name and last name is Seedat");
        assertEquals(frequency1,3);

        int frequency2 = wordFrequencyAnalyzer.calculateHighestFrequency("Full Name YUSUF YUsuF Seedat");
        assertEquals(frequency2,2);

        int frequency3 = wordFrequencyAnalyzer.calculateHighestFrequency("Hello,my,name*is&Yusuf");
        assertEquals(frequency3,1);

    }

    @Test
    void calculateFrequencyForWord() {
        WordFrequencyAnalyzerImpl wordFrequencyAnalyzer = new WordFrequencyAnalyzerImpl();
        int frequency = wordFrequencyAnalyzer.calculateFrequencyForWord("Hello my name is Yusuf","Yusuf");
        assertEquals(frequency,1);

        int frequency1 = wordFrequencyAnalyzer.calculateFrequencyForWord("Yusuf Seedat is my full name. Yusuf is my first name","Yusuf");
        assertEquals(frequency1,2);

        int frequency2 = wordFrequencyAnalyzer.calculateFrequencyForWord("Yusuf YusUf YUSUF YuSUF","Yusuf");
        assertEquals(frequency2,4);

        int frequency3 = wordFrequencyAnalyzer.calculateFrequencyForWord("Yusuf&YusUf^YUSUF YuSUF","Yusuf");
        assertEquals(frequency3,4);
    }

    @Test
    void calculateMostFrequentNWords() {
        WordFrequencyAnalyzerImpl wordFrequencyAnalyzer = new WordFrequencyAnalyzerImpl();
        List<WordFrequency> wordFrequencies = wordFrequencyAnalyzer.calculateMostFrequentNWords("The sun shines over the lake",2);
        assertEquals(wordFrequencies.size(), 2);
        assertEquals(wordFrequencies.get(0).getWord(),"the");

        List<WordFrequency> wordFrequencies1 = wordFrequencyAnalyzer.calculateMostFrequentNWords("The sun shines over the lake",0);
        assertEquals(wordFrequencies1.size(), 0);
    }

}
