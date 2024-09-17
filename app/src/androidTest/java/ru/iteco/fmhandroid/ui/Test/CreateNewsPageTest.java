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
import ru.iteco.fmhandroid.ui.Data.Page.CreateNewsPage;
import ru.iteco.fmhandroid.ui.Data.Page.MenuBarPage;
import ru.iteco.fmhandroid.ui.Data.Page.NewsPage;

@DisplayName("Сценарии тестирования страницы ControlPanelCreatePageTest")
@LargeTest
@RunWith(AllureAndroidJUnit4.class)

public class CreateNewsPageTest {
    private AuthorizationPage authPage = new AuthorizationPage();
    private NewsPage newsPage = new NewsPage();
    private MenuBarPage menuBar = new MenuBarPage();
    private ControlPanelPage controlPanel = new ControlPanelPage();
    private CreateNewsPage createNews = new CreateNewsPage();

    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);

    @Before
    public void setUp() {
        try {
            menuBar.openNewsPage();
            newsPage.clickControlPanel();
            controlPanel.clickCreate();
            createNews.waitingPageLoad();
        } catch (Exception e) {
            authPage.authUser();
            menuBar.openNewsPage();
            newsPage.clickControlPanel();
            controlPanel.clickCreate();
            createNews.waitingPageLoad();
        }
    }

    @Test //Проверить видимость и кликабельность элементов страницы
    public void shouldVisiblePageElements() {
        createNews.pageVisible();
        createNews.pageClickable();
    }

    @Test// создание новости на латинице
    public void createNewsInLatin() {
        createNews.createNewsInLatin();
        controlPanel.waitingPageLoad();
        controlPanel.titleNewsVisible("Car for sale");
    }

    @Test// создание новости на кириллице
    public void createNewsInCyrillic() {
        createNews.createNewsInCyrillic();
        controlPanel.waitingPageLoad();
        controlPanel.titleNewsVisible("С 21 числа индексация зарплат на 5%");
    }
}
