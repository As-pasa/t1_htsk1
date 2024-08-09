package com.as_pasa.t1_htsk1.utils;

import org.mockito.ArgumentMatcher;

import java.util.List;

public class ContainsSubstringsMatcher implements ArgumentMatcher<String> {
    private List<String> substrings;

    public ContainsSubstringsMatcher(List<String> substrings){
        this.substrings = substrings;
    }
    @Override
    public boolean matches(String s) {
        return substrings.stream()
                .map(s::contains)
                .reduce(true, (sub,e)->sub && e);
    }
}
