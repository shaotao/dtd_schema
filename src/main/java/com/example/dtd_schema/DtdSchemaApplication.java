package com.example.dtd_schema;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DtdSchemaApplication {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(DtdSchemaApplication.class, args);
		ReadDTD readDTD = new ReadDTD();
		readDTD.run();
	}

}
