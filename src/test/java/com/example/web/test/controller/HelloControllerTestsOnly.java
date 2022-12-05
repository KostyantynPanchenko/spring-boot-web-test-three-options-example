package com.example.web.test.controller;

import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SuiteDisplayName;

@Suite
@SuiteDisplayName("Unit tests for HelloController ONLY")
@SelectPackages("com.example.web.test.controller")
class HelloControllerTestsOnly {}
