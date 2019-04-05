package com.idyoga.yoga.common.view;

import android.animation.Animator;
import android.view.View;

public interface AnimatorProvider {

    Animator showAnimation(View view);

    Animator hideAnimation(View view);
}