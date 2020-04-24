package fun.huanghai;

import fun.huanghai.bean.service.UserService;
import fun.huanghai.bean.test.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class SpringJDBCTest {


    public static void main(String[] args) {
        ApplicationContext ac = new ClassPathXmlApplicationContext("spring-jdbc.xml");;
        UserService bean = ac.getBean(UserService.class);
        bean.save(new User(1,"小测",27,"女"));

        List<User> person1 = bean.getUsers();
        System.out.println("++++++++得到所有User");
        for (User person2 : person1) {
            System.out.println(person2.getId() + "  " + person2.getName()
                    + "   " + person2.getAge() + "  " + person2.getSex());
        }


    }

}
