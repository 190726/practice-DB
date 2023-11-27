package com.example.demo.learn;

import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.List;

public class IteratorTest {

    @Test
    void iterator() {

        CustomIterable it = new CustomIterable();

        it.forEach(System.out::println);

        Iterator<String> iterator = it.iterator();
        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }

    }

    static class CustomIterable implements  Iterable<String>{

        @Override
        public Iterator<String> iterator() {
            return new Iterator<String>() {

                private int count = 0;
                @Override
                public boolean hasNext() {
                    return count++ < 5;
                }

                @Override
                public String next() {
                    return count + "/";
                }
            };
        }
    }
}
