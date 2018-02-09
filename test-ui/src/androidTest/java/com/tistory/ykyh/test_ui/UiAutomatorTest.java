package com.tistory.ykyh.test_ui;

import android.content.Context;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.uiautomator.By;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.support.test.uiautomator.UiSelector;
import android.support.test.uiautomator.Until;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertTrue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsNull.notNullValue;

/**
 * Created by eokhyunlee on 2018. 2. 9..
 */

public class UiAutomatorTest {

    private UiDevice device;
    private final long LAUCH_TIMEOUT = 5000;
    private String GOOGLE_PLAY_PACKAGE = "com.android.vending";

    @Before
    public void startMainActivityFromHomeScreen() {
        device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
        device.pressHome();

        String launcherPackage = device.getLauncherPackageName();
        assertThat(launcherPackage, notNullValue());
        device.wait(Until.hasObject(By.pkg(launcherPackage).depth(0)), LAUCH_TIMEOUT);
    }

    @Test
    public void GooglePlay시작하고검색() throws UiObjectNotFoundException {
        Context context = InstrumentationRegistry.getContext();
        final Intent intent = context.getPackageManager().getLaunchIntentForPackage(GOOGLE_PLAY_PACKAGE);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);

        device.wait(Until.hasObject(By.pkg(GOOGLE_PLAY_PACKAGE).depth(0)), LAUCH_TIMEOUT);

        UiObject searchBoxImage = device.findObject(new UiSelector()
                .resourceId("com.android.vending:id/search_box_idle_text").className("android.widget.ImageView"));
        searchBoxImage.click();

        UiObject searchBox = device.findObject(new UiSelector().resourceId("com.android.vending:id/search_box_text_input").className("android.widget.EditText"));
        assertTrue(searchBox.exists());
    }
}
