package com.example.assignment04

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.database.*

class MainActivity : AppCompatActivity() {
    private lateinit var databaseReference:DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val database=Database(this)
        val list=ArrayList<Student>()
        val studentContainer=findViewById<ListView>(R.id.student_container)

        val dialog=ProgressDialog(this)
        dialog.setMessage("Please Wait...")
        dialog.show()


        databaseReference=FirebaseDatabase.getInstance().getReference("students")
        databaseReference.addValueEventListener(object:ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists()){
                    list.clear()
                    for(shots in snapshot.children){
                        val c=shots.getValue(Student::class.java)
                        list.add(c!!)
                    }
                    studentContainer.adapter=StudentAdapter(applicationContext, list)
                }
            }
            override fun onCancelled(error: DatabaseError) {}
        })
        dialog.dismiss()

        findViewById<FloatingActionButton>(R.id.add_new_btn).setOnClickListener{
            startActivity(Intent(this, AddNewStudent::class.java))
            finish()
        }
        studentContainer.setOnItemClickListener { _, view, _, _ ->
            val dialog=AlertDialog.Builder(this)
            dialog.setTitle("Confirm Deletion")
            dialog.setMessage("Are you Sure?")
            dialog.setNegativeButton("Cancel"){_, _ -> }
            dialog.setPositiveButton("Delete") { _, _ ->
                database.delete(view.findViewById<TextView>(R.id.reg_no).text.toString())
            }
            dialog.show()
        }
    }
}