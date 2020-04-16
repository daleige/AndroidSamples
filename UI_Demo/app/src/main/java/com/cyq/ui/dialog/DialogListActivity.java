package com.cyq.ui.dialog;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.cyq.ui.R;
import com.cyq.ui.dialog.builder.DialogBuilder;
import com.cyq.ui.dialog.builder.MessageDialogBuilder;
import com.cyq.ui.dialog.dialog.TestDialog;

public class DialogListActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mBtnMessageDialog;
    private Button mButton2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog_list);
        initView();
    }

    private void initView() {
        mBtnMessageDialog = findViewById(R.id.btn_message_dialog);
        mButton2 = findViewById(R.id.button2);

        mBtnMessageDialog.setOnClickListener(this);
        mButton2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_message_dialog:
                new MessageDialogBuilder(this)
                        .setTitle("测试标题...")
                        .setMessage("测试内容内容......")
                        .setCanceledOnTouchOutside(false)
                        .show();
                break;
            case R.id.button2:
                TestDialog dialog = new TestDialog(this);
                dialog.show();
                break;
        }
    }
}
