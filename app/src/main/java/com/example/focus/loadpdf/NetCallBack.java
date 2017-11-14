package com.example.focus.loadpdf;

import java.io.InputStream;

/**
 * Created by focus on 2017/11/14.
 */

public interface NetCallBack {
    void  onSuccess(InputStream inputStream);
    void onError(Exception e);
}
