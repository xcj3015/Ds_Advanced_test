package bwei.com;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.uuzuche.lib_zxing.activity.CodeUtils;

/**
 * Name:  The.xue
 * Date  2019-06-06
 */
public class TwoActivity extends AppCompatActivity {

    private EditText editText;
    private Bitmap mBitmap;
    private ImageView img;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_capture);

        editText = findViewById(R.id.ed);
        Button bt_1 = findViewById(R.id.bt_1);
        Button bt_2 = findViewById(R.id.bt_2);
        img = findViewById(R.id.img);

        bt_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String textContent = editText.getText().toString();
                if (TextUtils.isEmpty(textContent)) {
                    Toast.makeText(TwoActivity.this, "您的输入为空!", Toast.LENGTH_SHORT).show();
                    return;
                }
                editText.setText("");
                mBitmap = CodeUtils.createImage(textContent, 400, 400, BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher));
                img.setImageBitmap(mBitmap);
            }
        });

        bt_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String textContent = editText.getText().toString();
                if (TextUtils.isEmpty(textContent)) {
                    Toast.makeText(TwoActivity.this, "您的输入为空!", Toast.LENGTH_SHORT).show();
                    return;
                }
                editText.setText("");
                mBitmap = CodeUtils.createImage(textContent, 400, 400, null);
                img.setImageBitmap(mBitmap);
            }
        });

    }
}
