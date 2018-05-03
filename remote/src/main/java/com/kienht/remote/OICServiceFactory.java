package com.kienht.remote;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.kienht.remote.OICService;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.kienht.remote.OICService.URL_SERVER;

/**
 * Note:
 * Created by kienht on 4/30/18.
 */
public class OICServiceFactory {

    public OICService makeOICService(boolean isDebug) {
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        Retrofit retrofit = new Retrofit.Builder().baseUrl(URL_SERVER)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(makeOkHttpClient(isDebug))
                .build();
        return retrofit.create(OICService.class);
    }

    private OkHttpClient makeOkHttpClient(boolean isDebug) {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(isDebug ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE);
        return new OkHttpClient.Builder().connectTimeout(60, TimeUnit.SECONDS)
                .addInterceptor(chain -> {
                    Request request = chain.request()
                            .newBuilder()
                            .addHeader("Content-Type", "application/json")
                            .build();

                    return chain.proceed(request);
                })
                .addInterceptor(logging)
                .build();
    }

}
