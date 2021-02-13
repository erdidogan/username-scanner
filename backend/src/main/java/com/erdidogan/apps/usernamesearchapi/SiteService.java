package com.erdidogan.apps.usernamesearchapi;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;
import java.util.stream.Collectors;

@Service
@Slf4j
public class SiteService {


    private final List<Source> getSourceList;
    private final List<Source> postSourceList;
    private static final String TARGET = "{}";


    public SiteService() {
        InputStream inputStreamForGet = getClass().getResourceAsStream("/static/getSources.json");
        Source[] getSources = new Gson().fromJson(new BufferedReader(new InputStreamReader(inputStreamForGet)), Source[].class);
        getSourceList = Arrays.asList(getSources);

        InputStream inputStreamForPost = getClass().getResourceAsStream("/static/postSources.json");
        Source[] postSources = new Gson().fromJson(new BufferedReader(new InputStreamReader(inputStreamForPost)), Source[].class);
        postSourceList = Arrays.asList(postSources);


        if (!postSourceList.isEmpty() || !getSourceList.isEmpty())
            log.info("Init Source List");
        else
            log.error("Can not find or read file!");
    }


    public List<SiteResponseModel> returnGetResult(String username) {
        List<SiteResponseModel> resultList = new ArrayList<>();
        try {
            resultList = findForGetSources(username);
            log.info("Success");
        } catch (ExecutionException | InterruptedException | TimeoutException e) {
            Thread.currentThread().interrupt();
            log.error("Execution Error! " + e.getMessage());
        }
        return resultList;
    }

    public List<SiteResponseModel> returnPostResult(String username) {
        List<SiteResponseModel> resultList = new ArrayList<>();
        try {
            resultList = findForPostSources(username);
            log.info("Success");
        } catch (InterruptedException | IOException | ExecutionException e) {
            Thread.currentThread().interrupt();
            log.error("Execution Error! " + e.getMessage());
        }
        return resultList;
    }

    private List<SiteResponseModel> findForGetSources(String username) throws ExecutionException, InterruptedException, TimeoutException {
        List<SiteResponseModel> resultList = new ArrayList<>();
        HttpClientUtil httpClientUtil = new HttpClientUtil();
        List<String> uriList = getSourceList.stream().map(url -> url.getSiteUrl().replace(TARGET, username)).collect(Collectors.toList());
        List<CompletableFuture<HttpResponse<String>>> callResultList = httpClientUtil.concurrentCallForGetSource(uriList).get();
        for (int i = 0; i < getSourceList.size(); i++) {
            Source s = getSourceList.get(i);
            if (s.getMessage() == null) {
                resultList.add(new SiteResponseModel(s.getSiteName(), callResultList.get(i).get().statusCode(), s.getSiteRegisterUrl().replace(TARGET, username), s.getSiteIconUrl()));
            } else {
                int statusCode = 200;
                if (callResultList.get(i).get().body().contains(s.getMessage())) {
                    statusCode = 404;
                }
                resultList.add(new SiteResponseModel(s.getSiteName(), statusCode, s.getSiteRegisterUrl().replace(TARGET, username), s.getSiteIconUrl()));
            }
        }
        log.info("Async Get Completed");
        return resultList;
    }


    private List<SiteResponseModel> findForPostSources(String username) throws InterruptedException, IOException, ExecutionException {
        List<SiteResponseModel> resultList = new ArrayList<>();
        HttpResponse<String> response;
        HttpClientUtil httpClientUtil = new HttpClientUtil();
        int statusCode = 200;
        for (Source s : postSourceList) {
            switch (s.getSiteName()) {
                case "Instagram":
                    response = httpClientUtil.asyncCallForPostSource(s.getSiteUrl(),
                            HttpRequest.BodyPublishers.ofString("username=" + username), "application/x-www-form-urlencoded").get();
                    if (!response.body().contains(s.getErrorMessage()))
                        statusCode = 404;
                    break;
                case "Snapchat":
                    response = httpClientUtil.asyncCallForPostSource(s.getSiteUrl().replace("{}", username),
                            HttpRequest.BodyPublishers.noBody(), "application/json").get();
                    if (response.body().contains(s.getMessage()))
                        statusCode = 404;
                    break;
                case "Twitch":
                    String body = "{\"operationName\":\"UsernameValidator_User\",\"variables\":{\"username\":\"####\"},\"extensions\":{\"persistedQuery\":{\"version\":1,\"sha256Hash\":\"fd1085cf8350e309b725cf8ca91cd90cac03909a3edeeedbd0872ac912f3d660\"}}}";
                    response = httpClientUtil.asyncCallForPostSource(s.getSiteUrl(),
                            HttpRequest.BodyPublishers.ofString(body.replace("####", username)), "application/json").get();
                    if (!response.body().contains(s.getErrorMessage()))
                        statusCode = 404;
                    break;
                default:
                    log.debug("Default Case Executed");
            }
            resultList.add(new SiteResponseModel(s.getSiteName(), statusCode,
                    s.getSiteRegisterUrl().replace(TARGET, username), s.getSiteIconUrl()));

        }
        log.info("Async Post Completed");
        return resultList;
    }

}
