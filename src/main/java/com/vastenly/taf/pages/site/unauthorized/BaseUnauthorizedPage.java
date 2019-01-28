package com.vastenly.taf.pages.site.unauthorized;

import com.vastenly.taf.pages.BasePage;
import com.vastenly.taf.system.SelenideWrapper;

public abstract class BaseUnauthorizedPage extends BasePage {

    @Override
    public void checkPageContent() {
        if (SelenideWrapper.isMobile()) {
            checkPageContentMobile();
        } else {
            checkPageContentDesktop();
        }
    }

    private void checkPageContentDesktop() {
    }

    private void checkPageContentMobile() {
    }
}
