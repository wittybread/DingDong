package wangxuewei.bwie.com.demo02;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ImageView img = (ImageView) findViewById(R.id.img);
        //属性动画
        float translationY = img.getTranslationY();
        ObjectAnimator translation = ObjectAnimator.ofFloat(img, "translationY", translationY, 500f);
        ObjectAnimator alpha = ObjectAnimator.ofFloat(img, "alpha", 0f, 1f);
        ObjectAnimator rotation = ObjectAnimator.ofFloat(img, "rotation", 0f, 360f);

        ObjectAnimator scaleY = ObjectAnimator.ofFloat(img, "scaleY", 2f, 1f);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(rotation).with(alpha).with(scaleY).with(translation);
        //设置时间
        animatorSet.setDuration(3000);
        //开始
        animatorSet.start();
        animatorSet.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                //跳转
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
    }
}
