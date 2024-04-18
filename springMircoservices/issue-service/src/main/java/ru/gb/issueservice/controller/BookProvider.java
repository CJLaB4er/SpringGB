package ru.gb.issueservice.controller;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;
import org.springframework.cloud.client.loadbalancer.reactive.LoadBalancedExchangeFilterFunction;
import org.springframework.cloud.client.loadbalancer.reactive.ReactorLoadBalancerExchangeFilterFunction;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import ru.gb.issueservice.dto.BookDTO;

import java.util.List;
import java.util.Random;
import java.util.UUID;

@Component
public class BookProvider {

    private final WebClient webClient;
//    private final EurekaClient eurekaClient;

    public BookProvider(EurekaClient eurekaClient, ReactorLoadBalancerExchangeFilterFunction loadBalancerExchangeFilterFunction) {
        webClient = WebClient
                .builder()
                .filter(loadBalancerExchangeFilterFunction)
                .build();
//        this.eurekaClient = eurekaClient;
    }

    public UUID getRandomBookId() {
        BookDTO randomBook = webClient.get()
//                .uri("http://localhost:8081/book/random")
//                .uri(getBookServiceIp() + "/book/random")
                .uri("http://book-service/book/random")
                .retrieve() //метод выполняет запрос
                .bodyToMono(BookDTO.class)
                .block(); // ожидание ответа
        return randomBook.getId();
    }

//    public String getBookServiceIp() {
//        Application application = eurekaClient.getApplication("BOOK-SERVICE");
//        List<InstanceInfo> instanceInfos = application.getInstances();
//
//        Random random = new Random();
//
//        InstanceInfo instanceInfo = instanceInfos.get(random.nextInt(instanceInfos.size()));
//
//        return "http://" + instanceInfo.getIPAddr() + ":" + instanceInfo.getPort();
//    }
}
