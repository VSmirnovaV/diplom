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
import ru.iteco.fmhandroid.ui.Data.Page.AuthorizationPage;
import ru.iteco.fmhandroid.ui.Data.Page.ControlPanelPage;
import ru.iteco.fmhandroid.ui.Data.Page.MenuBarPage;
import ru.iteco.fmhandroid.ui.Data.Page.NewsFilterPage;
import ru.iteco.fmhandroid.ui.Data.Page.NewsPage;


@DisplayName("Сценарии тестирования страницы News")
@LargeTest
@RunWith(AllureAndroidJUnit4.class)

public class NewsPageTest {
    private AuthorizationPage authPage = new AuthorizationPage();
    private NewsPage newsPage = new NewsPage();
    private MenuBarPage menuBar = new MenuBarPage();
    private NewsFilterPage filterPage = new NewsFilterPage();
    private ControlPanelPage controlPanel = new ControlPanelPage();

    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);

    @Before
    public void setUp() {
        try {
            menuBar.openNewsPage();
            newsPage.waitingPageLoad();
        } catch (Exception e) {
            authPage.authUser();
            menuBar.openNewsPage();
            newsPage.waitingPageLoad();
        }
    }

    @Test //Видимость элементов страницы
    public void shouldVisiblePageElements() {
        newsPage.pageVisible();
    }

    @Test //Сортировка новостей
    public void shouldBeSortedElements() {
        newsPage.clickSort();
    }

    @Test //Открыть фильтр
    public void shouldVisibleFilterPageElements() {
        newsPage.clickFilter();
        filterPage.pageVisible();
    }

    @Test // Открыть панель Control Panel
    public void pageVisibleControlPanel() {
        newsPage.clickControlPanel();
        controlPanel.pageVisible();
    }
}
