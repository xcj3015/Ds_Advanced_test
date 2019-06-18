package bwei.com;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Name:  The.xue
 * Date  2019-06-18
 */
public class RetrofitUltis {

    private static RetrofitUltis mInstance;//私有静态
    private Retrofit retrofit;

    private RetrofitUltis(){//私有构造方法
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                //日志拦截器
                .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .build();
        retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .client(okHttpClient)//关联ok
                .addConverterFactory(GsonConverterFactory.create())//添加Gson数据转换器
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())//添加请求回调适配器
                .build();

    }

    public static RetrofitUltis getInstance(){
        if (mInstance==null){
            synchronized (RetrofitUltis.class){
                if (mInstance==null){
                    mInstance = new RetrofitUltis();
                }
            }
        }

        return mInstance;
    }

    /**
     * 动态代理模式创建servicee对象
     * @param clz
     * @param <T>
     * @return
     */
    public <T> T createService(Class<T> clz){

        return  retrofit.create(clz);
    }

}
