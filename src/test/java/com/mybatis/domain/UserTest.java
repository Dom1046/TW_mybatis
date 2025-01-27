package com.mybatis.domain;

import com.mybatis.config.SqlSessionFactoryManager;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertNotNull;

public class UserTest {

    private SqlSessionFactory sqlSessionFactory;

    @Before
    public void setUp() {
        // MyBatis 세션 팩토리 초기화
        sqlSessionFactory = SqlSessionFactoryManager.getSqlSessionFactory();
        assertNotNull(sqlSessionFactory);
    }

    @Test
    public void testSelectAll() {
        // 세션 열기
        try (SqlSession session = sqlSessionFactory.openSession()) {
            // "namespace.id" 형태로 쿼리 호출
            List<User_Dto> list = session.selectList("com.mybatis.mapper.UserMapper.selectAll");
            assertNotNull(list);
            System.out.println("조회된 user 수: " + list.size());

            for (User_Dto user : list) {
                System.out.println(user);
            }
        }
    }
}
