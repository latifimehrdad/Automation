package ir.ngra.automation.views.customs;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.core.content.res.ResourcesCompat;
import androidx.databinding.BindingAdapter;
import androidx.databinding.InverseBindingAdapter;
import androidx.databinding.InverseBindingListener;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import ir.mlcode.latifiarchitecturelibrary.utility.Validations;
import ir.ngra.automation.R;

public class ML_EditText extends LinearLayout {

    private Validations validations;

    private Context context;
    private EditText editText;

    private Drawable normalBack;
    private Drawable emptyBack;

    private int delimiter_marginLeft;
    private int delimiter_marginTop;
    private int delimiter_marginRight;
    private int delimiter_marginBottom;
    private int delimiterWidth;

    private int imageWidth;
    private int imageHeight;
    private Drawable imageSrc;
    private int imageTint;


    private int editWidth;
    private int editTextColor;
    private int inputType;
    private int textSize;
    private String editHint;
    private String text;
    private Drawable delimiterBack;
    private int fontFamilyId;
    private boolean editSplitter;
    private int validationType;
    private int textMaxLine;
    private int textMaxLength;


    //______________________________________________________________________________________________ ML_EditText
    public ML_EditText(Context context) {
        super(context);
        this.context = context;
    }
    //______________________________________________________________________________________________ ML_EditText


    //______________________________________________________________________________________________ ML_EditText
    public ML_EditText(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;

        validations = new Validations();

        TypedArray ta = getContext().obtainStyledAttributes(attrs, R.styleable.ML_EditText);

        normalBack = ta.getDrawable(R.styleable.ML_EditText_normalBack);
        emptyBack = ta.getDrawable(R.styleable.ML_EditText_emptyBack);

        imageWidth = (int) (ta.getDimension(R.styleable.ML_EditText_image_width, 0));
        imageHeight = (int) (ta.getDimension(R.styleable.ML_EditText_image_height, 0));
        imageSrc = ta.getDrawable(R.styleable.ML_EditText_image_src);
        imageTint = ta.getColor(R.styleable.ML_EditText_image_tint, 0);

        delimiter_marginLeft = (int) (ta.getDimension(R.styleable.ML_EditText_delimiter_marginLeft, 0));
        delimiter_marginTop = (int) (ta.getDimension(R.styleable.ML_EditText_delimiter_marginTop, 0));
        delimiter_marginRight = (int) (ta.getDimension(R.styleable.ML_EditText_delimiter_marginRight, 0));
        delimiter_marginBottom = (int) (ta.getDimension(R.styleable.ML_EditText_delimiter_marginBottom, 0));
        delimiterBack = ta.getDrawable(R.styleable.ML_EditText_delimiterBack);

        editTextColor = ta.getColor(R.styleable.ML_EditText_editTextColor, 0);
        editWidth = (int) (ta.getDimension(R.styleable.ML_EditText_editWidth, 0));
        editHint = ta.getString(R.styleable.ML_EditText_editHint);
        text = ta.getString(R.styleable.ML_EditText_text);
        textSize = (int) (ta.getDimension(R.styleable.ML_EditText_textSize, 0) / getResources().getDisplayMetrics().density);
        inputType = ta.getInt(R.styleable.ML_EditText_inputTypeEdit, 0);
        delimiterWidth = (int) (ta.getDimension(R.styleable.ML_EditText_delimiterWidth, 0));
        fontFamilyId = ta.getResourceId(R.styleable.ML_EditText_fontFamily, 0);
        editSplitter = ta.getBoolean(R.styleable.ML_EditText_editSplitter, false);
        validationType = ta.getInt(R.styleable.ML_EditText_validationType, 0);
        textMaxLine = ta.getInteger(R.styleable.ML_EditText_textMaxLine, 1);
        textMaxLength = ta.getInteger(R.styleable.ML_EditText_textMaxLength, 1);


        setBackground(normalBack);
        setGravity(Gravity.CENTER);
        setOrientation(HORIZONTAL);
        configEditText();
        delimiterLayout();
        configIcon();


    }
    //______________________________________________________________________________________________ ML_EditText


    //______________________________________________________________________________________________ configEditText
    private void configEditText() {
        editText = new EditText(context);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(editWidth, LayoutParams.WRAP_CONTENT);
        editText.setTextColor(editTextColor);
        editText.setGravity(Gravity.CENTER);
        if (inputType > 0)
            editText.setInputType(inputType);
        editText.setHint(editHint);
        editText.setText(text);
        editText.setTextSize(textSize);
        editText.setBackgroundColor(context.getResources().getColor(R.color.ML_Transparent));
        editText.setLayoutParams(params);
        editText.setPadding(10, 2, 10, 2);
        editText.addTextChangedListener(textChangeForChangeBack(editText, editSplitter));
        editText.setMaxLines(textMaxLine);
        InputFilter[] fArray = new InputFilter[1];
        fArray[0] = new InputFilter.LengthFilter(textMaxLength);
        editText.setFilters(fArray);
        if (fontFamilyId > 0)
            editText.setTypeface(ResourcesCompat.getFont(getContext(), fontFamilyId));
        addView(editText, params);
    }
    //______________________________________________________________________________________________ configEditText


    //______________________________________________________________________________________________ delimiterLayout
    private void delimiterLayout() {
        LinearLayout delimiter = new LinearLayout(context);
        LinearLayout.LayoutParams params = new LayoutParams(delimiterWidth, LayoutParams.MATCH_PARENT);
        params.setMargins(delimiter_marginLeft, delimiter_marginTop, delimiter_marginRight, delimiter_marginBottom);
        delimiter.setLayoutParams(params);
        delimiter.setBackground(delimiterBack);
        addView(delimiter, params);
    }
    //______________________________________________________________________________________________ delimiterLayout


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


    //______________________________________________________________________________________________ textChangeForChangeBack
    private TextWatcher textChangeForChangeBack(EditText edit, boolean splitter) {

        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                setBackground(normalBack);
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (splitter) {
                    String value = edit.getText().toString();
                    if (value.isEmpty())
                        return;
                    value = value.replaceAll(",", "");
                    value = value.replaceAll("٬", "");
                    edit.removeTextChangedListener(this);
                    edit.setText(splitNumberOfAmount(Long.valueOf(value)));
                    edit.addTextChangedListener(this);
                    edit.setSelection(edit.getText().length());
                }
            }
        };

    }
    //______________________________________________________________________________________________ textChangeForChangeBack


    //______________________________________________________________________________________________ splitNumberOfAmount
    public String splitNumberOfAmount(Long amount) {
        NumberFormat formatter = new DecimalFormat("#,###");
        return formatter.format(amount);
    }
    //______________________________________________________________________________________________ splitNumberOfAmount


    //______________________________________________________________________________________________ getText
    public String getText() {
        return text;
    }
    //______________________________________________________________________________________________ getText


    //______________________________________________________________________________________________ getEditText
    public EditText getEditText() {
        return editText;
    }
    //______________________________________________________________________________________________ getEditText


    //______________________________________________________________________________________________ setErrorLayout
    public void setErrorLayout(String error) {
        setBackground(emptyBack);
        getEditText().setError(error);
        getEditText().requestFocus();
    }
    //______________________________________________________________________________________________ setErrorLayout


    //______________________________________________________________________________________________ setText
    @BindingAdapter("text")
    public static void setText(ML_EditText view, String newValue) {
        if (view == null || newValue == null)
            return;

        if (view.text == null) {
            view.text = newValue;
            view.getEditText().setText(view.text);
            return;
        }

        if (!view.text.equalsIgnoreCase(newValue)) {
            view.text = newValue;
            view.getEditText().setText(view.text);
        }
    }
    //______________________________________________________________________________________________ setText


    //______________________________________________________________________________________________ setText
    @InverseBindingAdapter(attribute = "text")
    public static String getText(ML_EditText view) {
        if (view != null)
            view.text = view.getEditText().getText().toString();
        return view.getText();
    }
    //______________________________________________________________________________________________ setText


    //______________________________________________________________________________________________ setListeners
    @BindingAdapter("textAttrChanged")
    public static void setListeners(ML_EditText view, final InverseBindingListener listener) {
        if (listener != null) {
            view.getEditText().addTextChangedListener(new TextWatcher() {

                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void afterTextChanged(Editable editable) {
                    listener.onChange();
                }
            });
        }
    }
    //______________________________________________________________________________________________ setListeners


    //______________________________________________________________________________________________ checkValidation
    public boolean checkValidation() {
        boolean result = true;
        switch (validationType) {
            case 0://none
                result = true;
                break;

            case 1://mobile
                result = validations.mobileValidation(getEditText().getText().toString());
                break;

            case 2://text
                result = validations.textValidation(getEditText().getText().toString());
                break;

            case 3://email
                result = validations.emailValidation(getEditText().getText().toString());
                break;
        }

        return result;
    }
    //______________________________________________________________________________________________ checkValidation


}
