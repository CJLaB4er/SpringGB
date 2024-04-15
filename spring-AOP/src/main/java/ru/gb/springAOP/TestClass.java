package ru.gb.springAOP;

import org.springframework.stereotype.Component;

@Component
@Timer
public class TestClass {
    public void method3() throws InterruptedException {
        Thread.sleep(2345);
        System.out.println("Отработал метод в аннотированном классе. Спал 2345мс.");
    }
}
