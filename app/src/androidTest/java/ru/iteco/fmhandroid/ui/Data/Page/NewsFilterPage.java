package ru.iteco.fmhandroid.ui.Data.Page;import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.espresso.ViewInteraction;

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.Data.Function;

public class NewsFilterPage {
    private final ViewInteraction title = onView(withId(R.id.filter_news_title_text_view));
    private final ViewInteraction category = onView(withId(R.id.news_item_category_text_auto_complete_text_view));
    private final ViewInteraction viewCategory = onView(withId(com.google.android.material.R.id.text_input_end_icon));
    private final ViewInteraction startDate = onView(withId(R.id.news_item_publish_date_start_text_input_edit_text));
    private final ViewInteraction endDate = onView(withId(R.id.news_item_publish_date_end_text_input_edit_text));
    private final ViewInteraction filterButton = onView(withId(R.id.filter_button));
    private final ViewInteraction cancelButtonFilter = onView(withId(R.id.cancel_button));
    private final ViewInteraction emptyNewsImage = onView(withId(R.id.empty_news_list_image_view));
    private final ViewInteraction emptyNewsTitle = onView(withId(R.id.empty_news_list_text_view));
    private final ViewInteraction refreshButton = onView(withId(R.id.news_retry_material_button));
    private final ViewInteraction errorMessage = onView(withId(android.R.id.message));
    private final ViewInteraction errorMessageOkButton = onView(withId(android.R.id.button1));

    public void waitingPageLoad() {
        Allure.step("Ожидание загрузки страницы");
        onView(isRoot()).perform(Function.waitDisplayed(R.id.filter_news_title_text_view, 7000));
    }

    public void pageVisible() {
        Allure.step("Проверить видимость элементов на странице");
        title.check(matches(isDisplayed()));
        title.check(matches(withText("Filter news")));
        category.check(matches(isDisplayed()));
        startDate.check(matches(isDisplayed()));
        endDate.check(matches(isDisplayed()));
        filterButton.check(matches(isDisplayed()));
        filterButton.check(matches(withText("FILTER")));
        cancelButtonFilter.check(matches(isDisplayed()));
        cancelButtonFilter.check(matches(withText("CANCEL")));
    }

    public void addCategory(String data) {
        Allure.step("Ввести категории");
        category.perform(replaceText(data));
    }

    public void addStartDate(String value) {
        Allure.step("Ввести дату начала");
        startDate.perform(replaceText(value), closeSoftKeyboard());
    }

    public void addEndDate(String value) {
        Allure.step("Ввести дату окончания");
        endDate.perform(replaceText(value), closeSoftKeyboard());
    }

    public void clickFilter() {
        Allure.step("Нажать на кнопку FILTER");
        filterButton.perform(click());
    }

    public void errorVisible() {
        Allure.step("Видимость модального окна с текстом ошибки");
        errorMessage.check(matches(isDisplayed()));
        errorMessage.check(matches(withText("Wrong period")));
        errorMessageOkButton.check(matches(isDisplayed()));
        errorMessageOkButton.check(matches(withText("OK")));
    }
}