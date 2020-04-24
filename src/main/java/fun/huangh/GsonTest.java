package fun.huangh;

import fun.huangh.bean.SimpleTestBean;
import fun.huangh.util.GsonImpl;
import org.junit.Test;

public class GsonTest {

    @Test
    public void test01(){
        String json = "{\n" +
                "     \"name\": \"王五\",\n" +
                "     \"gender\": \"man\",\n" +
                "     \"age\": 15,\n" +
                "     \"height\": \"140cm\"\n" +
                " }";
        SimpleTestBean bean = GsonImpl.get().toObject(json, SimpleTestBean.class);

        System.out.println(bean.getGender());
        System.out.println(bean.getName());
        System.out.println(bean.getHeight());
    }
}
