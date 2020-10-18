package ir.ngra.automation.views.customs.searchspinner;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import ir.ngra.automation.R;
import ir.ngra.automation.models.MD_SpinnerItem;


public class MLSpinnerDialog {
    ArrayList<String> items;
    Activity context;
    String dTitle, closeTitle = "Close";
    OnSpinnerItemClick onSpinnerItemClick;
    AlertDialog alertDialog;
    int pos;
    int style;
    boolean cancellable = false;
    boolean showKeyboard = false;

    public MLSpinnerDialog(Activity activity, ArrayList<String> items, String dialogTitle) {
        this.items = items;
        this.context = activity;
        this.dTitle = dialogTitle;
    }

    public MLSpinnerDialog(Activity activity, ArrayList<String> items, String dialogTitle, String closeTitle) {
        this.items = items;
        this.context = activity;
        this.dTitle = dialogTitle;
        this.closeTitle = closeTitle;
    }

    public MLSpinnerDialog(Activity activity, ArrayList<String> items, String dialogTitle, int style) {
        this.items = items;
        this.context = activity;
        this.dTitle = dialogTitle;
        this.style = style;
    }

    public MLSpinnerDialog(Activity activity, ArrayList<MD_SpinnerItem> list, String dialogTitle, int style, String closeTitle) {
        this.items = new ArrayList<>();
        this.items.clear();
        for (MD_SpinnerItem item : list) {
            this.items.add(item.getTitle());
        }
        this.context = activity;
        this.dTitle = dialogTitle;
        this.style = style;
        this.closeTitle = closeTitle;
    }

    public void bindOnSpinnerListener(OnSpinnerItemClick onSpinnerItemClick1) {
        this.onSpinnerItemClick = onSpinnerItemClick1;
    }

    public void showSpinnerDialog() {
        AlertDialog.Builder adb = new AlertDialog.Builder(context);
        View v = context.getLayoutInflater().inflate(R.layout.dialog_spinner_layout, null);
        TextView rippleViewClose = (TextView) v.findViewById(R.id.close);
        TextView title = (TextView) v.findViewById(R.id.spinnerTitle);
        rippleViewClose.setText(closeTitle);
        title.setText(dTitle);
        title.setGravity(Gravity.RIGHT);
        final ListView listView = (ListView) v.findViewById(R.id.list);
/*        final ML_EditText ml_EditTextSearch = (ML_EditText) v.findViewById(R.id.ml_EditTextSearch);
        if (isShowKeyboard()) {
            showKeyboard(ml_EditTextSearch);
        }*/
        final ArrayAdapter<String> adapter = new ArrayAdapter<>(context, R.layout.adapter_spinner_item, items);
        listView.setAdapter(adapter);
        adb.setView(v);
        alertDialog = adb.create();
        alertDialog.getWindow().getAttributes().windowAnimations = style;//R.style.DialogAnimations_SmileWindow;

        listView.setOnItemClickListener((AdapterView.OnItemClickListener) (adapterView, view, i, l) -> {
            TextView t = (TextView) view.findViewById(R.id.text1);
            for (int j = 0; j < items.size(); j++) {
                if (t.getText().toString().equalsIgnoreCase(items.get(j))) {
                    pos = j;
                }
            }
            onSpinnerItemClick.onClick(t.getText().toString(), pos);
            closeSpinnerDialog();
        });

/*        ml_EditTextSearch.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                adapter.getFilter().filter(ml_EditTextSearch.getText());
            }
        });*/

        rippleViewClose.setOnClickListener(v1 -> closeSpinnerDialog());
        alertDialog.setCancelable(isCancellable());
        alertDialog.setCanceledOnTouchOutside(isCancellable());
        alertDialog.show();
    }

    public void closeSpinnerDialog() {
        hideKeyboard();
        if (alertDialog != null) {
            alertDialog.dismiss();
        }
    }

    private void hideKeyboard() {
        try {
            InputMethodManager inputManager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
            inputManager.hideSoftInputFromWindow(context.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        } catch (Exception e) {
        }
    }

/*    private void showKeyboard(final ML_EditText edit) {
        edit.requestFocus();
        edit.postDelayed(() -> {
            InputMethodManager keyboard = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
            keyboard.showSoftInput(edit, 0);
        }
                , 200);
    }*/

    private boolean isCancellable() {
        return cancellable;
    }

    public void setCancellable(boolean cancellable) {
        this.cancellable = cancellable;
    }

    private boolean isShowKeyboard() {
        return showKeyboard;
    }

    public void setShowKeyboard(boolean showKeyboard) {
        this.showKeyboard = showKeyboard;
    }
}
