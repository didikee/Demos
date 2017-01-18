package com.didikee.demos.ui.act.viewActivity;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.RippleDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.StateListDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.didikee.demos.R;

import java.util.Arrays;

public class RippleJavaActivity extends AppCompatActivity {

    private TextView tv_name;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ripple_java);

        tv_name = ((TextView) findViewById(R.id.tv_name));

        RippleDrawable drawable=new RippleDrawable(
                ColorStateList.valueOf(Color.BLUE),
                getResources().getDrawable(R.drawable.shape_cricle_solid_whote_stoke_gray),
                getResources().getDrawable(R.drawable.shape_cricle_solid_whote_stoke_gray_big)
        );

        tv_name.setBackground(drawable);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public Drawable getAdaptiveRippleDrawable(
            int normalColor, int pressedColor) {
            return new RippleDrawable(ColorStateList.valueOf(normalColor),
                    null, getResources().getDrawable(R.drawable.shape_bg_red_cricle));
    }

    private Drawable getRippleMask(int color) {
        float[] outerRadii = new float[8];
        // 3 is radius of final ripple,
        // instead of 3 you can give required final radius
        Arrays.fill(outerRadii, 3);

        RoundRectShape r = new RoundRectShape(outerRadii, null, null);
        ShapeDrawable shapeDrawable = new ShapeDrawable(r);
        shapeDrawable.getPaint().setColor(color);
        return shapeDrawable;
    }

    public StateListDrawable getStateListDrawable(
            int normalColor, int pressedColor) {
        StateListDrawable states = new StateListDrawable();
        states.addState(new int[]{android.R.attr.state_pressed},
                new ColorDrawable(pressedColor));
        states.addState(new int[]{android.R.attr.state_focused},
                new ColorDrawable(pressedColor));
        states.addState(new int[]{android.R.attr.state_activated},
                new ColorDrawable(pressedColor));
        states.addState(new int[]{},
                new ColorDrawable(normalColor));
        return states;
    }
}
