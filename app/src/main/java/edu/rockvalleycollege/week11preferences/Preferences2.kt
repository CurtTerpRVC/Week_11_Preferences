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
import android.widget.Toast

class Preferences2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_preferences2)

        val txtEmail2 = findViewById<EditText>(R.id.txtEmail2) as EditText
        val btnEnterPrefs = findViewById<Button>(R.id.btnEnterPrefs)

        val preferences = getSharedPreferences("data", Context.MODE_PRIVATE)

        txtEmail2.setText(preferences.getString("email",""))

        btnEnterPrefs.setOnClickListener {

            val editor = preferences.edit()

            hideKeyboard()

            editor.putString("email",txtEmail2.text.toString())
            editor.commit()

            //val intent = Intent(this, MainActivity::class.java)
            //startActivity(intent)
            onBackPressed()
            finish()
            //Toast.makeText(this,"After Finish",Toast.LENGTH_LONG).show()

        }// end of onclick listener

        findViewById<View>(android.R.id.content).setOnTouchListener { _, event ->
            hideKeyboard()
            false
        }// End of hidekeyboard findviewbyid

    }// end of oncreate

    fun hideKeyboard() {
        try {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)
        } catch (e: Exception) {
            // TODO: handle exception
        }
    }
}// end of Preferences2 activity