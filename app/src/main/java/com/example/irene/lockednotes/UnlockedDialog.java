package com.example.irene.lockednotes;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by Irene on 2018/1/4.
 */

public class UnlockedDialog extends Dialog {

    Activity context;

    private Button confirmBt;

    public EditText password;

    private View.OnClickListener mClickListener;

    public UnlockedDialog(Activity context) {
        super(context);
        this.context = context;
    }

    public UnlockedDialog(Activity context, int theme, View.OnClickListener clickListener) {
        super(context, R.style.dialog);
        this.context = context;
        this.mClickListener = clickListener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 指定布局
        this.setContentView(R.layout.lock_dialog);

        password = (EditText) findViewById(R.id.password);
        password.setTransformationMethod(PasswordTransformationMethod.getInstance());

        Window dialogWindow = this.getWindow();
        WindowManager m = context.getWindowManager();
        Display d = m.getDefaultDisplay(); // 获取屏幕宽、高用
        WindowManager.LayoutParams p = dialogWindow.getAttributes(); // 获取对话框当前的参数值
        //p.height = (int) (d.getHeight() * 0.4); // 高度设置为屏幕的0.4
        p.width = (int) (d.getWidth() * 0.8); // 宽度设置为屏幕的0.8
        dialogWindow.setAttributes(p);

        // 根据id在布局中找到控件对象
        confirmBt = (Button) findViewById(R.id.confirm_button);

        // 为按钮绑定点击事件监听器
        confirmBt.setOnClickListener(mClickListener);

        this.setCancelable(true);
    }
}
