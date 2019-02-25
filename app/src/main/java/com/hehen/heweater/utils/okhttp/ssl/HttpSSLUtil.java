package com.hehen.heweater.utils.okhttp.ssl;

import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.X509TrustManager;

/**
 * @Description
 * @auther ping
 * @create 2019-02-07 8:57 AM
 * @function "功能说明"
 */
public class HttpSSLUtil {
    public static SSLSocketFactory getSslSoketFactory(){
        //生成一个信任管理
        X509TrustManager trustManager = new X509TrustManager() {
            @Override
            public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {

            }

            @Override
            public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {

            }

            @Override
            public X509Certificate[] getAcceptedIssuers() {
                return new X509Certificate[0];
            }
        };
        //创建加密上下文
        SSLContext sslContext = null;
        try{
            sslContext = SSLContext.getInstance("SSL");
            X509TrustManager[] x509TrustArray = new X509TrustManager[]{trustManager};
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return sslContext.getSocketFactory();
    }

}
