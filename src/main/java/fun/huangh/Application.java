package fun.huangh;

import fun.huangh.bean.User;
import fun.huangh.mapper.UserMapper;
import fun.huangh.util.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Application {
    static SqlSessionFactory sqlSessionFactory = null;
    static {
        sqlSessionFactory = MyBatisUtil.getSqlSessionFactory();
    }

    @Test
    public void addUser(){
        SqlSession sqlSession = sqlSessionFactory.openSession();

        try {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            mapper.insertUser(new User("我是My Batis",28));
            sqlSession.commit(); //这里一定要提交,否则数据进不去数据库中去
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void getUser(){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            User user = userMapper.getUser(4);
            System.out.println(user);
        } finally {
            sqlSession.close();;
        }
    }

    @Test
    public void test01(){
        ApplicationContext ac = new ClassPathXmlApplicationContext("spring-mybatis.xml");
        UserMapper bean = ac.getBean(UserMapper.class);
        System.out.println(bean.getUser(4));
    }
}
