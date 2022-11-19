package com.ordina.countingwords.word.frequency.service.impl;

import com.ordina.countingwords.word.frequency.service.WordFrequency;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class WordFrequencyImpl implements WordFrequency {
    private String word;
    private int frequency;

    @Override
    public String getWord() {
        return word;
    }

    @Override
    public int getFrequency() {
        return frequency;
    }
}
