/*
 Name: Curt Terpstra
 Class: CIS-245-OA010 (Spring 2021)
 App: Week 11 Preferences
*/

package edu.rockvalleycollege.week11preferences

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnGotoPreferences2 = findViewById<Button>(R.id.btnGotoPreferences2)
        val btnRefresh = findViewById<Button>(R.id.btnRefresh)
        val btnClearData = findViewById<Button>(R.id.btnClearData)

        refresh()

        btnGotoPreferences2.setOnClickListener {

            var txtSendInfo = "EmptyString"

            //Intent is used to send data between activities
            val intent = Intent(this, Preferences2::class.java)
            //putExtra sets value to name SendStuff (Could be called whatever you want
            intent.putExtra("SendStuff", txtSendInfo)

            //Go to second activity
            startActivity(intent)

        }// end of GotoPreferences2 onclick listener

        // Refresh button
        btnRefresh.setOnClickListener{
            refresh()
        }

        btnClearData.setOnClickListener {
            val preferences = getSharedPreferences("data", Context.MODE_PRIVATE)
            val editor = preferences.edit()

            editor.putString("email","")
            editor.commit()

            refresh()
        }

        findViewById<View>(android.R.id.content).setOnTouchListener { _, event ->
            hideKeyboard()
            false
        }// End of hidekeyboard findviewbyid

    }//end of Oncreate

    fun refresh(){
        val txtSavedEmail = findViewById<TextView>(R.id.txtSavedEmail)
        val preferences = getSharedPreferences("data", Context.MODE_PRIVATE)

        txtSavedEmail.text = preferences.getString("email","").toString()

    }

    fun hideKeyboard() {
        try {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)
        } catch (e: Exception) {
            // TODO: handle exception
        }
    }// end of Hidekeyboard

}// end of MainActivity