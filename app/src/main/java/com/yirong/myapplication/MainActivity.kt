package com.yirong.myapplication

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.joanzapata.iconify.fonts.FontAwesomeModule
import com.yirong.core.app.Suger
import com.yirong.yirong_sc.FontEcModule

class MainActivity : AppCompatActivity() {
    private val url = "";
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        Suger.init(this)
                .withIcons(FontEcModule())
                .withIcons(FontAwesomeModule())
                .withAPIHost(url)
                .configure()
        setContentView(R.layout.activity_main)
    }
}
