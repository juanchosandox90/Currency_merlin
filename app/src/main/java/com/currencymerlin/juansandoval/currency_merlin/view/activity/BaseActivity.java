package com.currencymerlin.juansandoval.currency_merlin.view.activity;


import android.content.pm.ActivityInfo;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.transition.Fade;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.Window;

import com.currencymerlin.juansandoval.currency_merlin.R;

public abstract class BaseActivity extends AppCompatActivity {

    private ViewTreeObserver.OnGlobalLayoutListener keyboardLayoutListener;
    private Window window;
    private View rootView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(getLayout());
    }

    @LayoutRes
    protected abstract int getLayout();

    protected abstract View getRootView();

    protected abstract void inject();

    public void showFragment(Fragment fragment) {
        fragment.setEnterTransition(new Fade());
        fragment.setExitTransition(new Fade());

        getSupportFragmentManager()
                .beginTransaction()
                .setCustomAnimations(R.anim.fade_in, R.anim.fade_out)
                .replace(R.id.fragmentContainer, fragment)
                .commit();
    }

    public void showFragmentTransitionIcon(Fragment fragment, View shared) {
        Transition changeTransform = TransitionInflater.from(this).
                inflateTransition(R.transition.change_image_transform);
        fragment.setSharedElementEnterTransition(changeTransform);
        fragment.setEnterTransition(new Fade());

        getSupportFragmentManager()
                .beginTransaction()
                .setCustomAnimations(R.anim.fade_in, R.anim.fade_out)
                .addSharedElement(shared, "icon")
                .replace(R.id.fragmentContainer, fragment)
                .commit();

    }

    public ViewTreeObserver.OnGlobalLayoutListener getKeyboardLayoutListener() {
        return new ViewTreeObserver.OnGlobalLayoutListener() {
            int initialHeight;

            @Override
            public void onGlobalLayout() {
                Rect r = new Rect();
                View view = window.getDecorView();
                view.getWindowVisibleDisplayFrame(r);
                if (initialHeight == 0) {
                    initialHeight = r.height();
                } else {
                    if (initialHeight > r.height()) {

                    } else if (initialHeight == r.height()) {

                    }
                }
            }
        };
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        getRootView().getViewTreeObserver().removeOnGlobalLayoutListener(keyboardLayoutListener);
    }

}
