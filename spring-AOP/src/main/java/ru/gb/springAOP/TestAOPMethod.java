package ru.gb.springAOP;

import org.springframework.stereotype.Component;

@Component
public class TestAOPMethod {

    @Timer
    public void method1(){
        System.out.println("Отработал метод №1 помеченный аннотацией");
    }

    @Timer
    public void method2() throws InterruptedException {
        Thread.sleep(1234);
        System.out.println("Отработал метод №2 помеченный аннотацией. Спал 1234мс.");
    }

}
