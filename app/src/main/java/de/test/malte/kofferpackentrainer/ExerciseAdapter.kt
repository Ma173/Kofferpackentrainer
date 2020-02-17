package de.test.malte.kofferpackentrainer

import android.content.Context
import android.text.Layout
import android.view.LayoutInflater
import android.widget.BaseAdapter

class ExerciseAdapter (private val context: Context,
                       private val dataSource: ArrayList<Exercise>) : BaseAdapter(){
    private val inflater: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
}