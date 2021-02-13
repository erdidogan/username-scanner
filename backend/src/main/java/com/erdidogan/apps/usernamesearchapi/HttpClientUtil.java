package com.erdidogan.apps.usernamesearchapi;

import org.springframework.http.HttpHeaders;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;


public class HttpClientUtil {

    private static final String USER_AGENT = "Mozilla/5.0 Firefox/26.0";

    private final HttpClient httpClient;

    public HttpClientUtil() {

        this.httpClient = HttpClient.newBuilder()
                .followRedirects(HttpClient.Redirect.NEVER)
                .version(HttpClient.Version.HTTP_2)
                .connectTimeout(Duration.ofSeconds(10))
                .build();


    }

    @Async
    CompletableFuture<List<CompletableFuture<HttpResponse<String>>>> concurrentCallForGetSource(List<String> uriList) {

        return CompletableFuture.completedFuture(uriList.stream()
                .map(url -> httpClient.sendAsync(
                        HttpRequest.newBuilder(URI.create(url))
                                .GET()
                                .setHeader(HttpHeaders.USER_AGENT, USER_AGENT)
                                .build(),
                        HttpResponse.BodyHandlers.ofString())
                        .thenApply(stringHttpResponse -> stringHttpResponse))
                .collect(Collectors.toList()));
    }

    @Async
    CompletableFuture<HttpResponse<String>> asyncCallForPostSource(String url, HttpRequest.BodyPublisher bodyPublisher, String contentType) {


        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .setHeader(HttpHeaders.USER_AGENT, USER_AGENT)
                .header("Client-Id", "kimne78kx3ncx6brgo4mv6wki5h1ko")
                .header("Content-Type", contentType)
                .header("Cookie", "xsrf_token=PlEcin8s5H600toD4Swngg")
                .header("x-csrftoken", "oyMLA34p6Q6qZkfXMqGmsK62Jouv5Xpj")
                .POST(bodyPublisher)
                .build();

        return httpClient.sendAsync(request,
                HttpResponse.BodyHandlers.ofString());

    }
}
