package main;

import chap07.Calculator;
import config.AppCtxWithCache;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainAspectWithCache {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppCtxWithCache.class);

        Calculator cal = ctx.getBean("calculator", Calculator.class);
        cal.factorial(3);
        cal.factorial(4);
        cal.factorial(5);
        ctx.close();
    }
}
