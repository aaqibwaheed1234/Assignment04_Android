@file:Suppress("DEPRECATION")

package com.example.assignment04

import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.content.Context
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class Database(context: Context) {
    private val db: DatabaseReference = FirebaseDatabase.getInstance().reference
    private var context: Context

    init {
        this.context = context
    }

    @SuppressLint("Recycle")
    fun insert(student: Student) {
        val dialog = ProgressDialog(context)
        dialog.setMessage("Please Wait...")
        dialog.show()
        db.child("students").child(student.regNo).setValue(student).addOnSuccessListener {
            dialog.dismiss()
        }.addOnFailureListener {
            dialog.dismiss()
            Toast.makeText(context, "Records not inserted...", Toast.LENGTH_SHORT).show()
        }
    }

    fun delete(regNo: String) {
        val dialog = ProgressDialog(context)
        dialog.setMessage("Please Wait...")
        dialog.show()
        db.child("students").child(regNo).removeValue().addOnSuccessListener {
            dialog.dismiss()
        }.addOnFailureListener {
            dialog.dismiss()
            Toast.makeText(context, "Records Not deleted...", Toast.LENGTH_SHORT).show()
        }

    }
}