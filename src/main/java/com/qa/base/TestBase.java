package com.qa.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class TestBase {
    //此类为所有接口请求测试的父类

    public Properties pro;

    public int getRESPONSE_STATUS_CODE_200() {
        return RESPONSE_STATUS_CODE_200;
    }

    public int getRESPONSE_STATUS_CODE_201() {
        return RESPONSE_STATUS_CODE_201;
    }

    public int getRESPONSE_STATUS_CODE_404() {
        return RESPONSE_STATUS_CODE_404;
    }

    public int getRESPONSE_STATUS_CODE_500() {
        return RESPONSE_STATUS_CODE_500;
    }

    //改进的代码  返回的状态码不写死
    public int RESPONSE_STATUS_CODE_200 = 200;
    public int RESPONSE_STATUS_CODE_201 = 201;
    public int RESPONSE_STATUS_CODE_404 = 404;
    public int RESPONSE_STATUS_CODE_500 = 500;


    //此构造方法 实现加载读取properties文件
    //把加载配置文件的代码写在空参构造里
    //每次初始化这个类的对象，就会执行构造函数的代码 执行读取配置文件
    public TestBase(){
        pro = new Properties();
        try {
            FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+
                    "/src/main/java/com/qa/config/config.properties");
            pro.load(fis);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Properties getPro() {
        return pro;
    }

    public void setPro(Properties pro) {
        this.pro = pro;
    }
}
