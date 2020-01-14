package com.example.booksdemo.playground;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;


@Component
public class OneMoreSimpleBean implements ISimpleBean {

//    public int getDifferenceBetweenLowestAndHighestValuesFromIntegerList(final List<Integer> integerList) {
//        List<Integer> sortedList = integerList.stream().sorted().collect(Collectors.toList());
//        return sortedList.get(sortedList.size() - 1) - sortedList.get(0);
//
//    }

    @Override
    public int doSomething(List<Integer> intList) {
        List<Integer> sortedList = intList.stream().sorted().collect(Collectors.toList());
        return sortedList.get(sortedList.size() - 1) - sortedList.get(0);
    }
}
