package com.qa.tests;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.qa.base.TestBase;
import com.qa.restclient.RestClient;
import com.qa.util.TestUtil;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.logging.Logger;

public class GetApiTest extends TestBase{
    TestBase testBase;
    String host;
    String url;
    RestClient restClient;
    CloseableHttpResponse closeableHttpResponse;
    final static Logger Log = Logger.getLogger(String.valueOf(GetApiTest.class));

    @BeforeClass
    public void setUp(){
        testBase = new TestBase();
        host = testBase.getPro().getProperty("HOST");
        Log.info("测试服务器地址为：" + host.toString());
        url = host + "/api/users?page=2";
        Log.info("当前测试接口的完整地址为：" + url.toString());

    }
    @Test
    public void getAPITest() throws IOException {
//        restClient = new RestClient();
//        restClient.get(url);
        //改写get后
        Log.info("用例开始执行");
        restClient = new RestClient();
        closeableHttpResponse = restClient.get(url);

        //断言判断状态码hi不是200
        Log.info("断言状态是否是200");
        int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();
        Assert.assertEquals(statusCode,RESPONSE_STATUS_CODE_200,"response status code is not 200");

        //把响应内容存储在字符串对象中
        String responseString = EntityUtils.toString(closeableHttpResponse.getEntity(),"UTF-8");
        //创建json对象 把上面字符串序列化json对象
        JSONObject responseJson = JSON.parseObject(responseString);
        //System.out.println("respon json from api---->" + responseJson);

        //json内容解析
        String s1  = TestUtil.getValueByJpath(responseJson,"data[0]/first_name");
        Log.info("执行JSON解析，解析的内容是：" + s1);
//        String s1_1  = TestUtil.getValueByJpath(responseJson,"data[0]/id");
//        String s2  = TestUtil.getValueByJpath(responseJson,"per_page");
//        String s3  = TestUtil.getValueByJpath(responseJson,"total");
//        System.out.println(s1);
//        System.out.println(s1_1);
//        System.out.println(s2);
//        System.out.println(s3);
        Log.info("接口内容响应断言");
        Assert.assertEquals(s1,"Michael","first name is not Michael");
        Log.info("用例结束...");
    }
}
