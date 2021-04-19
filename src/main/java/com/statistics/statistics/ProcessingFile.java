package com.statistics.statistics;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.TreeMap;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.simple.parser.ParseException;

public class ProcessingFile {

    static String[] markerList; //наименование маркеров
    static boolean oneText; //количество проанализированных текстов

    //проверка JSON-файла на наличие информации
    public static boolean IsEmpty(String filepath) throws FileNotFoundException, IOException, ParseException {
        JSONArray marker_quantity_array = new JSONArray(new String(Files.readAllBytes(Paths.get(filepath))));

        if (marker_quantity_array.length() == 0)
            return true;

        return false;
    }

    //чтение JSON-файла - запись в объект TreeMap
    public static TreeMap<String, int[]> ReadFile(String filepath) throws ParseException {
        TreeMap<String,int[]> name_count = new TreeMap<String,int[]>();
        try {     
            JSONArray marker_quantity_array = new JSONArray(new String(Files.readAllBytes(Paths.get(filepath))));

            int text_num = 0; //количество проанализированных текстов

            for (Object M : marker_quantity_array)
            {
                JSONObject jsonObject =  (JSONObject) M;

                String marker = (String) jsonObject.get("Маркер");

                JSONArray marker_quant = (JSONArray) jsonObject.get("Количество");
                int[] mark = new int[marker_quant.length()];
                text_num = 0;
                for(var m: marker_quant){
                    mark[text_num]= (int) m;
                    text_num++;
                }
                name_count.put(marker, mark);
            }

            if(text_num>1)
                oneText=false;
            else
                oneText=true;
        } 
        catch (IOException e) {
            e.printStackTrace();
        }
        return name_count;   
    }

    //создание JSON-файла со статистиками
    public static void CreateJson(TreeMap<String, double[]> statistics, String[] statisticsHeadings, String filename) throws IOException {
        
        JSONArray jsonArray = new JSONArray();

        for(java.util.Map.Entry<String, double[]> mark : statistics.entrySet()) {
            double[] values = mark.getValue();

            JSONObject jsonObject = new JSONObject();
            jsonObject.put(statisticsHeadings[0], mark.getKey()); //добавляем в объект наименование статистики и ее значение

            for(int i=0; i<values.length;i++){          
                jsonObject.put(statisticsHeadings[i+1], values[i]);      
            }
            jsonArray.put(jsonObject);
        }  
        Files.write(Paths.get(filename), jsonArray.toString().getBytes()); //генеерация отчета
    }

    //формирование заголовков выходных файлов (JSON и Excel)
    public static String[] FormStatisticsHeadings() {
        if (oneText == true) {
            return new String[] { "Маркер", "Среднее выборочное", "Мода", "Медиана", "Дисперсия",
            "Среднеквадратичное отклонение", "Коэффициент вариации", "Коэффициент асимметрии", "Эксцесс" };
        } 
        else {
            return new String[] { "Маркер", "Среднее выборочное", "Мода", "Медиана", "Дисперсия",
            "Среднеквадратичное отклонение", "Коэффициент вариации", "Коэффициент асимметрии", "Эксцесс",
            "Минимальное значение", "Квартиль 0,25", "Квартиль 0,5","Квартиль 0,75", "Максимальное значение",
            "Интерквартильный размах"};
        }
    }
}