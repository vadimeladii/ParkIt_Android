package free.parking.parkit.activities;

import android.os.AsyncTask;

import free.parking.parkit.networking.RetrofitHelper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by lschidu on 6/10/17.
 */

public class MainPresenterImpl implements MainPresenter {
    private MainView mainView;

    public MainPresenterImpl(MainView mainView) {
        this.mainView = mainView;
    }

    @Override
    public void getCounter() {
        new CounterRequest().execute();
    }

    class CounterRequest extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            Call<Integer> call = RetrofitHelper.getINSTANCE().getApiService().getCounter();
            call.enqueue(new Callback<Integer>() {
                @Override
                public void onResponse(Call<Integer> call, Response<Integer> response) {
                    mainView.onCounterReceive(response.body());
                }

                @Override
                public void onFailure(Call<Integer> call, Throwable t) {

                }
            });
            return null;
        };
    }
}
