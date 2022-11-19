package com.ordina.countingwords.word.frequency.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WordFrequencyRequest {
    private String text;
    private String word;
    private Integer number;
}
