package free.parking.parkit.networking;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by lschidu on 1/5/17.
 */

public class RetrofitHelper {

    private static RetrofitHelper INSTANCE = new RetrofitHelper();

    private static final String apiUrl = "http://109.185.158.161:8080";

    private ApiService apiService;

    private RetrofitHelper() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(apiUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiService = retrofit.create(ApiService.class);
    }

    public static RetrofitHelper getINSTANCE() {
        return INSTANCE;
    }

    public ApiService getApiService() {
        return this.apiService;
    }


}
