package ir.ngra.automation.views.customs;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.text.InputFilter;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.core.content.res.ResourcesCompat;

import ir.ngra.automation.R;

public class ML_Button extends LinearLayout {

    private TextView textView;
    private Context context;

    private Drawable normalBack;
    private Drawable waitBack;

    private int imageWidth;
    private int imageHeight;
    private Drawable imageSrc;
    private int imageTint;

    private int textColor;
    private String text;
    private int textSize;
    private int fontFamilyId;


    //______________________________________________________________________________________________ ML_Button
    public ML_Button(Context context) {
        super(context);
        this.context = context;
    }
    //______________________________________________________________________________________________ ML_Button



    //______________________________________________________________________________________________ ML_Button
    public ML_Button(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;

        TypedArray ta = getContext().obtainStyledAttributes(attrs, R.styleable.ML_Button);

        normalBack = ta.getDrawable(R.styleable.ML_Button_connectBack);
        waitBack = ta.getDrawable(R.styleable.ML_Button_waitBack);

        imageWidth = (int) (ta.getDimension(R.styleable.ML_Button_img_width, 0));
        imageHeight = (int) (ta.getDimension(R.styleable.ML_Button_img_height, 0));
        imageSrc = ta.getDrawable(R.styleable.ML_Button_img_src);
        imageTint = ta.getColor(R.styleable.ML_Button_img_tint, 0);

        textColor = ta.getColor(R.styleable.ML_Button_textColor, 0);
        text = ta.getString(R.styleable.ML_Button_textValue);
        textSize = (int) (ta.getDimension(R.styleable.ML_Button_textValueSize, 0) / getResources().getDisplayMetrics().density);
        fontFamilyId = ta.getResourceId(R.styleable.ML_Button_fontFamily, 0);

        setBackground(normalBack);
        setGravity(Gravity.CENTER);
        setOrientation(HORIZONTAL);
        configText();
        configIcon();

    }
    //______________________________________________________________________________________________ ML_Button





    //______________________________________________________________________________________________ configEditText
    private void configText() {
        textView = new TextView(context);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        textView.setTextColor(textColor);
        textView.setGravity(Gravity.CENTER);
        textView.setText(text);
        textView.setTextSize(textSize);
        textView.setBackgroundColor(context.getResources().getColor(R.color.ML_Transparent));
        textView.setLayoutParams(params);
        textView.setPadding(20, 4, 20, 4);
        if (fontFamilyId > 0)
            textView.setTypeface(ResourcesCompat.getFont(getContext(), fontFamilyId));
        addView(textView, params);
    }
    //______________________________________________________________________________________________ configEditText



    //______________________________________________________________________________________________ configIcon
    private void configIcon() {
        ImageView imageView = new ImageView(context);
        LinearLayout.LayoutParams params = new LayoutParams(imageWidth, imageHeight);
        imageView.setLayoutParams(params);
        imageView.setImageDrawable(imageSrc);
        imageView.setColorFilter(imageTint);
        addView(imageView, params);
    }
    //______________________________________________________________________________________________ configIcon



}
