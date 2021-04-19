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
		
		String file_input = ""; //имя входного файла
		JsonProperties.CreateJson(file_input); //входные данные - JSON-файл (здесь тестовый вариант)

		TreeMap<String,int[]> input_data = ProcessingFile.ReadFile(file_input); //чтение JSON-файла
		TreeMap<String,double[]> stats = Calculation.CalculateStatistics(input_data); //вычисление статистик

		String json_output = ""; //наименование генерируемого json-отчета
		String excel_output = ""; //наименование генерируемого json-отчета
		ProcessingFile.CreateJson(stats, ProcessingFile.FormStatisticsHeadings(), json_output); //создание JSON-файла
		ExcelFile.Create(stats, ProcessingFile.FormStatisticsHeadings(), excel_output); //создание файла Excel

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
