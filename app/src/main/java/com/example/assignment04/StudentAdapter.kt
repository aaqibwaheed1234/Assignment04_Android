package com.example.assignment04

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.database.ValueEventListener

class StudentAdapter(context: Context, list:ArrayList<Student>):BaseAdapter() {
    var context:Context
    var list:ArrayList<Student>
    init {
        this.context=context
        this.list=list
    }

    override fun getCount(): Int {
        return list.size
    }

    override fun getItem(position: Int): Any {
        return list[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = LayoutInflater.from(context).inflate(R.layout.single_student_layout, null, false)
        view.findViewById<TextView>(R.id.reg_no).text=list[position].regNo
        view.findViewById<TextView>(R.id.name).text=list[position].name
        view.findViewById<TextView>(R.id.cgpa).text=list[position].cgpa
        view.findViewById<TextView>(R.id.age).text=list[position].age
        view.findViewById<TextView>(R.id.phone).text=list[position].phone
        return view
    }
}