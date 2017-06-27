package free.parking.parkit.activities;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import free.parking.parkit.R;
import free.parking.parkit.networking.RetrofitHelper;
import retrofit2.Call;
import retrofit2.Callback;

public class MainActivity extends AppCompatActivity implements MainView {

    private ImageView imageView;
    private MainPresenter mainPresenter;
    private TextView counterTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sendToken();
        initWidgets();
        mainPresenter.getCounter();


    }

    private void initWidgets() {
        imageView = (ImageView) findViewById(R.id.imageView);
        counterTextView = (TextView) findViewById(R.id.textView);

        final Animation startRotateAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate_animation);
        mainPresenter = new MainPresenterImpl(this);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageView.startAnimation(startRotateAnimation);
                mainPresenter.getCounter();
            }
        });

    }

    private void sendToken() {
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences(getString(R.string.FCM_PREF), Context.MODE_PRIVATE);
        final String token = sharedPreferences.getString(getString(R.string.FCM_TOKEN), "");
        Log.d("TOKEN", token);
        Call<Void> call = RetrofitHelper.getINSTANCE().getApiService().postToken(token);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, retrofit2.Response<Void> response) {
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
    }

    @Override
    public void  onCounterReceive(Integer integer) {
        counterTextView.setText(integer.toString());
    }
}
