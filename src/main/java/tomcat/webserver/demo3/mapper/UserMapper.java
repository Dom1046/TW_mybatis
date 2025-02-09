package tomcat.webserver.demo3.mapper;

import tomcat.webserver.demo3.entity.User;

//이렇게 interface를 만들어서 xml에 접근하는 이유를 모르겠음, 그리고 UserMapper.xml인데도 인터페이스로 읽기가 가능하군...
public interface UserMapper {
    void insertUser(User user);
    User getUserById(int id);
}
