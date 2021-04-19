package com.statistics.statistics;
import java.util.TreeMap;

public class Calculation {

    //вычисление статистик для одного текста
    public static TreeMap<String, double[]> OneText(TreeMap<String, int[]> input_data){
		TreeMap<String,double[]> stats_text = new TreeMap<String,double[]>(); //название маркера, массив статистик
        int[] values = new int[input_data.size()];
        int i =0;
        for(java.util.Map.Entry<String, int[]> mark : input_data.entrySet()) {
            values[i] = mark.getValue()[0];
            i++;
        }  

        StatisticalMethods calc = new StatisticalMethods(values);

        //вычисление статистик на основе количества повторений маркера в одном тексте
        stats_text.put("Характеристики текста", new double[]{calc.getMean(), calc.getMode(), calc.getMedian(), calc.getVariance(),
            calc.getStandardDeviation(), calc.getCoefficientOfVariation(), calc.getSkewness(), calc.getKurtosis()}); 
        return stats_text;
	}

    public static TreeMap<String, double[]> Corpus(TreeMap<String, int[]> input_data){
		TreeMap<String,double[]> stats_text = new TreeMap<String,double[]>(); //название маркера, массив статистик(среднее выборочное, мода..)

        for(java.util.Map.Entry<String, int[]> mark : input_data.entrySet()) {
            StatisticalMethods calc = new StatisticalMethods(mark.getValue()); //вычисление статистик по каждому маркеру

            stats_text.put(mark.getKey(), new double[]{calc.getMean(), calc.getMode(), calc.getMedian(), calc.getVariance(),
                calc.getStandardDeviation(), calc.getCoefficientOfVariation(), calc.getSkewness(), calc.getKurtosis(),
                calc.getMin(), calc.getQuartile(0.25), calc.getQuartile(0.5), calc.getQuartile(0.75), calc.getMax(),
                calc.getInterQuartile()}); 
        }
        return stats_text;  
	}

    public static TreeMap<String, double[]> CalculateStatistics (TreeMap<String, int[]> input_data){
        if(ProcessingFile.oneText==true)
            return OneText(input_data);
        else
            return Corpus(input_data);
    }

}