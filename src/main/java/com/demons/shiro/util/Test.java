package com.demons.shiro.util;

import javax.net.ssl.*;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import cn.com.client.DecodeClient;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.cert.X509Certificate;

public class Test {
    public static void main(String[] args) throws IOException, Exception {
        String url="https://219.232.196.202:8488/PublicTripProvide/LoadBusRealDataJson?ask=di8930285498&route=533&updown=0&statseq=16";
        String a = "aaaaaa";
        System.out.println(sendHtpps(a,url));
        String json = sendHtpps(a,url);
        JSONObject s1 = JSONObject.parseObject(json);
		 String string = s1.getString("statTimeList");
		 String strings= s1.getString("msKey");
		System.out.println(strings+"获取车辆线路关系的车辆"+string);
		JSONArray arr = JSONArray.parseArray(string);
       for(int i=0;i<arr.size();i++) {
            JSONObject ob = arr.getJSONObject(i);
            //500
            System.out.println(ob.getString("vehCode"));
            String str = DecodeClient.decryptMsgContent("di8930285498 ", strings,
           		 ob.getString("nextStatSeq").toString());
            String nextDistance = DecodeClient.decryptMsgContent("di8930285498 ", strings,
              		 ob.getString("nextDistance").toString());
            System.out.println("结果："+str+"--->"+nextDistance);
       }
    }
    
    private final static HostnameVerifier DO_NOT_VERIFY = new HostnameVerifier() {
        public boolean verify(String hostname, SSLSession session) {
            return true;
        }
    };

    private static void trustAllHosts() {
        // Create a trust manager that does not validate certificate chains
        TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
            public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                return new java.security.cert.X509Certificate[]{};
            }

            public void checkClientTrusted(X509Certificate[] chain, String authType) {
            }

            public void checkServerTrusted(X509Certificate[] chain, String authType) {
            }
        }};
        // Install the all-trusting trust manager
        try {
            SSLContext sc = SSLContext.getInstance("TLS");
            sc.init(null, trustAllCerts, new java.security.SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String sendHtpps(String a,String url) {
        String result = "";
        OutputStreamWriter out = null;
        BufferedReader in = null;
        HttpURLConnection conn;
        try {
            trustAllHosts();
            URL realUrl = new URL(url);
            //通过请求地址判断请求类型(http或者是https)
            if (realUrl.getProtocol().toLowerCase().equals("https")) {
                HttpsURLConnection https = (HttpsURLConnection) realUrl.openConnection();
                https.setHostnameVerifier(DO_NOT_VERIFY);
                conn = https;
            } else {
                conn = (HttpURLConnection) realUrl.openConnection();
            }
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            conn.setRequestProperty("Content-Type", "text/plain;charset=utf-8");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new OutputStreamWriter(conn.getOutputStream(), "utf-8");
            out.write(a);
            //错误方式，这种方式容易出现乱码
            // PrintWriter out = new PrintWriter(connection.getOutputStream());  
            /*out.print(a);*/
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {// 使用finally块来关闭输出流、输入流
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return result;
    }
}

