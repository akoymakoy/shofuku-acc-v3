package com.Junit.tests;

import org.apache.log4j.*;
import org.apache.log4j.BasicConfigurator;
public class SimpleLogTest {
  static Logger logger = Logger.getLogger("SimpleLog.class");
  public static void main(String[] args) {
  BasicConfigurator.configure();
  logger.debug("Hello world.");  
  }
}