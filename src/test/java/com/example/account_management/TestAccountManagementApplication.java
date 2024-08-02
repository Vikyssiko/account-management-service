package com.example.account_management;

import org.springframework.boot.SpringApplication;

public class TestAccountManagementApplication {

	public static void main(String[] args) {
		SpringApplication.from(AccountManagementApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
