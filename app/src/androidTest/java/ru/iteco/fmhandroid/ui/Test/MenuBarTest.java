package ru.iteco.fmhandroid.ui.Test;

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
import ru.iteco.fmhandroid.ui.Data.Page.NewsPage;


@DisplayName("Сценарии тестирования меню")
@LargeTest
@RunWith(AllureAndroidJUnit4.class)

public class MenuBarTest {
    private AuthorizationPage authPage = new AuthorizationPage();
    private MenuBarPage menuBar = new MenuBarPage();
    private AboutPage aboutPage = new AboutPage();
    private NewsPage newsPage = new NewsPage();
    private LoveIsAllPage loveIsAllPage = new LoveIsAllPage();

    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);

    @Before
    public void setUp() {
        try {
            menuBar.waitingPageLoad();
        } catch (Exception e) {
            authPage.authUser();
            menuBar.waitingPageLoad();
        }
    }

    @Test //Видимость и кликабельность элементов страницы
    public void shouldVisibleAndClickablePageElements() {
        menuBar.pageVisible();
        menuBar.pageClickable();
    }

    @Test // Открыть меню
    public void shouldBeMainMenuVisible() {
        menuBar.openMenu();
        menuBar.mainMenuVisible();
    }

    @Test // Перейти на страницу News
    public void shouldBeNewsPageVisible() {
        menuBar.openNewsPage();
        newsPage.pageVisible();
    }

    @Test// перейти на страницу About
    public void shouldBeAboutPageVisible() {
        menuBar.openAboutPage();
        aboutPage.pageVisible();
    }

    @Test //Перейти на страницу Love Is All
    public void shouldBeLoveIsAllVisible() {
        menuBar.openLoveIsAll();
        loveIsAllPage.pageVisible();
    }
}
