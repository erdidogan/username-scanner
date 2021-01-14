package com.erdidogan.apps.usernamesearchapi;

import lombok.Data;

@Data
public class Source {

    private String siteName;
    private String siteUrl;
    private String siteIconUrl;
    private String siteRegisterUrl;
    private String message;
    private String errorMessage;
}
