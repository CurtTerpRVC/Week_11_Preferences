/*
 Name: Curt Terpstra
 Class: CIS-245-OA010 (Spring 2021)
 App: Week 11 Preferences
*/

package edu.rockvalleycollege.week11preferences

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val txtEmail = findViewById<EditText>(R.id.txtEmail) as EditText
        val btnSetPreferences = findViewById<Button>(R.id.btnSubmit) as Button

        val preferences = getSharedPreferences("data", Context.MODE_PRIVATE)

        txtEmail.setText(preferences.getString("email",""))

        btnSetPreferences.setOnClickListener {

            val editor = preferences.edit()

            hideKeyboard()

            editor.putString("email",txtEmail.text.toString())
            editor.commit()
            finish()

        }// end of onclick listener

        findViewById<View>(android.R.id.content).setOnTouchListener { _, event ->
            hideKeyboard()
            false
        }// End of hidekeyboard findviewbyid

    }//end of Oncreate

    fun hideKeyboard() {
        try {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)
        } catch (e: Exception) {
            // TODO: handle exception
        }
    }

}// end of MainActivity