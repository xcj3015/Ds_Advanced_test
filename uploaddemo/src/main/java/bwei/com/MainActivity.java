package bwei.com;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class MainActivity extends AppCompatActivity {

    private Button m_upload;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        m_upload = findViewById(R.id.m_upload);

    }

    /**
     * 多图上传功能
     * @param view
     */
    public void uploads(View view) {

        List<File> files = new ArrayList<>();
        //创建文件
        File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath()+"/hello.jpg");
        File file2 = new File(Environment.getExternalStorageDirectory().getAbsolutePath()+"/hello.jpg");

        files.add(file);
        files.add(file2);

        List<MultipartBody.Part> parts = new ArrayList<>();

        parts.add(MultipartBody.Part.createFormData("commodityId","6"));
        parts.add(MultipartBody.Part.createFormData("content","这件商品非常好"));

        for (File file1 : files) {//2

            RequestBody requestBody = RequestBody.create(MediaType.parse("image/*"),file1);

            MultipartBody.Part part = MultipartBody.Part.createFormData("image",file1.getName(),requestBody);

            parts.add(part);

        }
        HashMap<String,String> headers = new HashMap<>();
        headers.put("userId","");//动态sp里获取
        headers.put("sessionId","");
        RetrofitUltis.getInstance().createService(ApiService.class)
                .uploadPics(headers,parts).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<UpLoadEntity>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(UpLoadEntity uploadEntity) {

                Toast.makeText(MainActivity.this, uploadEntity.headPath, Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });



    }
}
