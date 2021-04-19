package com.statistics.statistics;

import java.io.IOException;
import java.util.TreeMap;
import org.json.simple.parser.ParseException;
import org.springframework.boot.ExitCodeGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class StatisticsApplication {

	//работа компонента
	public static void main(String[] args) throws IOException, ParseException {
		SpringApplication.run(StatisticsApplication.class, args);
		
		JsonProperties.CreateJson(); //входные данные - JSON-файл
		TreeMap<String,int[]> input_data = ProcessingFile.ReadFile("C:/Users/Admin/input_statistics.json"); //чтение JSON-файла
		TreeMap<String,double[]> stats = Calculation.CalculateStatistics(input_data); //вычисление статистик
		ProcessingFile.CreateJson(stats, ProcessingFile.FormStatisticsHeadings()); //создание JSON-файла
		ExcelFile.Create(stats, ProcessingFile.FormStatisticsHeadings()); //создание файла Excel

		ConfigurableApplicationContext ctx = new SpringApplicationBuilder(StatisticsApplication.class)
  .web(WebApplicationType.NONE).run();

int exitCode = SpringApplication.exit(ctx, new ExitCodeGenerator() {
@Override
public int getExitCode() {
        //завершение работы
        return 0;
    }
});

System.exit(exitCode);
	}

}
