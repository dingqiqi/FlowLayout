package com.dingqiqi.testflowlayout;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private FlowLayout mFlowLayout;

    private String[] datas = new String[]{
            "新手", "乐享", "新手", "乐享", "钱包", "新手", "乐享", "钱包",
            "新手计划", "乐享活系列90天计划", "钱包钱包钱包",
            "30天理财计划(加息2%)",
            "林业局投资商业经营与大捞一笔",
            "中学老师购买车辆", "屌丝下海经商计划",
            "新西游影视拍", "Java培训老师自己周转", "HelloWorld", "C++-C-ObjectC-java", "Android vs ios", "算法与数据结构", "JNI与NDK", "team working"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFlowLayout = (FlowLayout) findViewById(R.id.flowlayout);
        mFlowLayout.removeAllViews();

        for (int i = 0; i < datas.length; i++) {
            final String str = datas[i];
            TextView textView = new TextView(this);
            textView.setText(str);
            textView.setTextSize(14);
            textView.setTextColor(Color.WHITE);
            textView.setBackgroundResource(R.drawable.label_selector);
            textView.setPadding(10, 5, 0, 2);
            textView.setGravity(Gravity.CENTER);
            mFlowLayout.addView(textView);

            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(MainActivity.this, str, Toast.LENGTH_SHORT).show();
                }
            });
        }

    }
}
