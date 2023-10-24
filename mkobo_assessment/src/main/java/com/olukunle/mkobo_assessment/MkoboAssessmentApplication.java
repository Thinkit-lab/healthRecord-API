package com.olukunle.mkobo_assessment;

import com.olukunle.mkobo_assessment.utils.AppConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(AppConfig.class)
public class MkoboAssessmentApplication {

	public static void main(String[] args) {
		SpringApplication.run(MkoboAssessmentApplication.class, args);
	}

}
