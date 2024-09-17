package ru.iteco.fmhandroid.ui.Data.Page;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isClickable;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.matcher.ViewMatchers;

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.Data.Function;


public class MenuBarPage {
    private final ViewInteraction mainMenu = onView(withId(R.id.main_menu_image_button));
    private final ViewInteraction mainButton = onView(withText("Main"));
    private final ViewInteraction newsButton = onView(withText("News"));
    private final ViewInteraction aboutButton = onView(withText("About"));
    private final ViewInteraction image = onView(withId(R.id.trademark_image_view));
    private final ViewInteraction ourMissionButton = onView(withId(R.id.our_mission_image_button));
    private final ViewInteraction profileButton = onView(withId(R.id.authorization_image_button));
    private final ViewInteraction logOut = onView(withText("Log out"));

    public void waitingPageLoad() {
        Allure.step("Ожидание загрузки страницы");
        onView(isRoot()).perform(Function.waitDisplayed(R.id.main_menu_image_button, 5000));
    }

    public void pageVisible() {
        Allure.step("Проверить видимость элементов на странице");
        mainMenu.check(matches(isDisplayed()));
        image.check(matches(isDisplayed()));
        ourMissionButton.check(matches(isDisplayed()));
        profileButton.check(matches(isDisplayed()));
    }

    public void pageClickable() {
        Allure.step("Провеить кликабельность элементов на странице");
        mainMenu.check(matches(isClickable()));
        ourMissionButton.check(matches(isDisplayed()));
        profileButton.check(matches(isClickable()));
    }

    public void openMenu() {
        Allure.step("Открыть меню");
        mainMenu.perform(click());
    }

    public void mainMenuVisible() {
        Allure.step("Проверить видимость открытого меню");
        mainButton.check(matches(isDisplayed()));
        newsButton.check(matches(isDisplayed()));
        aboutButton.check(matches(isDisplayed()));
    }

    public MainPage openMainPage() {
        Allure.step("Открыть страницу Main");
        mainMenu.perform(click());
        mainButton.perform(click());
        return new MainPage();
    }

    public NewsPage openNewsPage() {
        Allure.step("Открыть страницу News");
        waitingPageLoad();
        mainMenu.perform(click());
        newsButton.perform(click());
        return new NewsPage();
    }

    public AboutPage openAboutPage() {
        Allure.step("Открыть страницу About");
        waitingPageLoad();
        mainMenu.perform(click());
        aboutButton.perform(click());
        return new AboutPage();
    }

    public LoveIsAllPage openLoveIsAll() {
        Allure.step("Открыть страницу Love is All");
        waitingPageLoad();
        ourMissionButton.perform(click());
        onView(isRoot()).perform(Function.waitDisplayed(R.id.our_mission_title_text_view, 7000));
        return new LoveIsAllPage();
    }

    public void openProfileMenu() {
        Allure.step("Открыть меню профиля");
        profileButton.perform(click());

    }

    public void profileMenuVisible() {
        Allure.step("Проверить видимость меню профиля");
        logOut.check(matches(isDisplayed()));
        logOut.check(matches(ViewMatchers.withText("Log out")));
    }

    public AuthorizationPage exitProfile() {
        Allure.step("Выйти из профиля");
        waitingPageLoad();
        profileButton.perform(click());
        Function.waitDisplayed(android.R.id.content, 7000);
        logOut.perform(click());
        return new AuthorizationPage();
    }

}
