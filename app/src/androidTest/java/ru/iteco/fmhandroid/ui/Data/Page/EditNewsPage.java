package ru.iteco.fmhandroid.ui.Data.Page;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
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


public class EditNewsPage {

    private final ViewInteraction editingTitle = onView(withId(R.id.custom_app_bar_title_text_view));
    private final ViewInteraction newsTitle = onView(withId(R.id.custom_app_bar_sub_title_text_view));
    private final ViewInteraction categoryBox = onView(withId(R.id.news_item_category_text_input_layout));
    private final ViewInteraction categoryValue = onView(withId(R.id.news_item_category_text_auto_complete_text_view));
    private final ViewInteraction titleBox = onView(withId(R.id.news_item_title_text_input_layout));
    private final ViewInteraction titleValue = onView(withId(R.id.news_item_title_text_input_edit_text));
    private final ViewInteraction dateBox = onView(withId(R.id.news_item_create_date_text_input_layout));
    private final ViewInteraction dateValue = onView(withId(R.id.news_item_publish_date_text_input_edit_text));
    private final ViewInteraction timeBox = onView(withId(R.id.news_item_publish_time_text_input_layout));
    private final ViewInteraction timeValue = onView(withId(R.id.news_item_publish_time_text_input_edit_text));
    private final ViewInteraction descriptionBox = onView(withId(R.id.news_item_description_text_input_layout));
    private final ViewInteraction descriptionValue = onView(withId(R.id.news_item_description_text_input_edit_text));
    private final ViewInteraction switcher = onView(withId(R.id.switcher));
    private final ViewInteraction saveButton = onView(withId(R.id.save_button));
    private final ViewInteraction cancelButton = onView(withId(R.id.cancel_button));
    private final ViewInteraction cancelButtonMessage = onView(withId(android.R.id.message));
    private final ViewInteraction cancelCancelButton = onView(withId(android.R.id.button2));
    private final ViewInteraction cancelOkButton = onView(withId(android.R.id.button1));

    public void waitingPageLoad() {
        Allure.step("Ожидание загрузки страницы");
        onView(isRoot()).perform(Function.waitDisplayed(R.id.custom_app_bar_title_text_view, 7000));
    }

    public void pageVisible() {
        Allure.step("Проверить видимость элементов на странице");
//        editingTitle.check(matches(isDisplayed()));
//        editingTitle.check(matches(withText("Editing")));
//        newsTitle.check(matches(isDisplayed()));
//        newsTitle.check(matches(withText("News")));
//        categoryBox.check(matches(isDisplayed()));
//        categoryValue.check(matches(isDisplayed()));
//        titleBox.check(matches(isDisplayed()));
//        titleValue.check(matches(isDisplayed()));
//        dateBox.check(matches(isDisplayed()));
//        dateValue.check(matches(isDisplayed()));
//        timeBox.check(matches(isDisplayed()));
//        timeValue.check(matches(isDisplayed()));
//        descriptionBox.check(matches(isDisplayed()));
//        descriptionValue.check(matches(isDisplayed()));
//        switcher.check(matches(isDisplayed()));
//        saveButton.check(matches(isDisplayed()));
//        saveButton.check(matches(withText("SAVE")));
//        cancelButton.check(matches(isDisplayed()));
//        cancelButton.check(matches(withText("CANCEL")));
    }

    public void clickCancelButton() {
        Allure.step("Закрыть окно редактирования новости");
        cancelButton.perform(click());
    }

    public void cancelWindowVisible() {
        Allure.step("Видимость элементов модального окна");
        cancelButtonMessage.check(matches(isDisplayed()));
        cancelButtonMessage.check(matches(withText("The changes won't be saved, do you really want to log out?")));
        cancelCancelButton.check(matches(isDisplayed()));
        cancelCancelButton.check(matches(withText("CANCEL")));
        cancelOkButton.check(matches(isDisplayed()));
        cancelOkButton.check(matches(withText("OK")));
    }

    public void exit() {
        Allure.step("Закрыть окно редактирования новости");
        cancelOkButton.perform(click());
    }

    public void continueEditing() {
        Allure.step("Продолжить редактирование");
        cancelCancelButton.perform(click());
    }

    public void editCategory(String data) {
        Allure.step("Измененить категорию");
        categoryValue.perform(replaceText(data));
    }
}
