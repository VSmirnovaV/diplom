package ru.iteco.fmhandroid.ui.Data.Page;import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.matcher.ViewMatchers.isClickable;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsNot.not;
import static ru.iteco.fmhandroid.ui.Data.Function.childAtPosition;

import androidx.test.espresso.ViewInteraction;

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.Data.Function;

public class ControlPanelPage {
    private final ViewInteraction title = onView(withText("Control panel"));
    private final ViewInteraction sortButton = onView(withId(R.id.sort_news_material_button));
    private final ViewInteraction filterButton = onView(withId(R.id.filter_news_material_button));
    private final ViewInteraction createButton = onView(withId(R.id.add_news_image_view));
    private final ViewInteraction cardNews = onView(withId(R.id.news_item_material_card_view));
    private final ViewInteraction imageNews = onView(withId(R.id.category_icon_image_view));
    private final ViewInteraction publicationDateTitle = onView(withId(R.id.news_item_publication_text_view));
    private final ViewInteraction publicationDateValue = onView(withId(R.id.news_item_publication_date_text_view));
    private final ViewInteraction creationDateTitle = onView(withId(R.id.news_item_creation_text_view));
    private final ViewInteraction creationDateValue = onView(withId(R.id.news_item_create_date_text_view));
    private final ViewInteraction authorTitle = onView(withId(R.id.news_item_author_text_view));
    private final ViewInteraction authorValue = onView(withId(R.id.news_item_author_name_text_view));
    private final ViewInteraction statusImage = onView(withId(R.id.news_item_published_icon_image_view));
    private final ViewInteraction statusValue = onView(withId(R.id.news_item_published_text_view));
    private final ViewInteraction deleteButton = onView(
            Function.childAtPosition(
                    withId(R.id.news_item_material_card_view),
                    0));
    private final ViewInteraction deleteMessage = onView(withId(android.R.id.message));
    private final ViewInteraction deleteCancelButton = onView(withId(android.R.id.button2));
    private final ViewInteraction deleteOkButton = onView(withId(android.R.id.button1));
    private final ViewInteraction editButton = onView(withId(R.id.edit_news_item_image_view));
    private final ViewInteraction viewImageButton = onView(withId(R.id.view_news_item_image_view));
    //    private final ViewInteraction viewButton = onView(withId(R.id.news_list_recycler_view));
    public static ViewInteraction viewButton = onView(allOf(withId(R.id.news_list_recycler_view),
            childAtPosition(
                    withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                    0)));
    private final ViewInteraction titleNews = onView(withId(R.id.news_item_title_text_view));
    private final ViewInteraction textNews = onView(withId(R.id.news_item_description_text_view));

    public void waitingPageLoad() {
        Allure.step("Ожидание загрузки страницы");
        onView(isRoot()).perform(Function.waitDisplayed(R.id.sort_news_material_button, 7000));
    }

    public void pageVisible() {
        Allure.step("Проверить видимость элементов на странице");
        title.check(matches(isDisplayed()));
        sortButton.check(matches(isDisplayed()));
        filterButton.check(matches(isDisplayed()));
        createButton.check(matches(isDisplayed()));
//        cardNews.check(matches(isDisplayed()));
//        imageNews.check(matches(isDisplayed()));
//        titleNews.check(matches(isDisplayed()));
//        publicationDateTitle.check(matches(isDisplayed()));
//        publicationDateTitle.check(matches(withText("Publication date")));
//        publicationDateValue.check(matches(isDisplayed()));
//        creationDateTitle.check(matches(isDisplayed()));
//        creationDateTitle.check(matches(withText("Creation date")));
//        creationDateValue.check(matches(isDisplayed()));
//        authorTitle.check(matches(isDisplayed()));
//        authorTitle.check(matches(withText("Author")));
//        authorValue.check(matches(isDisplayed()));
//        statusImage.check(matches(isDisplayed()));
//        statusValue.check(matches(isDisplayed()));
//        deleteButton.check(matches(isDisplayed()));
//        editButton.check(matches(isDisplayed()));
        viewButton.check(matches(isDisplayed()));
//        viewImageButton.check(matches(isDisplayed()));
    }

    public void pageClickable() {
        Allure.step("Проверить кликабельность элементов на странице");
        sortButton.check(matches(isClickable()));
        filterButton.check(matches(isClickable()));
        createButton.check(matches(isClickable()));
    }

    public void scroll(int position) {
        Allure.step("Пролистать до заданной позиции");
        viewButton.perform(actionOnItemAtPosition(position, scrollTo()));
    }

    public void clickView(int position) {
        Allure.step("Развернуть карточку новости");
        viewButton.perform(actionOnItemAtPosition(position, click()));
    }

    public void titleNewsVisible(String title) {
        Allure.step("Видимость заголовка новости на странице");
        ViewInteraction textView2 = onView(
                allOf(withId(R.id.news_item_title_text_view), withText(title),
                        withParent(withParent(withId(R.id.news_item_material_card_view))),
                        isDisplayed()));
        textView2.check(matches(withText(title)));
    }

    public void textNewsVisible() {
        Allure.step("Видимость текста после разворачивания карточки новости");
        textNews.check(matches(isDisplayed()));
    }

    public void textNewsInvisible() {
        Allure.step("Видимость текста после сворачивания карточки новости");
        textNews.check(matches(not(isDisplayed())));
    }

    public void clickSort() {
        Allure.step("Нажать на кнопку сортировки");
        sortButton.perform(click());
    }

    public ControlPanelFilterPage clickFilter() {
        Allure.step("Открыть фильтр");
        filterButton.perform(click());
        return new ControlPanelFilterPage();
    }

    public CreateNewsPage clickCreate() {
        Allure.step("Открыть страницу создания новости");
        createButton.perform(click());
        return new CreateNewsPage();
    }

    public void clickDelete(int position) {
        Allure.step("Нажать на кнопку удалить по заданной позиции");
        deleteButton.perform(actionOnItemAtPosition(position, click()));
    }

    public EditNewsPage clickEdit() {
        Allure.step("Открыть страницу редактирования новости");
        editButton.perform(click());
        return new EditNewsPage();
    }

    public void checkViewNews(int index) {
        Allure.step("Развернуть новость");
        viewButton.perform(actionOnItemAtPosition(index, click()));
    }

    public void textVisible(String text) {
        Allure.step("Видимость описания новости");
        ViewInteraction textView = onView(
                allOf(withId(R.id.news_item_description_text_view), withText(text),
                        withParent(withParent(withId(R.id.news_item_material_card_view))),
                        isDisplayed()));
    }

    public void deleteWindowVisible() {
        Allure.step("Видимость модального окна при удалении новости");
//        deleteMessage.check(matches(isDisplayed()));
        deleteMessage.check(matches(withText("Are you sure you want to permanently delete the document? These changes cannot be reversed in the future.")));
//        deleteCancelButton.check(matches(isDisplayed()));
        deleteCancelButton.check(matches(withText("CANCEL")));
//        deleteOkButton.check(matches(isDisplayed()));
        deleteOkButton.check(matches(withText("OK")));
    }

    public void clickCancel() {
        Allure.step("Нажать на кнопку CANCEL");
        deleteCancelButton.perform(click());
    }

    public void clickButtonOK() {
        Allure.step("Нажать на кнопку OK");
        deleteOkButton.perform(click());
    }
}