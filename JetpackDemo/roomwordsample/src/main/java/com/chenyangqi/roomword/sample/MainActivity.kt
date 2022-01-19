package com.chenyangqi.roomword.sample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

       // WordRoomDatabase.getDatabase(this).wordDao().getAlphabetizedWords()
    }
}