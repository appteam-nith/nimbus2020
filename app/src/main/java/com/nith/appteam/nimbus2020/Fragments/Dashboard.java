package com.nith.appteam.nimbus2020.Fragments;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnticipateOvershootInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.nith.appteam.nimbus2020.R;


public class Dashboard extends Fragment {
    private TextView quote1, quote2, event_text, campus_text;
    private CardView quiz_card, workshop_card, exhibition_card, talk_card;
    private ImageView t_n, t_k, e_n, e_k;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_dashboard, container, false);

        quote1 = rootView.findViewById(R.id.quote1);
        quote2 = rootView.findViewById(R.id.quote2);
        event_text = rootView.findViewById(R.id.event_text);
        campus_text = rootView.findViewById(R.id.campus_text);
        quiz_card = rootView.findViewById(R.id.quiz_card);
        workshop_card = rootView.findViewById(R.id.workshop_card);
        exhibition_card = rootView.findViewById(R.id.exhibition_card);
        talk_card = rootView.findViewById(R.id.talk_card);

        t_n = rootView.findViewById(R.id.t_n);
        t_k = rootView.findViewById(R.id.t_k);
        e_n = rootView.findViewById(R.id.e_n);
        e_k = rootView.findViewById(R.id.e_k);

        ObjectAnimator e_n_right = ObjectAnimator.ofFloat(e_n, "translationX", e_n.getTranslationX(), e_n.getTranslationX()+30);
        ObjectAnimator e_n_left = ObjectAnimator.ofFloat(e_n, "translationX", e_n.getTranslationX(), e_n.getTranslationX()-30);
        ObjectAnimator e_n_right1 = ObjectAnimator.ofFloat(e_n, "translationY", e_n.getTranslationY(), e_n.getTranslationY()+30);
        ObjectAnimator e_n_left1 = ObjectAnimator.ofFloat(e_n, "translationY", e_n.getTranslationY(), e_n.getTranslationY()-30);
        e_n_right.setDuration(1000);
        e_n_left.setDuration(1000);
        e_n_right1.setDuration(1000);
        e_n_left1.setDuration(1000);
        e_n_right.setInterpolator(new AnticipateOvershootInterpolator());
        e_n_left.setInterpolator(new AnticipateOvershootInterpolator());
        e_n_right1.setInterpolator(new AnticipateOvershootInterpolator());
        e_n_left1.setInterpolator(new AnticipateOvershootInterpolator());

        ObjectAnimator e_k_right = ObjectAnimator.ofFloat(e_k, "translationX", e_k.getTranslationX(), e_k.getTranslationX()+30);
        ObjectAnimator e_k_left = ObjectAnimator.ofFloat(e_k, "translationX", e_k.getTranslationX(), e_k.getTranslationX()-30);
        ObjectAnimator e_k_right1 = ObjectAnimator.ofFloat(e_k, "translationY", e_k.getTranslationY(), e_k.getTranslationY()+30);
        ObjectAnimator e_k_left1 = ObjectAnimator.ofFloat(e_k, "translationY", e_k.getTranslationY(), e_k.getTranslationY()-30);
        e_k_right1.setDuration(1400);
        e_k_left1.setDuration(1400);
        e_k_right.setDuration(1400);
        e_k_left.setDuration(1400);
        e_k_right1.setInterpolator(new AnticipateOvershootInterpolator());
        e_k_left1.setInterpolator(new AnticipateOvershootInterpolator());
        e_k_right.setInterpolator(new AnticipateOvershootInterpolator());
        e_k_left.setInterpolator(new AnticipateOvershootInterpolator());

        AnimatorSet animator = new AnimatorSet();
        animator.playTogether(e_n_right,e_n_right1,e_k_right,e_k_right1);

        final AnimatorSet animator1 = new AnimatorSet();
        animator1.playTogether(e_n_left,e_n_left1,e_k_left,e_k_left1);

        animator.start();
        animator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                e_n.setTranslationX(e_n.getTranslationX()+30);
                e_n.setTranslationY(e_n.getTranslationY()+30);
                e_k.setTranslationX(e_k.getTranslationX()+30);
                e_k.setTranslationY(e_k.getTranslationY()+30);
                animator1.start();
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });

        animator1.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                e_n.setTranslationX(e_n.getTranslationX()-30);
                e_n.setTranslationY(e_n.getTranslationY()-30);
                e_k.setTranslationX(e_k.getTranslationX()-30);
                e_k.setTranslationY(e_k.getTranslationY()-30);
                animator.start();
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            quote2.setText(Html.fromHtml("<p>\"HOW YOU <font color=\"#2fc0d1\">CODIN'</font> \uD83D\uDCBB ?!\" <small><i><font color=\"#888888\"> ~ <strike>JOEY</strike> NIMBUS</font></i></small></p>", Html.FROM_HTML_MODE_COMPACT));
        } else {
            quote2.setText(Html.fromHtml("<p>\"HOW YOU <font color=\"#2fc0d1\">CODIN'</font> \uD83D\uDCBB ?!\" <small><i><font color=\"#888888\"> ~ <strike>JOEY</strike> NIMBUS</font></i></small></p>"));
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            campus_text.setText(Html.fromHtml("<p>\"ARE YOU A <font color=\"#2fc0d1\">CAMPUS AMBASSADOR</font> \uD83D\uDEA9 ?\"</p>", Html.FROM_HTML_MODE_COMPACT));
        } else {
            campus_text.setText(Html.fromHtml("<p>\"ARE YOU A <font color=\"#2fc0d1\">CAMPUS AMBASSADOR</font> \uD83D\uDEA9 ?\"</p>"));
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            event_text.setText(Html.fromHtml("<p>\"SNEAK PEAK \uD83D\uDD76 OUR <font color=\"#2fc0d1\">EVENTS</font> !\"</p>", Html.FROM_HTML_MODE_COMPACT));
        } else {
            event_text.setText(Html.fromHtml("<p>\"SNEAK PEAK \uD83D\uDD76 OUR <font color=\"#2fc0d1\">EVENTS</font> !\"</p>"));
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            quote1.setText(Html.fromHtml("<p>\"I AM <strike>IRON MAN</strike> <font color=\"#2fc0d1\">SEMBLANCE</font> \uD83D\uDE80 !\" <small><i><font color=\"#888888\"> ~ NIMBUS</font></i></small></p>", Html.FROM_HTML_MODE_COMPACT));
        } else {
            quote1.setText(Html.fromHtml("<p>\"I AM <strike>IRON MAN</strike> <font color=\"#2fc0d1\">SEMBLANCE</font> \uD83D\uDE80 !\" <small><i><font color=\"#888888\"> ~ NIMBUS</font></i></small></p>"));
        }

        return rootView;
    }
}
