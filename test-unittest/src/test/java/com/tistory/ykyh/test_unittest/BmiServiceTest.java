package com.tistory.ykyh.test_unittest;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyBoolean;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


/**
 * Created by eokhyunlee on 2018. 2. 9..
 */

public class BmiServiceTest {

    @Test
    public void static인start메소드를호출하면startService된다() {
        Context context = mock(Context.class);
        SaveBmiService.start(context, mock(BmiValue.class));
        verify(context, times(1)).startService((Intent) any());
    }

    //java.lang.RuntimeException: Method setAction in android.content.Intent not mocked. 발생
    //gradle 에 unitTest.returnDefaultValues = true 지정해야함
    @Test
    public void Intent생성() throws Exception {
        Intent intent = new Intent();
        intent.setAction("hoge");
        assertEquals("hoge", intent.getAction());
    }

    @Test
    public void onHandleIntent데스트() throws Exception {
        BmiValue bmiValue = mock(BmiValue.class);
        Intent intent = mock(Intent.class);

        when(intent.getSerializableExtra(SaveBmiService.PARAM_KEY_BMI_VALUE)).thenReturn(bmiValue);

        SaveBmiService service = spy(new SaveBmiService());
        doReturn(false).when(service).saveToServer((BmiValue) any());
        doNothing().when(service).sendLocalBroadcast(anyBoolean());

        service.onHandleIntent(intent);
        verify(service, times(1)).sendLocalBroadcast(anyBoolean());
        verify(service, times(1)).saveToServer((BmiValue) any());
    }

    @Test
    public void broadcast날린다() throws  Exception{
        LocalBroadcastManager manager = mock(LocalBroadcastManager.class);
        SaveBmiService service = new SaveBmiService();
        service.setLocalBroadcastManager(manager);

        service.sendLocalBroadcast(true);

        verify(manager, times(1)).sendBroadcast((Intent) any());

    }
}
