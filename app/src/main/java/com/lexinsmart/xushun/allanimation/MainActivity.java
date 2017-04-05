package com.lexinsmart.xushun.allanimation;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.btn_alpha)
    AppCompatButton btnAlpha;
    @BindView(R.id.button_rotate)
    Button buttonRotate;
    @BindView(R.id.button_translate)
    Button buttonTranslate;
    @BindView(R.id.button_scale)
    Button buttonScale;
    @BindView(R.id.button_animationset)
    Button buttonAnimationset;
    @BindView(R.id.button_ObjectAnimator)
    Button buttonObjectAnimator;
    @BindView(R.id.button_propertyvalueholder)
    Button buttonPropertyvalueholder;
    @BindView(R.id.button_valueanimator)
    Button buttonValueanimator;
    @BindView(R.id.button_animatorset)
    Button buttonAnimatorset;
    @BindView(R.id.button_xml)
    Button buttonXml;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    /**
     * 透明度动画
     */
    @OnClick(R.id.btn_alpha)
    public void alpha() {
        AlphaAnimation aa = new AlphaAnimation(0, 1);
        aa.setDuration(1000);
        btnAlpha.startAnimation(aa);
    }

    /**
     * 旋转动画
     */
    @OnClick(R.id.button_rotate)
    public void rotate() {

        RotateAnimation ra = new RotateAnimation(0, 270, 100, 100);
        ra.setDuration(2000);
        buttonRotate.startAnimation(ra);
    }

    /**
     * 位移动画
     */
    @OnClick(R.id.button_translate)
    public void translate() {
        TranslateAnimation ta = new TranslateAnimation(0, 200, 0, 300);
        ta.setDuration(2000);
        buttonTranslate.startAnimation(ta);
    }

    /**
     * 缩放动画 可以设置中心点
     */
    @OnClick(R.id.button_scale)
    public void scale() {
        ScaleAnimation sa = new ScaleAnimation(0, 1.5f, 0, 1.5f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        sa.setDuration(3000);
        buttonScale.startAnimation(sa);
    }

    /**
     * 动画集合
     */
    @OnClick(R.id.button_animationset)
    public void animationset() {
        AnimationSet as = new AnimationSet(true);
        as.setDuration(1000);

        AlphaAnimation aa = new AlphaAnimation(0, 1);
        aa.setDuration(1000);
        as.addAnimation(aa);

        TranslateAnimation ta = new TranslateAnimation(0, 100, 0, 500);
        ta.setDuration(1000);
        as.addAnimation(ta);

        buttonAnimationset.startAnimation(as);

        as.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Toast.makeText(getApplicationContext(), "动画结束", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    /**
     * 属性动画的ObjectAnimator
     * translationX translationY
     * rotation,rotationX,rotationY
     * scaleX,scaleY
     * pivotX,pivotY 围绕支点位置进行旋转和缩放处理。
     * x,y
     * alpha
     */
    @OnClick(R.id.button_ObjectAnimator)
    public void ObjectAnimator() {
        ObjectAnimator animator = ObjectAnimator.ofFloat(
                buttonObjectAnimator,
                "translationX",
                300
        );
        animator.setDuration(3000);
        animator.start();
    }

    /**
     * 类似animationSet
     */
    @OnClick(R.id.button_propertyvalueholder)
    public void Propertyvalueholder() {
        PropertyValuesHolder pvh1 = PropertyValuesHolder.ofFloat("translationX", 300f);
        PropertyValuesHolder pvh2 = PropertyValuesHolder.ofFloat("scaleX", 1f, 0, 1f);
        PropertyValuesHolder pvh3 = PropertyValuesHolder.ofFloat("scaleY", 1f, 0, 1f);
        ObjectAnimator.ofPropertyValuesHolder(buttonPropertyvalueholder, pvh1, pvh2, pvh3)
                .setDuration(1000)
                .start();
    }

    /**
     * ValueAnimator
     */
    @OnClick(R.id.button_valueanimator)
    public void valueAnimator() {
//        final ValueAnimator animator = ValueAnimator.ofFloat(0,100);
//        animator.setTarget(buttonValueanimator);
//        animator.setDuration(1000);
//        animator.start();
//
//        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//            @Override
//            public void onAnimationUpdate(ValueAnimator valueAnimator) {
//                Float value = (Float) animator.getAnimatedValue();
//                System.out.print(value);
//            }
//        });
        ObjectAnimator anim = ObjectAnimator.ofFloat(buttonValueanimator, "alpha", 0.5f);
        anim.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                Toast.makeText(getApplicationContext(), "end", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
        anim.start();
    }

    /**
     * 不仅能实现propertyvaluesholder功能，还能精确实现顺序控制
     * playTogether,playSequentially()依次，animSet.play(),with(),before(),after()
     */
    @OnClick(R.id.button_animatorset)
    public void animatorset() {
        ObjectAnimator animator1 = ObjectAnimator.ofFloat(buttonAnimatorset, "translationX", 300f);
        ObjectAnimator animator2 = ObjectAnimator.ofFloat(buttonAnimatorset, "scaleX", 1f, 0f, 1f);
        ObjectAnimator animator3 = ObjectAnimator.ofFloat(buttonAnimatorset, "scaleY", 1f, 0f, 1f);

        AnimatorSet set = new AnimatorSet();
        set.setDuration(2000);
        set.playTogether(animator1, animator2, animator3);
        set.start();

    }
    /**
     * xml 中使用属性动画
     *
     *  animator：对应代码中的ValueAnimator类。

        objectAnimator：对应代码中的ObjectAnimator类。

        set：对应代码中的AnimatorSet类。
     */
    @OnClick(R.id.button_xml)
    public void xml(){
        Animator animator = AnimatorInflater.loadAnimator(this,R.animator.scalex);
        animator.setTarget(buttonXml);
        animator.start();
    }
}
