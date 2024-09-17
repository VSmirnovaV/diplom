package ru.iteco.fmhandroid.ui.Data.Page;import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsNot.not;

import androidx.test.espresso.ViewInteraction;

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.Data.Function;


public class LoveIsAllPage {

    private final ViewInteraction title = onView(withId(R.id.our_mission_title_text_view));
    private final ViewInteraction materialCard = onView(allOf(withId(R.id.our_mission_item_list_recycler_view), Function.
            childAtPosition(withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")), 0)));
    private final ViewInteraction imageCard = onView(withId(R.id.our_mission_item_image_view));
    private final ViewInteraction itemTitle = onView(withId(R.id.our_mission_item_title_text_view));
    private final ViewInteraction itemTextCard = onView(allOf(withId(R.id.our_mission_item_description_text_view),
            withParent(withParent(withId(R.id.our_mission_item_material_card_view))),
            isDisplayed()));
    private final ViewInteraction viewCardButton = onView(withId(R.id.our_mission_item_open_card_image_button));

    public void waitingPageLoad() {
        onView(isRoot()).perform(Function.waitDisplayed(R.id.our_mission_title_text_view, 7000));
    }

    public void pageVisible() {
        Allure.step("Проверить видимость элементов на странице");
        title.check(matches(isDisplayed()));
        title.check(matches(withText("Love is all")));
//        materialCard.check(matches(isDisplayed()));
//        imageCard.check(matches(isDisplayed()));
//        itemTitle.check(matches(isDisplayed()));
//        viewCardButton.check(matches(isDisplayed()));
//        itemTextCard.check(matches(not(isDisplayed())));
    }

    public void clickView(int position) {
        Allure.step("Развернуть/Свернуть карточку с цитатой");
        materialCard.perform(actionOnItemAtPosition(position, click()));
    }

    public void textCardVisible(String text) {
        Allure.step("Видимость текста после разворачивания карточки с цитатой");
        itemTextCard.check(matches(withText(text)));
        itemTextCard.check(matches(isDisplayed()));
    }
}