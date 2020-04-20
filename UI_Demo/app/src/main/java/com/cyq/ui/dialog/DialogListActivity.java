package com.cyq.ui.dialog;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.cyq.ui.R;
import com.cyq.ui.dialog.action.ActionListener;
import com.cyq.ui.dialog.dialog.BaseDialog;
import com.cyq.ui.dialog.dialog.MyDialog;

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
//                BaseDialog dialog =new MyDialog.MessageDialogBuilder(this)
//                        .setTitle("真的要放弃购买吗？")
//                        .setMessage("您可能会失去会员曲库、无损音质、手机铃声，免费下载等特权！")
//                        .setCanceledOnTouchOutside(true)
//                        .setNegativeAction("残忍离开", new ActionListener() {
//                            @Override
//                            public void onClick(BaseDialog dialog, int index) {
//                                dialog.dismiss();
//                            }
//                        })
//                        .setPositiveAction("继续支付", new ActionListener() {
//                            @Override
//                            public void onClick(BaseDialog dialog, int index) {
//                                // 响应点击事件
//                                Toast.makeText(DialogListActivity.this, "点击确认！", Toast.LENGTH_SHORT).show();
//                            }
//                        })
//                        .show();
                break;
            case R.id.button2:
                break;
        }
    }
}
