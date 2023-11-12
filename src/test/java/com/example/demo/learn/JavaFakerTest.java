package com.example.demo.learn;

import com.github.javafaker.Faker;
import com.github.javafaker.service.FakeValuesService;
import com.github.javafaker.service.RandomService;
import org.junit.jupiter.api.Test;

import java.util.Locale;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class JavaFakerTest {

    @Test
    void name(){
        Faker faker = new Faker(Locale.KOREAN);
        String result =
          faker.commerce().productName() + "/" +
          faker.team().name() +"/"+
          faker.address().fullAddress()
        ;
        System.out.println(faker.random());
    }

    @Test
    void fakeValue(){
        FakeValuesService fakeValuesService =
                new FakeValuesService(new Locale("ko-KR"), new RandomService());
        String email = fakeValuesService.bothify("????##@gmail.com");
        Matcher matcher = Pattern.compile("\\w{4}\\d{2}@gmail.com").matcher(email);
        System.out.println(email);
        assertTrue(matcher.find());

        String alphaNumericString = fakeValuesService.regexify("[a-z1-9]{10}");
        Matcher alphaNumericMatcher = Pattern.compile("[a-z1-9]{10}").matcher(alphaNumericString);

        System.out.println(alphaNumericString);
        assertTrue(alphaNumericMatcher.find());
    }

    @Test
    void random(){
        Random random = new Random();
        var a1 = random.nextInt(26);
        var a2 = random.nextInt(26);
        var a3 = random.nextInt(26);
        System.out.println(String.format("%d %d %d", a1, a2, a3));
        //대문자
        System.out.println((char)(65+a1));
        System.out.println((char)(65+a2));
        System.out.println((char)(65+a3));
        //소문자
        System.out.println((char)(97+a1));
        System.out.println((char)(97+a2));
        System.out.println((char)(97+a3));

        System.out.println(new Random().nextInt(2));
        System.out.println(new Random().nextInt(2));
        System.out.println(new Random().nextInt(2));
        System.out.println(new Random().nextInt(2));
        System.out.println(new Random().nextInt(2));
    }
}