@file:Suppress("DEPRECATION")

package com.example.assignment04
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class AddNewStudent : AppCompatActivity() {
    private lateinit var regNoLabel:TextView
    private lateinit var regNoInp:EditText
    private lateinit var nameLabel:TextView
    private lateinit var nameInp:EditText
    private lateinit var cgpaLabel:TextView
    private lateinit var cgpaInp:EditText
    private lateinit var ageLabel:TextView
    private lateinit var ageInp:EditText
    private lateinit var phoneLabel:TextView
    private lateinit var phoneInp:EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_new_student)

        regNoLabel=findViewById(R.id.reg_no_text)
        regNoInp=findViewById(R.id.reg_no_inp)

        nameLabel=findViewById(R.id.name_text)
        nameInp=findViewById(R.id.name_inp)

        cgpaLabel=findViewById(R.id.cgpa_text)
        cgpaInp=findViewById(R.id.cgpa_inp)

        ageLabel=findViewById(R.id.age_text)
        ageInp=findViewById(R.id.age_inp)

        phoneLabel=findViewById(R.id.phone_text)
        phoneInp=findViewById(R.id.phone_inp)

        val database=Database(this)

        regNoInp.setOnFocusChangeListener{_, focus ->
            if(focus)
                focusIn(regNoLabel, regNoInp)
            else
               focusOut(regNoLabel, regNoInp)
        }
        nameInp.setOnFocusChangeListener{_, focus ->
            if(focus)
                focusIn(nameLabel, nameInp)
            else
                focusOut(nameLabel, nameInp)
        }
        cgpaInp.setOnFocusChangeListener{_, focus ->
            if(focus)
                focusIn(cgpaLabel, cgpaInp)
            else
                focusOut(cgpaLabel, cgpaInp)
        }
        ageInp.setOnFocusChangeListener{_, focus ->
            if(focus)
                focusIn(ageLabel, ageInp)
            else
                focusOut(ageLabel, ageInp)
        }
        phoneInp.setOnFocusChangeListener{_, focus ->
            if(focus)
                focusIn(phoneLabel, phoneInp)
            else
                focusOut(phoneLabel, phoneInp)
        }
        findViewById<Button>(R.id.cancel_btn).setOnClickListener{ cancel() }
        findViewById<Button>(R.id.save_btn).setOnClickListener{
            regNoLabel.setTextColor(resources.getColor(R.color.focused_input))
            nameLabel.setTextColor(resources.getColor(R.color.focused_input))
            cgpaLabel.setTextColor(resources.getColor(R.color.focused_input))
            ageLabel.setTextColor(resources.getColor(R.color.focused_input))
            phoneLabel.setTextColor(resources.getColor(R.color.focused_input))
            regNoInp.background=resources.getDrawable(R.drawable.focused_input_background)
            nameInp.background=resources.getDrawable(R.drawable.focused_input_background)
            cgpaInp.background=resources.getDrawable(R.drawable.focused_input_background)
            ageInp.background=resources.getDrawable(R.drawable.focused_input_background)
            phoneInp.background=resources.getDrawable(R.drawable.focused_input_background)
            focusOut(regNoLabel, regNoInp)
            focusOut(nameLabel, nameInp)
            focusOut(cgpaLabel, cgpaInp)
            focusOut(ageLabel, ageInp)
            focusOut(phoneLabel, phoneInp)

            if(regNoInp.text.isEmpty())
                error(regNoLabel, regNoInp)
            else if(nameInp.text.isEmpty())
                error(nameLabel, nameInp)
            else if(cgpaInp.text.isEmpty())
                error(cgpaLabel, cgpaInp)
            else if(ageInp.text.isEmpty())
                error(ageLabel, ageInp)
            else if(phoneInp.text.isEmpty())
                error(phoneLabel, phoneInp)
            else {
                val student = Student(
                    regNoInp.text.toString(),
                    nameInp.text.toString(),
                    cgpaInp.text.toString(),
                    ageInp.text.toString(),
                    phoneInp.text.toString()
                )
                database.insert(student)
                cancel()
            }
        }
    }

    override fun onBackPressed() { cancel() }
    fun cancel(){
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    fun focusIn(label:TextView, input:EditText){
        label.animate().translationY(-45F).duration=500
        label.setTextColor(resources.getColor(R.color.focused_input))
        label.setPadding(10, 0, 10, 0)
        label.textSize=14F
        input.background=resources.getDrawable(R.drawable.focused_input_background)
        input.requestFocus()
    }
    fun focusOut(label:TextView, input:EditText){
        if(input.text.isEmpty()){
            label.animate().translationY(0F).duration=500
            label.setTextColor(resources.getColor(R.color.hint_color))
            label.setPadding(0, 0, 0, 0)
            label.textSize=20F
            input.background=resources.getDrawable(R.drawable.input_background)
        }
    }
    fun error(label:TextView, input:EditText){
        label.animate().translationY(-45F).duration=500
        label.setTextColor(resources.getColor(R.color.error_color))
        label.setPadding(10, 0, 10, 0)
        label.textSize=14F
        input.background=resources.getDrawable(R.drawable.errored_input_background)
        input.requestFocus()
    }
}