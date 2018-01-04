package com.gabrielspassos.poc.eureka;

import com.gabrielspassos.poc.exceptions.FileNotFound;
import com.gabrielspassos.poc.infra.PropretiesReader;
import java.io.IOException;

public class EurekaModelRegistry {

    PropretiesReader propretiesReader = new PropretiesReader();

    private final String hostName = "music";
    private final String appName =  "music-service";
    private final String vipAddress =   "com.gabrielspassos.poc";
    private final String secureVipAddress =  "com.gabrielspassos.poc";
    private final Integer port;
    private final String ipAddr = "127.0.0.1";
    private final String status = "UP";
    private final String healthCheckUrl = "http://localhost:9000/musics";
    private final String statusPageUrl = "http://localhost:9000/musics";
    private final String homePageUrl = "http://localhost:9000/musics";
    private final String dataCenterInfo = "com.netflix.appinfo.InstanceInfo$DefaultDataCenterInfo";
    private final String dataCenterName = "MyOwn";

    public EurekaModelRegistry(){
        try {
            this.port = Integer.valueOf(propretiesReader.getPropValues());
        }catch (IOException e){
            throw new FileNotFound();
        }
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

    public int getPort() {
        return port;
    }

    public String getHealthCheckUrl() {
        return healthCheckUrl;
    }

    public String getStatusPageUrl() {
        return statusPageUrl;
    }

    public String getHomePageUrl() {
        return homePageUrl;
    }

    public String getDataCenterInfo() {
        return dataCenterInfo;
    }

    public String getDataCenterName() {
        return dataCenterName;
    }
}
