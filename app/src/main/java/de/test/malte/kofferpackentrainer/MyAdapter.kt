package de.test.malte.kofferpackentrainer

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView

class MyAdapter (var mCtx:Context, var resources:Int, var items:List<Model>):ArrayAdapter<Model>(mCtx, resources, items){
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layoutInflater:LayoutInflater = LayoutInflater.from(mCtx)
        val view:View = layoutInflater.inflate(resources,null)

        val imageView:ImageView = view.findViewById(R.id.image0)
        val titleTextView:TextView = view.findViewById(R.id.textView1)
        val descriptionTextView:TextView = view.findViewById(R.id.textView2)

        var mItem:Model = items[position]
        val imageVal=mItem.img
        if (imageVal!=null){
            imageView.setImageDrawable(mCtx.resources.getDrawable(imageVal))
        }

        titleTextView.text = mItem.title
        descriptionTextView.text = mItem.description

        return view
    }

}

class MyAdapter2 (var mCtx:Context, var resources:Int, var items:List<Model2>):ArrayAdapter<Model2>(mCtx, resources, items){
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layoutInflater:LayoutInflater = LayoutInflater.from(mCtx)
        val view:View = layoutInflater.inflate(resources,null)

        val imageView:ImageView = view.findViewById(R.id.image0)
        val titleTextView:TextView = view.findViewById(R.id.textView1)

        var mItem:Model2 = items[position]
        val imageVal=mItem.img
        if (imageVal!=null){
            imageView.setImageDrawable(mCtx.resources.getDrawable(imageVal))
        }

        titleTextView.text = mItem.title

        return view
    }

}