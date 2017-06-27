package free.parking.parkit.activities;

import android.os.AsyncTask;
import android.os.Handler;

import free.parking.parkit.networking.RetrofitHelper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by lschidu on 6/10/17.
 */

public class MainPresenterImpl implements MainPresenter {
    private MainView mainView;
    private int mInterval = 500;
    private Handler mHandler;


    public MainPresenterImpl(MainView mainView) {
        this.mainView = mainView;
        mHandler = new Handler();
    }

    @Override
    public void getCounter() {
        mStatusChecker.run();
    }

    Runnable mStatusChecker = new Runnable() {
        @Override
        public void run() {
            try {
                new CounterRequest().execute();
            } finally {
                // 100% guarantee that this always happens, even if
                // your update method throws an exception
                mHandler.postDelayed(mStatusChecker, mInterval);
            }
        }
    };

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
