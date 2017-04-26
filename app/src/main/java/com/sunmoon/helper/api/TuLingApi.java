package com.sunmoon.helper.api;

import com.sunmoon.helper.model.TuLing;



import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by SunMoon on 2017/4/20.
 */

public interface TuLingApi {
  @GET("/openapi/api")
  Observable<TuLing> getResult(@Query("key") String key,@Query("info") String info);
}
