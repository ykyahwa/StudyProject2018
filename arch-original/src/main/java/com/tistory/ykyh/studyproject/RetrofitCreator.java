package com.tistory.ykyh.studyproject;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by eokhyunlee on 2016. 8. 28..
 */
public class RetrofitCreator {

    public static final String BASE_URL = "https://api.github.com";

    private static GitHubService gitHubService;

    public static Retrofit createRetrofit() {

        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(buildClient())
                .build();

    }

    public static GitHubService getGitHubService() {
        if (gitHubService == null) {
            gitHubService = createRetrofit().create(GitHubService.class);
        }
        return gitHubService;
    }

    private static OkHttpClient buildClient()
    {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();

        builder.addInterceptor(chain -> {
            Response response = chain.proceed(chain.request());
            // Do anything with response here
            // if we ant to grab a specific cookie or something..
            return response;
        });

        builder.addInterceptor(chain -> {
            //this is where we will add whatever we want to our request headers.
            Request request = chain.request().newBuilder().addHeader("Content-Type", "application/json").build();
            return chain.proceed(request);
        });

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BASIC);
        builder.addInterceptor(logging);

        return builder.build();
    }
}
