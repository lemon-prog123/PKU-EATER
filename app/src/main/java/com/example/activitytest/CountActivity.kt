package com.example.activitytest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class CountActivity : AppCompatActivity() {
    private var mWeightRuler: RulerView? = null
    private var mHeightRuler: RulerView? = null
    private var mTvWeight: TextView? = null
    private var mTvHeight: TextView? = null
    private var weight: Float = 55f
    private var height: Int = 165
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mWeightRuler = findViewById(R.id.ruler_weight)
        mHeightRuler = findViewById(R.id.ruler_height)
        mTvWeight= findViewById(R.id.tv_weight)
        mTvHeight= findViewById(R.id.tv_height)
        val button1: Button = findViewById(R.id.button1)
        button1.setOnClickListener {
            finish()
        }

        //体重的view
        mWeightRuler!!.setOnValueChangeListener(object : RulerView.OnValueChangeListener {
            override fun onValueChange(value: Float) {
                weight = value
                mTvWeight!!.text = weight.toString() + "kg"
            }
        })
        mWeightRuler!!.setValue(55f, 20f, 200f, 0.1f)
        mTvWeight!!.text = weight.toString() + "kg"

        //身高的view
        mHeightRuler!!.setOnValueChangeListener(object : RulerView.OnValueChangeListener {
            override fun onValueChange(value: Float) {
                height = value.toInt()
                mTvHeight!!.text = height.toString() + "cm"
            }
        })
        mHeightRuler!!.setValue(165f, 80f, 250f, 1f)
        mTvHeight!!.text = height.toString() + "cm"

    }
}