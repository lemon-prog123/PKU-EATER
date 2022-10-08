package com.example.activitytest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ViewDebug.IntToString
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import com.loper7.date_time_picker.DateTimePicker
import com.loper7.date_time_picker.DateTimeConfig
import java.util.Calendar
import java.util.Date

class CountActivity : AppCompatActivity() {
    private var mWeightRuler: RulerView? = null
    private var mHeightRuler: RulerView? = null
    private var mTvWeight: TextView? = null
    private var mTvHeight: TextView? = null

    private var weight: Float = 55f
    private var height: Int = 165
    private var gender:Int=1
    private var monthOfYear:Int=0
    private var dayOfMonth:Int=0
    private var year:Int=0
    private var date:Date=Date(0)

    private fun dateLayout()
    {
        setContentView(R.layout.datapicker)
        val picker:DateTimePicker=findViewById(R.id.picker)
        picker.setDisplayType(intArrayOf(
            DateTimeConfig.YEAR,//显示年
            DateTimeConfig.MONTH,//显示月
            DateTimeConfig.DAY))
        picker.showLabel(true)
        picker.setOnDateTimeChangedListener {
            var calendar  = Calendar.getInstance()
            calendar.timeInMillis = it
            val time=calendar.time
            date=time
            monthOfYear=time.month+1
            year=time.year+1900
            dayOfMonth=time.date
            Log.d("Date",date.toString())
            Log.d("Month",monthOfYear.toString())
            Log.d("Year",year.toString())
            Log.d("Day",dayOfMonth.toString())

        }
        val btn_back:Button=findViewById(R.id.btn_back)
        btn_back.setOnClickListener{
            selectorLayout()
        }
    }

    private fun selectorLayout()
    {
        setContentView(R.layout.sex_selector)
        Log.d("Weight",weight.toString())
        Log.d("Height",height.toString())
        val button_back:Button=findViewById(R.id.btn_back)
        button_back.setOnClickListener {
            mainLayout()
        }
        val button_next:Button=findViewById(R.id.btn_date)
        button_next.setOnClickListener {
            dateLayout()
        }
        val check_sex:CheckBox=findViewById(R.id.btn_register_info_sex)
        check_sex.setOnClickListener{
            if (check_sex.isChecked())
                gender=2
            else
                gender=1
            Log.d("Gender",gender.toString())
        }
    }

    private fun mainLayout()
    {
        setContentView(R.layout.activity_main)
        mWeightRuler = findViewById(R.id.ruler_weight)
        mHeightRuler = findViewById(R.id.ruler_height)
        mTvWeight= findViewById(R.id.tv_weight)
        mTvHeight= findViewById(R.id.tv_height)
        val button1: Button = findViewById(R.id.button1)
        val button_skip:Button=findViewById(R.id.outlinedButton)
        button1.setOnClickListener {
            finish()
        }

        button_skip.setOnClickListener{
            selectorLayout()
        }

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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainLayout()
    }
}