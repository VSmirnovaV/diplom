package ru.iteco.fmhandroid.ui.Data.Page;import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isClickable;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.matcher.ViewMatchers;

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.Data.Function;

public class AboutPage {

    private final ViewInteraction titleVersion = onView(withId(R.id.about_version_title_text_view));
    private final ViewInteraction versionValue = onView(withId(R.id.about_version_value_text_view));
    private final ViewInteraction privacyPolicy = onView(withId(R.id.about_privacy_policy_label_text_view));
    private final ViewInteraction privacyPolicyLinc = onView(withId(R.id.about_privacy_policy_value_text_view));
    private final ViewInteraction termsOfUse = onView(withId(R.id.about_terms_of_use_label_text_view));
    private final ViewInteraction termsOfUseLinc = onView(withId(R.id.about_terms_of_use_value_text_view));
    private final ViewInteraction infoLabel = onView(withId(R.id.about_company_info_label_text_view));
    private final ViewInteraction backButton = onView(withId(R.id.about_back_image_button));

    public void waitingPageLoad() {
        Allure.step("Ожидание загрузки страницы");
        onView(isRoot()).perform(Function.waitDisplayed(R.id.about_version_title_text_view, 7000));
    }

    public void pageVisible() {
        Allure.step("Проверить видимость элементов на странице");
        titleVersion.check(ViewAssertions.matches(isDisplayed()));
        titleVersion.check(matches(ViewMatchers.withText("Version:")));
        versionValue.check(ViewAssertions.matches(isDisplayed()));
        versionValue.check(matches(ViewMatchers.withText("1.0.0")));
        privacyPolicy.check(ViewAssertions.matches(isDisplayed()));
        privacyPolicy.check(matches(ViewMatchers.withText("Privacy Policy:")));
        privacyPolicyLinc.check(ViewAssertions.matches(isDisplayed()));
        termsOfUse.check(ViewAssertions.matches(isDisplayed()));
        termsOfUse.check(matches(ViewMatchers.withText("Terms of use:")));
        termsOfUseLinc.check(ViewAssertions.matches(isDisplayed()));
        infoLabel.check(ViewAssertions.matches(isDisplayed()));
        backButton.check(ViewAssertions.matches(isDisplayed()));
    }

    public void pageClickable() {
        Allure.step("Проверить кликабельность элементов на странице");
        privacyPolicyLinc.check(ViewAssertions.matches(isClickable()));
        termsOfUseLinc.check(ViewAssertions.matches(isClickable()));
        backButton.check(ViewAssertions.matches(isClickable()));
    }

    public void clickBackButton() {
        Allure.step("Вернуться на предыдущую страницу");
        backButton.perform(click());
    }

    public void clickPrivacyPolicyLinc() {
        Allure.step("Перейти по ссылке Privacy Policy");
        privacyPolicyLinc.perform(click());
    }

    public void clickTermsOfUseLinc() {
        Allure.step("Перейти по ссылке Terms of use");
        termsOfUseLinc.perform(click());
    }

}