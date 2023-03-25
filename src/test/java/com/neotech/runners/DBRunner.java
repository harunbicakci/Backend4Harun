package com.neotech.runners;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)

@CucumberOptions(
		
		features = "src/test/resources/features/",
		glue = "com.neotech.steps",
		dryRun = false,
		tags = "@DB",
		monochrome = true,
		plugin = {
				// "pretty", steps are not printed
				"html:target/cucumber-default-report.html",
				"json:target/cucumber.json" }
		)

public class DBRunner {

}
