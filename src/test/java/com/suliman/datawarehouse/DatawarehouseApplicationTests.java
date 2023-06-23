package com.suliman.datawarehouse;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;
import org.springframework.boot.test.context.SpringBootTest;

import com.suliman.datawarehouse.controller.DealControllerTest;
import com.suliman.datawarehouse.service.DealServiceTest;

@Suite
@SpringBootTest
@SelectClasses(value = { DealControllerTest.class, DealServiceTest.class })
class DatawarehouseApplicationTests {

}
