package com.chenyangqi.pdf.preview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import kotlinx.android.synthetic.main.activity_test.*
import java.io.File

class TestActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)

        mPDFView.setOffscreenPageLimit(2)
        mPDFView.isCanZoom(true)
        mPDFView.setMaxScale(10F)

        val downloadFileDir = getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS)
        val path = "${downloadFileDir?.absolutePath}${File.separator}阿里巴巴android开发手册.pdf"
        mPDFView.showPdfFromPath(path)
    }
}