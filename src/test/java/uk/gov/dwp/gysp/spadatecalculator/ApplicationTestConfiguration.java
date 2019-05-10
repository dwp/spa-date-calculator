package uk.gov.dwp.gysp.spadatecalculator;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit4.SpringRunner;

import java.lang.annotation.*;

@Documented
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@RunWith(SpringRunner.class)
@SpringBootTest(classes={Application.class}, properties = "spring.data.mongodb.port = 0")
public @interface ApplicationTestConfiguration {
	//
}
