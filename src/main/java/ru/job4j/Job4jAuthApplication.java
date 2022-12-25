package ru.job4j;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import javax.sql.DataSource;

//@Configuration
//@ComponentScan(basePackages = "ru.job4j")
@SpringBootApplication
//@EntityScan("ru.job4j.domain")
public class Job4jAuthApplication {

	public static void main(String[] args) {
		SpringApplication.run(Job4jAuthApplication.class, args);
	}
}