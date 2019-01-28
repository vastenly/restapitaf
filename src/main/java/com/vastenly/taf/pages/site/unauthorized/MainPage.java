package com.vastenly.taf.pages.site.unauthorized;


public class MainPage extends BaseUnauthorizedPage {

    public final static String URL = System.getProperty("baseUrl");

    @Override
    public void checkPageContent() {
        checkUrlContains(URL);
    }

}