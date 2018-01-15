package com.gabrielspassos.poc.eureka;

import okhttp3.*;
import org.springframework.boot.context.embedded.AnnotationConfigEmbeddedWebApplicationContext;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;

@Component
public class EurekaRegistry implements ApplicationListener<ApplicationReadyEvent> {

    private EurekaModelRegistry eurekaModelRegistry;
    private WebApplicationContext webApplicationContext;
    private OkHttpClient okHttpClient;
    private final static String URL_BASE = "http://localhost:8080/eureka/v2/apps/";

    public EurekaRegistry(EurekaModelRegistry eurekaModelRegistry, WebApplicationContext webApplicationContext, OkHttpClient okHttpClient) {
        this.eurekaModelRegistry = eurekaModelRegistry;
        this.webApplicationContext = webApplicationContext;
        this.okHttpClient = okHttpClient;
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        try {
            registry();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void registry() throws IOException {

        RequestBody body = RequestBody.create(MediaType.parse("application/json"),"" +
                "{\"instance\": {" +
                "   \"hostName\": \""+ eurekaModelRegistry.getHostName() +"\"," +
                "   \"app\": \""+ eurekaModelRegistry.getAppName()+"\"," +
                "   \"vipAddress\": \""+ eurekaModelRegistry.getVipAddress()+"\"," +
                "   \"secureVipAddress\": \""+ eurekaModelRegistry.getSecureVipAddress()+"\"," +
                "   \"ipAddr\": \""+ eurekaModelRegistry.getIpAddr()+"\"," +
                "   \"status\": \""+ eurekaModelRegistry.getStatus()+"\"," +
                "   \"port\": {\"$\": \""+ getPort() +"\", \"@enabled\": \"true\"}," +
                "   \"healthCheckUrl\": \""+ createHealthUrl()+ "\"," +
                "   \"statusPageUrl\": \""+ createHealthUrl()+ "\"," +
                "   \"homePageUrl\": \""+ createHealthUrl()+ "\"," +
                "   \"dataCenterInfo\": {" +
                "       \"@class\": \""+ eurekaModelRegistry.getDataCenterInfo()+"\"," +
                "       \"name\": \""+ eurekaModelRegistry.getDataCenterName()+"\"" +
                " }}}") ;

        Request request2 = new Request.Builder().url(URL_BASE+ eurekaModelRegistry.getAppName()).post(body).build() ;
        okHttpClient.newCall(request2).execute() ;
    }

    private String createHealthUrl(){
        return "http://"+eurekaModelRegistry.getIpAddr()+":"+getPort()+"/health";

    }

    private int getPort(){
        return ((AnnotationConfigEmbeddedWebApplicationContext) webApplicationContext).getEmbeddedServletContainer().getPort();
    }



}
