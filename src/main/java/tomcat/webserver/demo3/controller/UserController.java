package tomcat.webserver.demo3.controller;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import tomcat.webserver.demo3.entity.User;
import tomcat.webserver.demo3.mapper.UserMapper;

public class UserController {
    public static void main(String[] args) {
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(
                UserController.class.getResourceAsStream("/mybatis-config.xml"));

        try(SqlSession session = sqlSessionFactory.openSession()){ //try-with-resources 자원을 바로 닫기 위함
            UserMapper userMapper = session.getMapper(UserMapper.class);

            //insert a new User
            User user = new User();
            user.setName("dom");
            user.setEmail("dom@naver.com");
            userMapper.insertUser(user);
            session.commit();//Commit the transaction

            //Fetch user by ID
            User fetchedUser = userMapper.getUserById(1);
            System.out.println("fetched User = " + fetchedUser.getName());
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
