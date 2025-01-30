package com.mybatis.config;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;

public class SqlSessionFactoryManager {

    private static final SqlSessionFactory sqlSessionFactory;

    static {
        String configPath = "mybatis-config.xml";  // resources 루트 기준
        try (Reader reader = Resources.getResourceAsReader(configPath)) {
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
            System.out.println("SqlSessionFactory 객체 생성 성공!");
        } catch (IOException e) {
            throw new RuntimeException("MyBatis 설정 파일 로드 실패: " + configPath, e);
        }
    }

    public static SqlSessionFactory getSqlSessionFactory() {
        return sqlSessionFactory;
    }
}
