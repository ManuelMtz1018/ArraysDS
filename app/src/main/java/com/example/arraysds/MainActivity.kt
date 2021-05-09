package com.example.arraysds

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import com.example.arraysds.control.ArraysDS
import com.example.arraysds.databinding.ActivityMainBinding
import java.time.LocalDate

class MainActivity : AppCompatActivity(),View.OnClickListener {

    val MAXIMUN_VALUE=100
    val MINIMUM_VALUE=-100
    lateinit var listForMatrixDS:ArrayList<ArrayList<Int>>
    lateinit var binding:ActivityMainBinding;

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding=DataBindingUtil.setContentView(this,R.layout.activity_main)
        var date:LocalDate=LocalDate.now();
        binding.textTitle.setText("Welcome, "+date.month+" "+date.dayOfMonth+", "+date.year)
        binding.buttonGenerate.setOnClickListener(this)
        binding.buttonCalculate.setOnClickListener(this)
        listForMatrixDS= arrayListOf()
    }

    override fun onClick(view: View?) {
        var option=view as Button
        when(option.id){
            R.id.buttonGenerate->{
                Log.d("Generate","///////////// Generate")
                listForMatrixDS= generateRandomInputs()
            }
            R.id.buttonCalculate->{
                Log.d("Generate","///////////// Calculate")
                var dsValue=ArraysDS.hourglassSum(listForMatrixDS)
                binding.textViewMatrix.setText("Result:"+dsValue)
            }
        }
    }
    fun generateRandomInputs():ArrayList<ArrayList<Int>>{
        var listValues:ArrayList<ArrayList<Int>>
        listValues= arrayListOf()
        for(row in 6 downTo 1) {
            var listInputs: ArrayList<Int>
            listInputs = arrayListOf()
            for (col in 6 downTo 1) {
                var input = (Math.random() * (MAXIMUN_VALUE - MINIMUM_VALUE) + MINIMUM_VALUE)
                listInputs.add(input.toInt())
            }
            Log.d("List", "************ " + listInputs)
            listValues.add(listInputs)
        }
        Log.d("List", "************ " + listValues)
        var matrixValues:String=""
        for (values in listValues){
            var items=values.toString().replace(","," ")
            items=items.replace("[","")
            items=items.replace("]","")
            matrixValues+=items+"\n"
        }
        binding.textViewMatrix.setText(matrixValues)
        return listValues
    }
}