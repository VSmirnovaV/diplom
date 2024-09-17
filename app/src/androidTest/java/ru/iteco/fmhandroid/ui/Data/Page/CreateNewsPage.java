package ru.iteco.fmhandroid.ui.Data.Page;

import static androidx.test.espresso.Espresso.closeSoftKeyboard;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isClickable;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.ViewInteraction;

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.Data.DataHelper;
import ru.iteco.fmhandroid.ui.Data.Function;

public class CreateNewsPage {

    private final ViewInteraction creatingTitle = Espresso.onView(withId(R.id.custom_app_bar_title_text_view));
    private final ViewInteraction newsTitle = Espresso.onView(withId(R.id.custom_app_bar_sub_title_text_view));
    private final ViewInteraction categoryBox = Espresso.onView(withId(R.id.news_item_category_text_auto_complete_text_view));
    private final ViewInteraction categoryView = Espresso.onView(withId(com.google.android.material.R.id.text_input_end_icon));
    private final ViewInteraction titleBox = Espresso.onView(withId(R.id.news_item_title_text_input_edit_text));
    private final ViewInteraction publicationDate = Espresso.onView(withId(R.id.news_item_publish_date_text_input_edit_text));
    private final ViewInteraction time = Espresso.onView(withId(R.id.news_item_publish_time_text_input_edit_text));
    private final ViewInteraction description = Espresso.onView(withId(R.id.news_item_description_text_input_edit_text));
    private final ViewInteraction switcher = Espresso.onView(withId(R.id.switcher));
    private final ViewInteraction saveButton = Espresso.onView(withId(R.id.save_button));
    private final ViewInteraction cancelButton = Espresso.onView(withId(R.id.cancel_button));
    private final ViewInteraction cancelButtonMessage = Espresso.onView(withId(android.R.id.message));
    private final ViewInteraction cancelCancelButton = Espresso.onView(withId(android.R.id.button2));
    private final ViewInteraction cancelOkButton = Espresso.onView(withId(android.R.id.button1));
    private final ViewInteraction alertCategory = Espresso.onView(withId(com.google.android.material.R.id.text_input_start_icon));
    private final ViewInteraction alertOther = Espresso.onView(withId(com.google.android.material.R.id.text_input_end_icon));

    public void waitingPageLoad() {
        Allure.step("Ожидание загрузки страницы");
        onView(isRoot()).perform(Function.waitDisplayed(R.id.custom_app_bar_title_text_view, 7000));
    }

    public void pageVisible() {
        Allure.step("Проверить видимость элементов на странице");
        creatingTitle.check(matches(isDisplayed()));
        creatingTitle.check(matches(withText("Creating")));
        newsTitle.check(matches(isDisplayed()));
        newsTitle.check(matches(withText("News")));
        categoryBox.check(matches(isDisplayed()));
        titleBox.check(matches(isDisplayed()));
        publicationDate.check(matches(isDisplayed()));
        time.check(matches(isDisplayed()));
        description.check(matches(isDisplayed()));
        switcher.check(matches(isDisplayed()));
        switcher.check(matches(withText("Active")));
        saveButton.check(matches(isDisplayed()));
        saveButton.check(matches(withText("SAVE")));
        cancelButton.check(matches(isDisplayed()));
        cancelButton.check(matches(withText("CANCEL")));
    }

    public void pageClickable() {
        Allure.step("Проверить кликабельность элементов на странице");
        categoryBox.check(matches(isClickable()));
        titleBox.check(matches(isClickable()));
        publicationDate.check(matches(isClickable()));
        time.check(matches(isClickable()));
        description.check(matches(isClickable()));
        switcher.check(matches(isClickable()));
        saveButton.check(matches(isClickable()));
        cancelButton.check(matches(isClickable()));
    }

    public void createNewsInLatin() {
        Allure.step("Создать новость на латинице");
        categoryBox.perform(replaceText(DataHelper.getCategory(1)));
        titleBox.perform(replaceText("Car for sale"));
        publicationDate.perform(replaceText(DataHelper.generateDate(0)));
        time.perform(replaceText(DataHelper.addTime(19, 36)));
        description.perform(replaceText("Cherry Tiggo 4, mileage 100000 km"));
        saveButton.perform(click());
    }

    public void createNewsInCyrillic() {
        Allure.step("Создать новость на кирилице");
        categoryBox.perform(replaceText(DataHelper.getCategory(3)));
        titleBox.perform(replaceText("С 21 числа индексация зарплат на 5%"));
        publicationDate.perform(replaceText(DataHelper.getCurrentDate()));
        time.perform(replaceText(DataHelper.getCurrentTime()));
        description.perform(replaceText("Повышение зарплат сотрудникам, чей стаж больше 1 года"));
        closeSoftKeyboard();
        saveButton.perform(click());
    }
}
