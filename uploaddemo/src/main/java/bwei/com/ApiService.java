package bwei.com;

import java.util.HashMap;
import java.util.List;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import retrofit2.http.HeaderMap;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

/**
 * Name:  The.xue
 * Date  2019-06-18
 */
public interface ApiService {

    /**
     * 多图
     * @param headers
     * @param file
     * @return
     */
    @POST(Api.UPLOAD_URL)
    @Multipart
    Observable<UpLoadEntity> uploadPics(@HeaderMap HashMap<String, String> headers, @Part List<MultipartBody.Part> file);

}
