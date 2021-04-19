package com.statistics.statistics;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class StatisticalMethodsTests {
    private static StatisticalMethods statsTest = new StatisticalMethods(new int[]{45,75,22,0,1,11,111,200,20,2,0,0,0,0,1,200,200,200,1,0});

    
    @Test
    void Mean(){
        assertEquals(54.45, statsTest.getMean());
    }

    @Test
    void Mode(){
        assertEquals(0, statsTest.getMode());
    }

    @Test
    void Median(){
        assertEquals(6.5, statsTest.getMedian());
    }

    @Test
    void Variance(){
        assertEquals(6084.35, statsTest.getVariance());
    }

    @Test
    void StandardDeviation(){
        assertEquals(78, statsTest.getStandardDeviation());
    }

    @Test
    void CoefficientOfVariation(){
        assertEquals(143.25, statsTest.getCoefficientOfVariation());
    }

    @Test
    void Kurtosis(){
        assertEquals(-0.44, statsTest.getKurtosis());
    }

    @Test
    void Skewness(){
        assertEquals(1.14, statsTest.getSkewness());
    }

    @Test
    void Min(){
        assertEquals(0, statsTest.getMin());
    }

    @Test
    void Max(){
        assertEquals(200, statsTest.getMax());
    }

    @Test
    void Quartile1(){
        assertEquals(0, statsTest.getQuartile(0.25));
    }

    @Test
    void Quartile2(){
        assertEquals(75, statsTest.getQuartile(0.75));
    }
    
    @Test
    void InterQuartile(){
        assertEquals(75, statsTest.getInterQuartile());
    }

    public static void main(String[] args){
        System.out.println(statsTest.getMode());
	}
}
