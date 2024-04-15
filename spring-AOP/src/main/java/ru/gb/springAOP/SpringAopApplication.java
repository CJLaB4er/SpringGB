package ru.gb.springAOP;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class SpringAopApplication {

    public static void main(String[] args) throws InterruptedException {
        ApplicationContext context = SpringApplication.run(SpringAopApplication.class, args);

        TestAOPMethod test1 = context.getBean(TestAOPMethod.class);

        test1.method1();
        test1.method2();

        TestClass tets2 = context.getBean(TestClass.class);

        tets2.method3();
    }

}
