package com.example.booksdemo.playground;

import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class SimpleBean {

    public List<Integer> getListOfSortedEvenElementsFromIntegerSet(final Set<Integer> integerSet) {
        return integerSet.stream().filter(integer -> integer % 2 == 0).sorted(Comparator.reverseOrder()).collect(Collectors.toList());
    }
}
