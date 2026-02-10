package com.example.api.runners;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/java/ressouces/features/user.feature",
        glue = "com/example/api/cucumbersteps",
        plugin = {"pretty"}
)
public class CucumberTestRunner {

}
