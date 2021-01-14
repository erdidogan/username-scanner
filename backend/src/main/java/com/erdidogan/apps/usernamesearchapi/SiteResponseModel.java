package com.erdidogan.apps.usernamesearchapi;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SiteResponseModel {

    private final String siteName;
    private final int statusCode;
    private final String registerUrl;
    private final String siteIconUrl;

}
