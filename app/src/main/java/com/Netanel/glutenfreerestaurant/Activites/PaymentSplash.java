package com.Netanel.glutenfreerestaurant.Activites;
import android.animation.Animator;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.Netanel.glutenfreerestaurant.MyUtils.MySignal;
import com.Netanel.glutenfreerestaurant.R;
import com.airbnb.lottie.LottieAnimationView;

public class PaymentSplash extends AppCompatActivity {

    private LottieAnimationView lottieAnimationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_creditcard);
        findViews();
        lottieAnimationView.resumeAnimation();
        lottieAnimationView.addAnimatorListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {
            }

            @Override
            public void onAnimationEnd(Animator animator) {
                MySignal.getInstance().toast("Thank you your payment was successful =]");
                Intent intent = new Intent(getApplicationContext(),HomeActivity.class);
                startActivity(intent);
                finish();
            }
            @Override
            public void onAnimationCancel(Animator animator) {

            }
            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });

    }

    private void findViews() {
        lottieAnimationView = findViewById(R.id.animation_view_creditcard);
    }
}
