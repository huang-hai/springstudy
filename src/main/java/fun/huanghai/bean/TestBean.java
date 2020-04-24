package fun.huanghai.bean;

public class TestBean {

    private String testStr = "testStr";

    public String getTestStr() {
        return testStr;
    }

    public void setTestStr(String testStr) {
        this.testStr = testStr;
    }

    public void test(String name){
        System.out.println("test"+name);
    }
}
