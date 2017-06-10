package free.parking.parkit.networking;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by lschidu on 1/5/17.
 */

public interface ApiService {

    @GET("/api/parking/add/token/{token}")
    Call<Void> postToken(@Path("token") String uuid);
}
