package com.gabrielspassos.poc.eureka;


import okhttp3.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.AnnotationConfigEmbeddedWebApplicationContext;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;
import java.net.UnknownHostException;

@Component
public class EurekaRegistry implements ApplicationListener<ApplicationReadyEvent> {

    @Autowired
    EurekaModelRegistry eurekaModelRegistry;

    @Autowired
    WebApplicationContext webApplicationContext;

    OkHttpClient client = new OkHttpClient();

    public EurekaRegistry() throws UnknownHostException {
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
                "   \"healthCheckUrl\": \""+ createUrl()+ "\"," +
                "   \"statusPageUrl\": \""+ createUrl()+ "\"," +
                "   \"homePageUrl\": \""+ createUrl()+ "\"," +
                "   \"dataCenterInfo\": {" +
                "       \"@class\": \""+ eurekaModelRegistry.getDataCenterInfo()+"\"," +
                "       \"name\": \""+ eurekaModelRegistry.getDataCenterName()+"\"" +
                " }}}") ;

        Request request2 = new Request.Builder().url("http://localhost:8080/eureka/v2/apps/"+ eurekaModelRegistry.getAppName()).post(body).build() ;
        client.newCall(request2).execute() ;
    }

    private String createUrl(){
        return "http://"+eurekaModelRegistry.getIpAddr()+":"+getPort()+"/health";

    }

    private int getPort(){
        return ((AnnotationConfigEmbeddedWebApplicationContext) webApplicationContext).getEmbeddedServletContainer().getPort();
    }



}
