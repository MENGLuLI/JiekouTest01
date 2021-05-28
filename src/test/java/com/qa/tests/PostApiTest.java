package com.qa.tests;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.qa.base.TestBase;
import com.qa.data.Users;
import com.qa.restclient.RestClient;
import com.qa.util.TestUtil;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;

public class PostApiTest extends TestBase {
    TestBase testBase;
    String host;
    String url;
    RestClient restClient;
    CloseableHttpResponse closeableHttpResponse;
    @BeforeClass
    public void setUp(){
        testBase = new TestBase();
        host = testBase.getPro().getProperty("HOST");
        url = host + "/api/users";
    }
    @Test
    public void postApiTest() throws IOException {
        restClient = new RestClient();
        //准备请求头信息
        HashMap<String,String> headermap = new HashMap<String, String>();
        headermap.put("Content-Type","application/json");

        //对象转换成json字符串
        Users user = new Users("Anthony","tester");
        String userJsonString = JSON.toJSONString(user);
//        System.out.println(userJsonString);
        closeableHttpResponse = restClient.post(url, userJsonString,headermap);

        //验证状态码是不是200
        int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();
        Assert.assertEquals(statusCode,RESPONSE_STATUS_CODE_201,"Status code is not 201");

        //断言响应json内容中name和job是不是期待结果
        String responseString = EntityUtils.toString(closeableHttpResponse.getEntity());
        JSONObject responseJson = JSON.parseObject(responseString);
        //System.out.println(responseString);
        String name  = TestUtil.getValueByJpath(responseJson,"name");
        String job  = TestUtil.getValueByJpath(responseJson,"job");
        Assert.assertEquals(name,"Anthony","name is not the same");
        Assert.assertEquals(job,"tester","job is not the same");


    }

}
