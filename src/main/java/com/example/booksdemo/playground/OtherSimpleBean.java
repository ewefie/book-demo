package com.example.booksdemo.playground;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OtherSimpleBean  implements  ISimpleBean{

//    public int getSumFromIntegerList(final List<Integer> intList) {
//        return intList
//                .stream()
//                .mapToInt(Integer::intValue)
//                .sum();
//    }

    @Override
    public int doSomething(List<Integer> intList) {
        return intList
                .stream()
                .mapToInt(Integer::intValue)
                .sum();
    }
}
