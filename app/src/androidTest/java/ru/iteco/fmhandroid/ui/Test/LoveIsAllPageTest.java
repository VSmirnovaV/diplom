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
import ru.iteco.fmhandroid.ui.Data.Page.LoveIsAllPage;
import ru.iteco.fmhandroid.ui.Data.Page.MenuBarPage;


@DisplayName("Сценарии тестирования страницы Love is All")
@LargeTest
@RunWith(AllureAndroidJUnit4.class)

public class LoveIsAllPageTest {

    private AuthorizationPage authPage = new AuthorizationPage();
    private MenuBarPage menuBar = new MenuBarPage();
    private LoveIsAllPage loveIsAllPage = new LoveIsAllPage();
    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);

    @Before
    public void setUp() {
        try {
            menuBar.openLoveIsAll();
            loveIsAllPage.waitingPageLoad();
        } catch (Exception e) {
            authPage.authUser();
            menuBar.openLoveIsAll();
            loveIsAllPage.waitingPageLoad();
        }
    }

    @Test // проверить видимость элементов страницы
    public void shouldVisiblePageElements() {
        loveIsAllPage.pageVisible();
    }

    @Test //Развернуть карточку цитаты и проверить видимость текста
    public void ExpandQuoteCard3() {
        loveIsAllPage.clickView(2);
        loveIsAllPage.textCardVisible("Все сотрудники хосписа - это адвокаты пациента, его прав и потребностей. Поиск путей решения различных задач - это и есть хосписный индивидуальный подход к паллиативной помощи.");
    }

    @Test //Развернуть карточку цитаты и проверить видимость текста
    public void ExpandQuoteCard2() {
        loveIsAllPage.clickView(1);
        loveIsAllPage.textCardVisible("Нет шаблона и стандарта, есть только дух, который живет в разных домах по-разному. Но всегда он добрый, любящий и помогающий.");
    }
}
