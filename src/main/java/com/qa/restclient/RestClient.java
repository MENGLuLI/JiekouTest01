package com.qa.restclient;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.Header;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class RestClient {
    //实现get请求 得到响应状态码和响应头信息 以及相应主体是json内容

//    //1,get请求方法
//    public void get(String url) throws IOException {
//        //创建一个可以关闭的httpclient对象
//        CloseableHttpClient httpClient = HttpClients.createDefault();
//        //创建一个httpGet请求对象
//        HttpGet httpGet = new HttpGet(url);
//        //请求执行  相当于feddler的执行按钮 然后赋值给httpresponse对象接受
//        CloseableHttpResponse httpResponse = httpClient.execute(httpGet);
//
//        //拿到HTTP响应状态码 例如200 400
//        int responseStatusCode = httpResponse.getStatusLine().getStatusCode();
//        System.out.println("response ststus code--->" + responseStatusCode);
//        //把响应内容存储在字符串对象中
//        String responseString = EntityUtils.toString(httpResponse.getEntity(),"UTF-8");
//        //创建json对象 把上面的字符串序列化成json对象
//        JSONObject responseJson = JSON.parseObject(responseString);
//        System.out.println("response json from API---->" + responseJson);
//        //获取响应头信息 返回一个数组
//        Header[] headers = httpResponse.getAllHeaders();
//        //创建一个hashmap对象 header返回的值都以key vaule的形式存在
//        HashMap<String,String> hm = new HashMap<String, String>();
//        //循环遍历数组中的数据  把元素添加到hashmap中
//        for (Header header : headers) {
//            if (header.getName().equals("Content-Length") ){
//                continue;
//            }
//            else {
//                hm.put(header.getName(),header.getValue());
//            }
//        }
//        System.out.println("response headers --->" + hm);
//
//
//    }
    final static Logger Log = Logger.getLogger(String.valueOf(RestClient.class));

    //1 重构get请求方法 不带请求头的方法
    public CloseableHttpResponse get (String url) throws IOException {
        //创建一个HTTP client对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        //创建httpget对象
        HttpGet httpGet = new HttpGet(url);
        Log.info("开始发送get请求。。。。");
        //执行请求 把得到的值付给httpresponse接受
        CloseableHttpResponse HttpResponse = httpClient.execute(httpGet);
        Log.info("发送get请求成功！开始得到响应对象");
        return HttpResponse;

    }

    /**
     * 带请求头信息的get方法
     * @param url
     * @param headermap  键值对形式存储
     * @return 返回响应对象
     * @throws IOException
     */

    //2 get请求方法（带请求头信息）
    public CloseableHttpResponse get(String url,HashMap<String ,String> headermap) throws IOException {
        //创建一个可关闭的HTTPclient 对象
        CloseableHttpClient client = HttpClients.createDefault();
        //创建一个HTTPget对象
        HttpGet httpGet = new HttpGet(url);
        //加载请求头到http
        for (Map.Entry<String,String> entry : headermap.entrySet()){
            httpGet.addHeader(entry.getKey(),entry.getValue());

        }
        //执行请求 相当于feddiler上执行请求
        CloseableHttpResponse httpResponse = client.execute(httpGet);
        Log.info("开始发送带请求头的get请求。。。。");
        return httpResponse;
    }

    /**
     *
     * @param url
     * @param entityString  设置请求json参数
     * @param headermap 带请求头
     * @return
     * @throws IOException
     */

    //3 POST请求
    public CloseableHttpResponse post(String url,String entityString,HashMap<String,String> headermap) throws IOException {
        //创建一个可关闭的HTTP client对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        //创建一个HTTP post请求对象
        HttpPost httpPost = new HttpPost(url);
        //设置payload
        httpPost.setEntity(new StringEntity(entityString));

        //加载请求头到httppost对象
        for (Map.Entry<String,String> entry : headermap.entrySet()){
            httpPost.addHeader(entry.getKey(),entry.getValue());
        }
        //发送post
        CloseableHttpResponse httpResponse = httpClient.execute(httpPost);
        Log.info("开始发送post请求。。。");
        return httpResponse;

    }



}
