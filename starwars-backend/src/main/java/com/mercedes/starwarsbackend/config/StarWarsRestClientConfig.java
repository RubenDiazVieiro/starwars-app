package com.mercedes.starwarsbackend.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.JdkClientHttpRequestFactory;
import org.springframework.web.client.RestClient;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.net.http.HttpClient;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;

@Configuration
public class StarWarsRestClientConfig {

    @Bean
    public RestClient swapiRestClient(@Value("${swapi.base-url}") String swapiUrl)
            throws NoSuchAlgorithmException, KeyManagementException {

        HttpClient httpClient = createInsecureHttpClient();
        JdkClientHttpRequestFactory factory = new JdkClientHttpRequestFactory(httpClient);

        return RestClient.builder()
                .baseUrl(swapiUrl)
                .requestFactory(factory)
                .defaultHeader("User-Agent", "Mercedes-StarWars-Backend/1.0")
                .defaultHeader("Accept", "application/json")
                .build();
    }

    private HttpClient createInsecureHttpClient() throws NoSuchAlgorithmException, KeyManagementException {
        SSLContext sslContext = createTrustAllSSLContext();

        return HttpClient.newBuilder()
                .sslContext(sslContext)
                .build();
    }

    private SSLContext createTrustAllSSLContext() throws NoSuchAlgorithmException, KeyManagementException {
        TrustManager[] trustAllCerts = { createTrustAllManager() };

        SSLContext sslContext = SSLContext.getInstance("TLS");
        sslContext.init(null, trustAllCerts, new java.security.SecureRandom());

        return sslContext;
    }

    private X509TrustManager createTrustAllManager() {
        return new X509TrustManager() {
            @Override
            public X509Certificate[] getAcceptedIssuers() {
                return new X509Certificate[0];
            }

            @Override
            public void checkClientTrusted(X509Certificate[] certs, String authType) {}

            @Override
            public void checkServerTrusted(X509Certificate[] certs, String authType) {}
        };
    }

}
