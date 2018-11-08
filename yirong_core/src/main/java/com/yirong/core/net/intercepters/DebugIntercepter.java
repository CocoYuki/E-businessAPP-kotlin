package com.yirong.core.net.intercepters;

import android.support.annotation.RawRes;


import com.yirong.core.utils.file.FileUtils;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.Protocol;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * Created by yiron on 2018/11/3.
 */

public class DebugIntercepter extends BaseIntercepter {

    private final String DEBUG_URL;
    private final int DEBUG_RAW_ID;

    public DebugIntercepter(String debug_url, int debug_raw_id) {
        DEBUG_URL = debug_url;
        DEBUG_RAW_ID = debug_raw_id;
    }

    private Response getResponse(Chain chain,String json){
        return new Response.Builder()
                .code(200)
                .addHeader("content-type","application/json")
                .body(ResponseBody.create(MediaType.parse("application/json"),json))
                .message("OK")
                .request(chain.request())
                .protocol(Protocol.HTTP_1_1)
                .build();
    }

    private Response debugResponse(Chain chain, @RawRes int rawId){
        final String json = FileUtils.getRawFile(rawId);
        return getResponse(chain,json);
    }
    @Override
    public Response intercept(Chain chain) throws IOException {
        final String url = chain.request().url().toString();
        if(url.contains(DEBUG_URL)){
            return debugResponse(chain,DEBUG_RAW_ID);
        }
        return chain.proceed(chain.request());
    }
}
