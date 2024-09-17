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
import ru.iteco.fmhandroid.ui.Data.DataHelper;
import ru.iteco.fmhandroid.ui.Data.Page.AuthorizationPage;
import ru.iteco.fmhandroid.ui.Data.Page.MenuBarPage;
import ru.iteco.fmhandroid.ui.Data.Page.NewsFilterPage;
import ru.iteco.fmhandroid.ui.Data.Page.NewsPage;


@DisplayName("Сценарии тестирования страницы NewsFilterPage")
@LargeTest
@RunWith(AllureAndroidJUnit4.class)


public class NewsFilterPageTest {
    private AuthorizationPage authPage = new AuthorizationPage();
    private NewsPage newsPage = new NewsPage();
    private MenuBarPage menuBar = new MenuBarPage();
    private NewsFilterPage newsFilter = new NewsFilterPage();

    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);

    @Before
    public void setUp() {
        try {
            menuBar.openNewsPage();
            newsPage.clickFilter();
            newsFilter.waitingPageLoad();
        } catch (Exception e) {
            authPage.authUser();
            menuBar.openNewsPage();
            newsPage.clickFilter();
            newsFilter.waitingPageLoad();
        }
    }

    @Test //Ввод валидных данных в фильтр
    public void shouldBeFilterNewsVisible() {
        newsFilter.addCategory(DataHelper.getCategory(1));
        newsFilter.addStartDate(DataHelper.generateDate(15));
        newsFilter.addEndDate(DataHelper.getCurrentDate());
        newsFilter.clickFilter();
    }

    @Test //Ввод валидных данных в фильтр, указать прошедшую дату
    public void shouldBeFilterNewsVisible2() {
        newsFilter.addCategory(DataHelper.getCategory(3));
        newsFilter.addStartDate(DataHelper.generateDate(-5));
        newsFilter.addEndDate(DataHelper.getCurrentDate());
        newsFilter.clickFilter();
    }

    @Test //заполнить только дату начала
    public void fillInStartDateOnly() {
        newsFilter.addStartDate(DataHelper.generateDate(-3));
        newsFilter.clickFilter();
    }

    @Test //заполнить только дату окончания
    public void shouldBeErrorMessageVisible() {
        newsFilter.addEndDate(DataHelper.getCurrentDate());
        newsFilter.clickFilter();
        newsFilter.errorVisible();
    }
}
