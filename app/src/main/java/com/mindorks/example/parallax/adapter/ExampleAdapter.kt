package com.mindorks.example.parallax.adapter

import com.mindorks.example.parallax.model.ExampleItem
import androidx.recyclerview.widget.RecyclerView
import com.mindorks.example.parallax.adapter.ExampleAdapter.ExampleViewHolder
import android.widget.TextView
import com.mindorks.example.parallax.R
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import java.util.ArrayList

 class ExampleAdapter constructor(exampleList: MutableList<ExampleItem>) :
    RecyclerView.Adapter<ExampleViewHolder>(), Filterable {
    private val exampleList: List<ExampleItem>
    private lateinit var exampleListFull: List<ExampleItem>

    inner class ExampleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imageView: ImageView
        var textView1: TextView
        var textView2: TextView

        init {
            imageView = itemView.findViewById(R.id.image_view)
            textView1 = itemView.findViewById(R.id.text_view1)
            textView2 = itemView.findViewById(R.id.text_view2)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExampleViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(
            R.layout.example_item,
            parent, false
        )
        return ExampleViewHolder(v)
    }

    override fun onBindViewHolder(holder: ExampleViewHolder, position: Int) {
        val currentItem = exampleList[position]
        holder.imageView.setImageResource(currentItem.imageResource)
        holder.textView1.text = currentItem.text1
        holder.textView2.text = currentItem.text2
    }

    override fun getItemCount(): Int {
        return exampleList.size
    }

    override fun getFilter(): Filter {
        return exampleFilter
    }

    private val exampleFilter: Filter = object : Filter() {
        override fun performFiltering(constraint: CharSequence): FilterResults {
            val filteredList: MutableList<ExampleItem> = ArrayList()
            if (constraint.isEmpty()) {
                filteredList.addAll(exampleListFull)
            } else {
                val filterPattern = constraint.toString().toLowerCase().trim { it <= ' ' }
                for (item in exampleListFull) {
                    if (item.text1.toLowerCase().contains(filterPattern)) {
                        filteredList.add(item)
                    }
                }
            }
            val results = FilterResults()
            results.values = filteredList
            return results
        }

        override fun publishResults(constraint: CharSequence, results: FilterResults) {
            exampleList.clear()
            exampleList.addAll(results.values as List<ExampleItem>)
            notifyDataSetChanged()
        }
    }

    init {
        this.exampleList = exampleList
        exampleListFull = ArrayList(exampleList)
    }
}