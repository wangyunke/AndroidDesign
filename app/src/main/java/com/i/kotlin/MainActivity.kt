package com.i.kotlin

import android.os.Bundle
import android.os.PersistableBundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.i.designpattern.R

class MainActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(R.layout.activity_knowledge)

        findViewById<TextView>(R.id.tv).scaleSize(2F)
    }

}

/**
 * 扩展函数
 */
fun TextView.scaleSize(scale: Float){
    this.textSize = this.textSize*scale
}