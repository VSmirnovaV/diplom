package ru.iteco.fmhandroid.ui.Data.Page;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.isClickable;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withHint;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.not;

import android.view.View;

import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.matcher.ViewMatchers;

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.Data.DataHelper;
import ru.iteco.fmhandroid.ui.Data.Function;


public class AuthorizationPage {

    private final ViewInteraction title = onView(withText("Authorization"));
    private final ViewInteraction loginField = onView(withHint("Login"));
    private final ViewInteraction passwordField = onView(withHint("Password"));
    private final ViewInteraction button = onView(withId(R.id.enter_button));

    public void waitingPageLoad() {
        Allure.step("Ожидание загрузки страницы");
        onView(isRoot()).perform(Function.waitDisplayed(R.id.enter_button, 5000));
    }

    public void pageVisible() {
        Allure.step("Проверить видимость элементов на странице");
        title.check(matches(isDisplayed()));
        loginField.check(matches(isDisplayed()));
        passwordField.check(matches(isDisplayed()));
        button.check(matches(isDisplayed()));
        button.check(matches(ViewMatchers.withText("SIGN IN")));
    }

    public void pageClickable() {
        Allure.step("Проверить кликабельность элементов на странице");
        loginField.check(matches(isClickable()));
        passwordField.check(matches(isClickable()));
        button.check(matches(isClickable()));
    }

    public void addLogin(String login) {
        Allure.step("Ввести логин");
        loginField.perform(replaceText(login), closeSoftKeyboard());
    }

    public void deleteLogin() {
        Allure.step("Удалить логин");
        loginField.perform(clearText(), closeSoftKeyboard());
    }

    public void addPassword(String password) {
        Allure.step("Ввести пароль");
        passwordField.perform(replaceText(password), closeSoftKeyboard());
    }

    public void deletePassword() {
        Allure.step("Удалить пароль");
        passwordField.perform(clearText(), closeSoftKeyboard());
    }

    public void authUser() {
        Allure.step("Авторизация");
        onView(isRoot()).perform(Function.waitDisplayed(R.id.login_text_input_layout, 5000));
        addLogin(DataHelper.getValidLogin());
        addPassword(DataHelper.getValidPassword());
        clickButton();
    }

    public void clickButton() {
        Allure.step("Нажать кнопку SIGN IN");
        button.perform(click());
    }

    public void errorMessageText(String text, View decorView) {
        Allure.step("Окно с текстом ошибки");
        onView(withText(text))
                .inRoot(withDecorView(not(decorView)))
                .check(matches(isDisplayed()));
    }
}
