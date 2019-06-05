package bwei.com;

import com.uuzuche.lib_zxing.activity.ZXingLibrary;

/**
 * Name:  The.xue
 * Date  2019-06-05
 */
public class MyApplication extends android.app.Application {

    @Override
    public void onCreate() {
        super.onCreate();

        ZXingLibrary.initDisplayOpinion(this);
    }
}
