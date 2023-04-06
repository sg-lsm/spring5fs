package main;

import chap07.ExeTimecalculator;
import chap07.ImpCalculator;
import chap07.RecCalculator;

public class MainProxy {
    public static void main(String[] args) {
        ExeTimecalculator ttCal = new ExeTimecalculator(new ImpCalculator());
        System.out.println(ttCal.factorial(20));

        ExeTimecalculator ttCal2 = new ExeTimecalculator(new RecCalculator());
        System.out.println(ttCal2.factorial(20));
    }
}
