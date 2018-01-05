package com.gabrielspassos.poc.eureka;

import org.springframework.stereotype.Component;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Random;

@Component
public class EurekaModelRegistry {

    private final String hostName = "music"+getRandomNumber();
    private final String appName =  "music-service";
    private final String vipAddress =   "com.gabrielspassos.poc";
    private final String secureVipAddress =  "com.gabrielspassos.poc";
    private String ipAddr = InetAddress.getLocalHost().getHostName();
    private final String status = "UP";
    private final String dataCenterInfo = "com.netflix.appinfo.InstanceInfo$DefaultDataCenterInfo";
    private final String dataCenterName = "MyOwn";

    public EurekaModelRegistry() throws UnknownHostException {

    }

    public String getHostName() {
        return hostName;
    }

    public String getAppName() {
        return appName;
    }

    public String getVipAddress() {
        return vipAddress;
    }

    public String getSecureVipAddress() {
        return secureVipAddress;
    }

    public String getIpAddr() {
        return ipAddr;
    }

    public String getStatus() {
        return status;
    }

    public String getDataCenterInfo() {
        return dataCenterInfo;
    }

    public String getDataCenterName() {
        return dataCenterName;
    }

    private String getRandomNumber(){
        Random rand = new Random();
        return String.valueOf(rand.nextInt(100));
    }


}
