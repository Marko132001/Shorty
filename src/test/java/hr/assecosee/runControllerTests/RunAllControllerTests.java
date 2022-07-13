package hr.assecosee.runControllerTests;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

import hr.asseccosee.controllers.LoginControllerIntegrationTest;
import hr.asseccosee.controllers.RegistrationControllerIntegrationTest;
import hr.asseccosee.controllers.ShortyControllerIntegrationTest;
import hr.asseccosee.controllers.StatisticsControllerIntegrationTest;
import hr.assecosee.shorty.UrlRepositoryTest;

@Suite
@SelectClasses({LoginControllerIntegrationTest.class, RegistrationControllerIntegrationTest.class,
					ShortyControllerIntegrationTest.class, StatisticsControllerIntegrationTest.class, UrlRepositoryTest.class})
class RunAllControllerTests {


}
