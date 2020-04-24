package fun.huanghai;

import fun.huanghai.Invocation.MyInvocationHandler;
import fun.huanghai.service.NameValidator;
import fun.huanghai.service.UserService;
import fun.huanghai.service.impl.UserServiceImpl;
import org.junit.Test;

public class ProxyTest {

    @Test
    public void testProxy() throws Throwable{
        UserService userService = new UserServiceImpl();

        MyInvocationHandler handler = new MyInvocationHandler(userService);
        UserService proxy = (UserService) handler.getProxy();
        proxy.add(null);
    }
}
