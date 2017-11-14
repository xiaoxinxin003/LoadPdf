package com.example.focus.loadpdf;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.listener.OnLoadCompleteListener;

import java.io.InputStream;

public class MainActivity extends AppCompatActivity implements NetCallBack{

    private static final String TAG = "my-log";
    private PDFView mPdfView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPdfView = (PDFView) findViewById(R.id.pdfView);

        NetworkAsyncTask.AsyncRequest("http://www.gov.cn/zhengce/pdfFile/2017_PDF.pdf", this);

//        loadPdf();
    }

    private void loadPdf() {
//        mPdfView.fromAsset("test.pdf")
//                .defaultPage(1)
//        .enableSwipe(true)
//        .load();

    }

    @Override
    public void onSuccess(InputStream inputStream) {
        if (inputStream != null) {
            mPdfView.fromStream(inputStream)
                    .enableAnnotationRendering(false)
                    .password(null)
                    .scrollHandle(null)
            .enableSwipe(true)
                    .onLoad(new OnLoadCompleteListener() {
                        @Override
                        public void loadComplete(int nbPages) {
                            Log.d(TAG, "LOAD COMPLETE");
                        }
                    })
            .load();
        }
    }

    @Override
    public void onError(Exception e) {

    }
}
