package ru.iteco.fmhandroid.ui.Test;

import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasAction;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasData;

import android.content.Intent;

import androidx.test.espresso.intent.Intents;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.filters.LargeTest;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.junit4.DisplayName;
import ru.iteco.fmhandroid.ui.AppActivity;
import ru.iteco.fmhandroid.ui.Data.Page.AboutPage;
import ru.iteco.fmhandroid.ui.Data.Page.AuthorizationPage;
import ru.iteco.fmhandroid.ui.Data.Page.LoveIsAllPage;
import ru.iteco.fmhandroid.ui.Data.Page.MenuBarPage;

@DisplayName("Сценарии тестирования страницы AboutPage")
@LargeTest
@RunWith(AllureAndroidJUnit4.class)
public class AboutPageTest {
    private AuthorizationPage authPage = new AuthorizationPage();
    private MenuBarPage menuBar = new MenuBarPage();
    private AboutPage aboutPage = new AboutPage();
    private LoveIsAllPage loveIsAllPage = new LoveIsAllPage();
    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);

    @Before
    public void setUp() {
        try {
            menuBar.openAboutPage();
        } catch (Exception e) {
            authPage.authUser();
            menuBar.openAboutPage();
            aboutPage.waitingPageLoad();
        }
    }

    @Test // Проверить видимость и кликабельность элементов страницы
    public void shouldVisibleAndClickablePageElements() {
        aboutPage.pageVisible();
        aboutPage.pageClickable();
    }

    @Test // открыть ссылку Privacy Policy
    public void shouldOpeningPrivacyPolicyLink() {
        Intents.init();
        aboutPage.clickPrivacyPolicyLinc();
        intended(hasData("https://vhospice.org/#/privacy-policy"));
        intended(hasAction(Intent.ACTION_VIEW));
        Intents.release();
    }

    @Test // открыть ссылку Terms of use
    public void shouldOpeningTermsOfUseLink() {
        Intents.init();
        aboutPage.clickTermsOfUseLinc();
        intended(hasData("https://vhospice.org/#/terms-of-use"));
        intended(hasAction(Intent.ACTION_VIEW));
        Intents.release();
    }

    @Test // Вернуться на предыдущую страницу
    public void shouldReturnPage() {
        aboutPage.clickBackButton();
        menuBar.openLoveIsAll();
        menuBar.openAboutPage();
        aboutPage.pageVisible();
        aboutPage.clickBackButton();
        loveIsAllPage.pageVisible();
    }
}
