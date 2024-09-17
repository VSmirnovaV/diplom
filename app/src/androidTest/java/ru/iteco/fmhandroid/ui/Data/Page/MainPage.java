package ru.iteco.fmhandroid.ui.Data.Page;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.doesNotExist;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.matcher.ViewMatchers.hasChildCount;
import static androidx.test.espresso.matcher.ViewMatchers.isClickable;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.core.IsNot.not;

import androidx.test.espresso.ViewInteraction;

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.Data.Function;


public class MainPage {
    private final ViewInteraction title = onView(withText("News"));
    private final ViewInteraction allNewsButton = onView(withId(R.id.all_news_text_view));
    private final ViewInteraction cardNews = onView(Function.childAtPosition(allOf(withId
            (R.id.all_news_cards_block_constraint_layout)), 0));
    private final ViewInteraction imageNews = onView(withId(R.id.category_icon_image_view));
    private final ViewInteraction dateNews = onView(withId(R.id.news_item_date_text_view));
    private final ViewInteraction titleNews = onView(withId(R.id.news_item_title_text_view));
    private final ViewInteraction textNews = onView(withId(R.id.news_item_description_text_view));
    private final ViewInteraction viewAllNewsButton = onView(withId(R.id.expand_material_button));
    private final ViewInteraction viewNewsImage = onView(withId(R.id.view_news_item_image_view));
    private final ViewInteraction viewNewsButton = onView(withId(R.id.news_list_recycler_view));

    public void waitingPageLoad() {
        Allure.step("Ожидание загрузки страницы");
        onView(isRoot()).perform(Function.waitDisplayed(R.id.all_news_text_view, 7000));
    }

    public void pageVisible() {
        Allure.step("Проверить видимость элементов на странице");
        title.check(matches(isDisplayed()));
        allNewsButton.check(matches(isDisplayed()));
        allNewsButton.check(matches(withText("ALL NEWS")));
        viewAllNewsButton.check(matches(isDisplayed()));
    }

    public void pageClickable() {
        Allure.step("Проверить кликабельность элементов на странице");
        allNewsButton.check(matches(isClickable()));
        viewAllNewsButton.check(matches(isClickable()));
    }

    public void clickViewAllNews() {
        Allure.step("Развернуть/свернуть все новости");
        viewAllNewsButton.perform((click()));
    }

    public void clickView(int position) {
        Allure.step("Развернуть карточку новости");
        cardNews.perform(actionOnItemAtPosition(position, click()));
        onView(isRoot()).perform(Function.waitDisplayed(R.id.news_item_description_text_view, 7000));
    }

    public void childCount(int child) {
        Allure.step("Проверить количество карточек новостей");
        cardNews.check(matches(hasChildCount(child)));
    }

    public void textNewsVisible() {
        Allure.step("Проверить видимость текста новости после разворачивания блока новости");
        textNews.check(matches(isDisplayed()));
    }

    public void textNewsInvisible() {
        Allure.step("Проверить видимость текста новости после сворачивания блока новости");
        textNews.check(matches(not(isDisplayed())));
    }

    public NewsPage openAllNews() {
        Allure.step("Перейти в раздел All news");
        allNewsButton.perform(click());
        return new NewsPage();
    }
}