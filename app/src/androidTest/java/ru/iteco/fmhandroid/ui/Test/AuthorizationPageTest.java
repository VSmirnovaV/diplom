package ru.iteco.fmhandroid.ui.Test;

import android.view.View;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.filters.LargeTest;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.junit4.DisplayName;
import ru.iteco.fmhandroid.ui.AppActivity;
import ru.iteco.fmhandroid.ui.Data.DataHelper;
import ru.iteco.fmhandroid.ui.Data.Page.AuthorizationPage;
import ru.iteco.fmhandroid.ui.Data.Page.MainPage;
import ru.iteco.fmhandroid.ui.Data.Page.MenuBarPage;


@DisplayName("Сценарии тестирования страницы авторизации")
@LargeTest
@RunWith(AllureAndroidJUnit4.class)
public class AuthorizationPageTest {
    private AuthorizationPage authPage = new AuthorizationPage();
    private MenuBarPage menuBar = new MenuBarPage();
    private MainPage mainPage = new MainPage();
    private View decorView;

    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);

    @Before
    public void setUp() {
        try {
            authPage.waitingPageLoad();
        } catch (Exception e) {
            menuBar.exitProfile();
            authPage.waitingPageLoad();
        }
    }

    @After
    public void after1() {
        try {
            menuBar.exitProfile();
        } catch (Exception e) {
        }
    }

    @Test // Проверить видимость и кликабельность элементов страницы
    public void shouldVisibleAndClickablePageElements() {
        authPage.pageVisible();
        authPage.pageClickable();
    }

    @Test // Авторизация с валидными данными
    public void authInValidData() {
        authPage.addLogin(DataHelper.getValidLogin());
        authPage.addPassword(DataHelper.getValidPassword());
        authPage.clickButton();
        mainPage.pageVisible();
        menuBar.pageVisible();
    }

    @Test // Авторизация с неверным логином и валидным паролем
    public void authWrongLogin() {
        authPage.addLogin(DataHelper.generateInvalidLogin("en"));
        authPage.addPassword(DataHelper.getValidPassword());
        authPage.clickButton();
        authPage.errorMessageText("Something went wrong. Try again later.", decorView);
    }

    @Test // Авторизация с валидным логином и неверным паролем
    public void authWrongPassword() {
        authPage.addLogin(DataHelper.getValidLogin());
        authPage.addPassword(DataHelper.generateInvalidPassword("en"));
        authPage.clickButton();
        authPage.errorMessageText("Something went wrong. Try again later.", decorView);
    }

    @Test // Не заполнять поля логин и пароль
    public void emptyFieldLoginAndPassword() {
        authPage.clickButton();
        authPage.errorMessageText("Login and password cannot be empty", decorView);
    }

    @Test // Не заполнять поле логин, в поле пароль ввести валидный пароль
    public void emptyFieldLogin() {
        authPage.addPassword(DataHelper.getValidPassword());
        authPage.clickButton();
        authPage.errorMessageText("Login and password cannot be empty", decorView);
    }

    @Test // Не заполнять поле пароль, в поле логин ввести валидный логин
    public void emptyFieldPassword() {
        authPage.addLogin(DataHelper.getValidLogin());
        authPage.clickButton();
        authPage.errorMessageText("Login and password cannot be empty", decorView);
    }

    @Test // Ввод пробела перед валидными логином и паролем
    public void InputSpaces() {
        authPage.addLogin(DataHelper.getValidLoginWithSpase());
        authPage.addPassword(DataHelper.getValidPasswordWithSpase());
        authPage.clickButton();
        authPage.errorMessageText("Something went wrong. Try again later.", decorView);
    }

    @Test //Ввод неверного логина и пароля на кириллице
    public void inputLoginAndPasswordInCyrillic() {
        authPage.addLogin(DataHelper.generateInvalidLogin("ru"));
        authPage.addPassword(DataHelper.generateInvalidPassword("ru"));
        authPage.clickButton();
        authPage.errorMessageText("Something went wrong. Try again later.", decorView);
    }

    @Test //Ввод неверного логина и пароля на латинице
    public void inputLoginAndPasswordInLatin() {
        authPage.addLogin(DataHelper.generateInvalidLogin("en"));
        authPage.addPassword(DataHelper.generateInvalidPassword("en"));
        authPage.clickButton();
        authPage.errorMessageText("Something went wrong. Try again later.", decorView);
    }

    @Test
    public void inputSpecialSings() { // ввод спецсимволов в поля логин и пароль
        authPage.addLogin(DataHelper.generateInvalidString(7));
        authPage.addPassword(DataHelper.generateInvalidString(7));
        authPage.clickButton();
        authPage.errorMessageText("Something went wrong. Try again later.", decorView);
    }

    @Test
    public void inputNumbers() { // ввод цифр в поля логин и пароль
        authPage.addLogin(DataHelper.generateNumber(5));
        authPage.addPassword(DataHelper.generateNumber(5));
        authPage.clickButton();
        authPage.errorMessageText("Something went wrong. Try again later.", decorView);
    }
}
