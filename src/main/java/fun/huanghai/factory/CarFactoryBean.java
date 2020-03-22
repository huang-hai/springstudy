package fun.huanghai.factory;

import fun.huanghai.bean.Car;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.lang.Nullable;

public class CarFactoryBean implements FactoryBean<Car>{

    private String carInfo;

    @Nullable
    public Car getObject() throws Exception {
        Car car = new Car();
        String[] infos = carInfo.split(",");
        car.setBrand(infos[0]);
        car.setMaxSpeed(Integer.parseInt(infos[1]));
        car.setPrice(Double.parseDouble(infos[2]));
        return car;
    }

    @Nullable
    public Class<?> getObjectType() {
        return null;
    }

    public boolean isSingleton() {
        return false;
    }

    public String getCarInfo() {
        return carInfo;
    }

    //接收逗号分隔符设置属性信息
    public void setCarInfo(String carInfo) {
        this.carInfo = carInfo;
    }
}
