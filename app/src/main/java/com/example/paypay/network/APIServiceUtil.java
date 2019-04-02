package com.example.paypay.network;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.io.File;
import java.io.IOException;

import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

//singleton class for api service utility
public class APIServiceUtil {

    private static final long NETWORK_TIMEOUT = 10;
    private static Retrofit mRetrofit;
    private static APIServiceUtil sInstance;
    private static Context mContext;

    private static File httpCacheDir ;
    private static Cache cache ;

    //INTERCEPTORS
    private static final Interceptor REWRITE_RESPONSE_INTERCEPTOR = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            Response response = chain.proceed(request);
            String cacheControl = response.header("Cache-Control");
        /*    if (!isOnline()) {
                int maxStale = 60 * 60 * 24 * 28; // tolerate 4-weeks stale
                request = request.newBuilder()
                        .header("Cache-Control", "public, only-if-cached,max-stale=" + maxStale)
                        .build();
                return chain.proceed(request);
            } else {*/

                if (cacheControl == null || cacheControl.contains("no-store") || cacheControl.contains("no-cache") ||
                        cacheControl.contains("must-revalidate") || cacheControl.contains("max-age=0")) {
                    return response.newBuilder()
                            .header("Cache-Control", "public, max-age=" + 1800)
                            .build();
                } else {
                    return response;
                }
            //}
        }
    };

    private static final Interceptor OFFLINE_INTERCEPTOR = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            if (!isOnline()) {
                int maxStale = 60 * 60 * 24 * 28; // tolerate 4-weeks stale
                request = request.newBuilder()
                        .header("Cache-Control", "public, only-if-cached,max-stale=" + maxStale)
                        .build();
            }
            return chain.proceed(request);
        }
    };

    private APIServiceUtil(Context context) {
        httpCacheDir = new File(mContext.getCacheDir(),"cacheFlikr");
        cache = new Cache(httpCacheDir, 20 * 1024 * 1024);
       mContext = context;
        mRetrofit = new Retrofit.Builder()
                .baseUrl("https://api.flickr.com/services/rest/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(new OkHttpClient.Builder().cache(cache)
                        .addInterceptor(REWRITE_RESPONSE_INTERCEPTOR)
                        .addInterceptor(OFFLINE_INTERCEPTOR)
                        .build()
                ).build();


    }



    public static APIServiceUtil getInstance(Context context) {
        mContext = context;
        if (sInstance == null) {
            sInstance = new APIServiceUtil(context);
        }
        return sInstance;
    }

    public APIInterface getAPIInterface() {
        return mRetrofit.create(APIInterface.class);
    }

    public static boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) mContext.getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnected();
    }
}
