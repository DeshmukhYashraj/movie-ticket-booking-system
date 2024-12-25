//package com.movie.config;
//
//import org.apache.log4j.ConsoleAppender;
//import org.apache.log4j.Level;
//import org.apache.log4j.Logger;
//import org.apache.log4j.SimpleLayout;
//
//public class LoggerApp {
//
//    private static final Logger logger = Logger.getLogger(LoggerApp.class);
//
//    static {
//        SimpleLayout layout = new SimpleLayout();
//        ConsoleAppender appender = new ConsoleAppender(layout);
//
//        if (logger.getAllAppenders().hasMoreElements() == false) {
//            logger.addAppender(appender);
//        }
//        logger.setLevel(Level.DEBUG);
//    }
//
//    public static Logger getLogger() {
//        return logger;
//    }
//}
