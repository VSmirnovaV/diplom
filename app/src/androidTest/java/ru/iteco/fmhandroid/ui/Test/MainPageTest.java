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
import ru.iteco.fmhandroid.ui.Data.Page.MainPage;
import ru.iteco.fmhandroid.ui.Data.Page.NewsPage;

@DisplayName("Сценарии тестирования MainPage")
@LargeTest
@RunWith(AllureAndroidJUnit4.class)

public class MainPageTest {
    private AuthorizationPage authPage = new AuthorizationPage();
    private MainPage mainPage = new MainPage();
    private NewsPage newsPage = new NewsPage();

    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);

    @Before
    public void setUp() {
        try {
            mainPage.waitingPageLoad();
        } catch (Exception e) {
            authPage.waitingPageLoad();
            authPage.authUser();
            mainPage.waitingPageLoad();
        }
    }

    @Test //Видимость и кликабельность элементов страницы
    public void shouldVisibleAndClickablePageElements() {
        mainPage.pageVisible();
        mainPage.pageClickable();
    }

    @Test //Развернуть карточки всех новостей поочередно
    public void shouldBeViewAllCardNews() {
        mainPage.clickView(0);
        mainPage.clickView(1);
        mainPage.clickView(2);
    }

    @Test //Развернуть карточки всех новостей одной кнопкой
    public void expandNewsCardsWithOneButton() {
        mainPage. clickViewAllNews();
    }

    @Test // Проверить, что на странице отображаются последние 3 новости
    public void shouldBeCountCardNews() {
        mainPage.childCount(3);
    }

    @Test //Перейти в раздел AllNews
    public void goToSectionNews() {
        mainPage.openAllNews();
        newsPage.waitingPageLoad();
        newsPage.pageVisible();
    }
}
