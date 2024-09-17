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
import ru.iteco.fmhandroid.ui.Data.Page.ControlPanelFilterPage;
import ru.iteco.fmhandroid.ui.Data.Page.ControlPanelPage;
import ru.iteco.fmhandroid.ui.Data.Page.MenuBarPage;
import ru.iteco.fmhandroid.ui.Data.Page.NewsPage;


@DisplayName("Сценарии тестирования страницы ControlPanelFilter")
@LargeTest
@RunWith(AllureAndroidJUnit4.class)

public class FilterOnControlPanelPageTest {
    private AuthorizationPage authPage = new AuthorizationPage();
    private NewsPage newsPageTest = new NewsPage();
    private MenuBarPage menuBar = new MenuBarPage();
    private ControlPanelPage controlPanel = new ControlPanelPage();
    private ControlPanelFilterPage controlPanelFilterPage = new ControlPanelFilterPage();

    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);

    @Before
    public void setUp() {
        try {
            menuBar.waitingPageLoad();
            menuBar.openNewsPage();
            newsPageTest.clickControlPanel();
            controlPanel.clickFilter();
            controlPanelFilterPage.waitingPageLoad();
        } catch (Exception e) {
            authPage.authUser();
            menuBar.openNewsPage();
            newsPageTest.clickControlPanel();
            controlPanel.clickFilter();
            controlPanelFilterPage.waitingPageLoad();
        }
    }

    @Test //Проверить видимость и кликабельность элементов страницы
    public void shouldVisibleAndClickablePageElements() {
        controlPanelFilterPage.pageVisible();
        controlPanelFilterPage.pageClicable();
    }

    @Test // Проверить видимость текста ошибки при фильтрации только по дате начала
    public void shouldBeErrorTextWhenFilteringOnlyByStartDate() {
        controlPanelFilterPage.addStartDate(DataHelper.getCurrentDate());
        controlPanelFilterPage.clickFilter();
        controlPanelFilterPage.errorVisible();
        controlPanelFilterPage.clickErrorMessageOkButton();
        controlPanelFilterPage.pageVisible();
    }

    @Test // фильтр новостей с указанием будущих дат
    public void ShouldBeRefreshButtonVisible() {
        controlPanelFilterPage.addCategory(DataHelper.getCategory(2));
        controlPanelFilterPage.addStartDate(DataHelper.generateDate(70));
        controlPanelFilterPage.addEndDate(DataHelper.generateDate(79));
        controlPanelFilterPage.clickFilter();
    }

    @Test // Фильтр новостей с чек-боксом Active
    public void shouldBeFilteringWithCheckBoxActive() {
        controlPanelFilterPage.addCategory(DataHelper.getCategory(5));
        controlPanelFilterPage.addStartDate(DataHelper.generateDate(5));
        controlPanelFilterPage.addEndDate(DataHelper.generateDate(9));
        controlPanelFilterPage.clickActiveCheckBox();
        controlPanelFilterPage.clickFilter();
    }

    @Test // дата начала > даты окончания
    public void DateStartMoreDateEnd() {
        controlPanelFilterPage.addCategory(DataHelper.getCategory(5));
        controlPanelFilterPage.addStartDate(DataHelper.generateDate(5));
        controlPanelFilterPage.addEndDate(DataHelper.generateDate(3));
        controlPanelFilterPage.clickFilter();
        controlPanelFilterPage.errorVisible();
    }

    @Test // фильтр без выбора категории
    public void filterWithoutCategorySelection() {
        controlPanelFilterPage.addStartDate(DataHelper.generateDate(5));
        controlPanelFilterPage.addEndDate(DataHelper.generateDate(8));
        controlPanelFilterPage.clickFilter();
    }

    @Test //Отмена применения фильтрации
    public void shoutBeFilterClosureAndControlPanelPageVisible() {
        controlPanelFilterPage.addCategory(DataHelper.getCategory(5));
        controlPanelFilterPage.addStartDate(DataHelper.generateDate(-770));
        controlPanelFilterPage.addEndDate(DataHelper.generateDate(2));
        controlPanelFilterPage.clickCancel();
        controlPanel.pageVisible();
    }
}
