# restapitaf
REST api test automation framework with Unirest

Local tests run: use testNG runner 
    or from console:

    ./gradlew clean chromeLocalTest -PsuiteName=orderTestSuite



Get allure report:
    
    allure generate --clean && allure serve
    