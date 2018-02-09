package com.tistory.ykyh.test_unittest;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.support.v4.content.LocalBroadcastManager;

import java.io.Serializable;
import java.util.Random;

/**
 * Created by eokhyunlee on 2018. 2. 9..
 */

public class SaveBmiService extends IntentService {


    public static final String ACTION_RESULT = SaveBmiService.class.getName() + ".ACTION_RESULT";
    public static final String PARAM_RESULT = "param_result";

    static final String PARAM_KEY_BMI_VALUE = "bmi_value";

    private LocalBroadcastManager localBroadcastManager;

    public SaveBmiService() {
        super(SaveBmiService.class.getName());
    }

    @Override
    public void onCreate() {
        super.onCreate();
        localBroadcastManager = LocalBroadcastManager.getInstance(getApplicationContext());
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        if (intent == null) return;

        Serializable serializable = intent.getSerializableExtra(PARAM_KEY_BMI_VALUE);
        if (serializable == null || !(serializable instanceof BmiValue)) return;

        BmiValue bmiValue = (BmiValue) serializable;
        boolean result = saveToServer(bmiValue);
        sendLocalBroadcast(result);

    }

    @VisibleForTesting
    public boolean saveToServer(BmiValue bmiValue) {

        try {
            Thread.sleep(3000 + new Random().nextInt(2000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new Random().nextBoolean();
    }

    @VisibleForTesting
    public void sendLocalBroadcast(boolean result) {
        Intent intent = new Intent(ACTION_RESULT);
        intent.putExtra(PARAM_RESULT, result);
        localBroadcastManager.sendBroadcast(intent);
    }

    @VisibleForTesting
    public void setLocalBroadcastManager(LocalBroadcastManager localBroadcastManager) {
        this.localBroadcastManager = localBroadcastManager;
    }

    public static void start(Context context, BmiValue bmiValue) {
        Intent intent = new Intent(context, SaveBmiService.class);
        intent.putExtra(PARAM_KEY_BMI_VALUE, bmiValue);
        context.startService(intent);
    }
}
