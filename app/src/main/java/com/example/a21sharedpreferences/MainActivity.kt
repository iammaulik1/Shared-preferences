package com.example.a21sharedpreferences

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private val sharedPreFile ="SharedPreference"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val inputId = findViewById<EditText>(R.id.editId)
        val inputName = findViewById<EditText>(R.id.editName)
        val outputId = findViewById<TextView>(R.id.textViewshowId)
        val outputName = findViewById<TextView>(R.id.textViewShowName)

        val saveButton = findViewById<Button>(R.id.Save)
        val viewButton = findViewById<Button>(R.id.View)
        val clearButton = findViewById<Button>(R.id.Clear)

        val sharedPreferences:SharedPreferences = this.getSharedPreferences(sharedPreFile,Context.MODE_PRIVATE)

        saveButton.setOnClickListener(View.OnClickListener {
            val id:Int = Integer.parseInt(inputId.text.toString())
            val name:String = inputName.text.toString()
            val editor:SharedPreferences.Editor = sharedPreferences.edit()
            editor.putInt("id_key",id)
            editor.putString("name_key",name)
            editor.apply()
            editor.commit()
            Toast.makeText(applicationContext,"Data Saved",Toast.LENGTH_SHORT).show()
        })

        viewButton.setOnClickListener {
            val sharedIdValue = sharedPreferences.getInt("id_key",0)
            val sharedNameValue = sharedPreferences.getString("name_key","defaultname")
            if(sharedIdValue.equals(0)&&sharedNameValue.equals("defaultname")){
                outputName.setText("default name : ${sharedNameValue}").toString()
                outputId.setText("default Id : ${sharedIdValue.toString()}")
            }else{
                outputName.setText(sharedNameValue).toString()
                outputId.setText(sharedIdValue.toString())
            }
        }

        clearButton.setOnClickListener(View.OnClickListener {
            val editor = sharedPreferences.edit()
            editor.clear()
            editor.apply()
            outputName.setText("").toString()
            outputId.setText("".toString())
        })
    }
}