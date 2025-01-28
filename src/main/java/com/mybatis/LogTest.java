package com.mybatis;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LogTest {
    private static final Logger logger = LogManager.getLogger(LogTest.class);

    public static void main(String[] args) {
        logger.info("Log4j2 설정이 정상적으로 동작 중입니다.");
        logger.warn("경고 메시지 테스트");
        logger.error("에러 메시지 테스트");
    }
}
