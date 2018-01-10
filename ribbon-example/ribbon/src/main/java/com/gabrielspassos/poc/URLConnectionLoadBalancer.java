package com.gabrielspassos.poc;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import rx.Observable;

import com.google.common.collect.Lists;

import com.netflix.loadbalancer.BaseLoadBalancer;
import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.LoadBalancerBuilder;
import com.netflix.loadbalancer.LoadBalancerStats;
import com.netflix.loadbalancer.Server;
import com.netflix.loadbalancer.reactive.LoadBalancerCommand;
import com.netflix.loadbalancer.reactive.ServerOperation;

public class URLConnectionLoadBalancer {

    private final ILoadBalancer loadBalancer;

    private URLConnectionLoadBalancer(List<Server> serverList) {
        loadBalancer = LoadBalancerBuilder.newBuilder().buildFixedServerListLoadBalancer(serverList);
    }

    private String call(final String path) throws Exception {
        return LoadBalancerCommand.<String>builder()
                .withLoadBalancer(loadBalancer)
                .build()
                .submit(new ServerOperation<String>() {
                    @Override
                    public Observable<String> call(Server server) {
                        URL url;
                        try {
                            url = new URL("http://" + server.getHost() + ":" + server.getPort() + path);
                            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                            return Observable.just(conn.getResponseMessage());
                        } catch (Exception e) {
                            return Observable.error(e);
                        }
                    }
                }).toBlocking().first();
    }

    private LoadBalancerStats getLoadBalancerStats() {
        return ((BaseLoadBalancer) loadBalancer).getLoadBalancerStats();
    }

    public static void main(String[] args) throws Exception {
        URLConnectionLoadBalancer urlLoadBalancer = new URLConnectionLoadBalancer(Lists.newArrayList(
                new Server("localhost", 9020),
                new Server("localhost", 9021)));

        for (int i = 0; i < 10; i++) {
            System.out.println(urlLoadBalancer.call("/test"));
        }
        System.out.println("=== Load balancer stats ===");
        System.out.println(urlLoadBalancer.getLoadBalancerStats());
    }
}

