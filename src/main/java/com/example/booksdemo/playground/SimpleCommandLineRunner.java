package com.example.booksdemo.playground;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Profile("h1")
@Component
public class SimpleCommandLineRunner implements CommandLineRunner {
    private final SimpleBean simpleBean;
    private final ISimpleBean beanOne;
    private final ISimpleBean beanTwo;

    public SimpleCommandLineRunner(SimpleBean simpleBean, @Qualifier("otherSimpleBean") ISimpleBean beanOne, @Qualifier("oneMoreSimpleBean") ISimpleBean beanTwo) {
        this.simpleBean = simpleBean;
        this.beanOne = beanOne;
        this.beanTwo = beanTwo;
    }


    @Override
    public void run(String... args) throws Exception {
        Set<Integer> arguments = convertArgumentsToIntegerSet(args);
        List<Integer> sortedAndEven = simpleBean.getListOfSortedEvenElementsFromIntegerSet(arguments);
        System.out.println("Sorted list of even elements: " + sortedAndEven.toString());
        System.out.println("Sum of even elements: " + beanOne.doSomething(sortedAndEven));
        System.out.println("Difference between lowest ang highest value from list: " + beanTwo.doSomething(sortedAndEven));
    }

    private Set<Integer> convertArgumentsToIntegerSet(String[] args) {
        return Arrays.stream(args).map(Integer::valueOf).collect(Collectors.toSet());
    }
}
