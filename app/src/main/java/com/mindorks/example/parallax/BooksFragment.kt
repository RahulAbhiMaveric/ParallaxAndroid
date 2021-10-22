package com.mindorks.example.parallax

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mindorks.example.parallax.adapter.ExampleAdapter
import com.mindorks.example.parallax.model.ExampleItem

class BooksFragment : Fragment() {
    //private lateinit var recyclerAdapter:BooksRecyclerAdapter
    private lateinit var adapter: ExampleAdapter
    private val exampleList=arrayListOf<ExampleItem>()
    lateinit var recyclerAdapter:ExampleAdapter
    fun newInstance(): BooksFragment {
        return BooksFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view : View? = inflater.inflate(R.layout.books_fragment, container, false)
        val rvBooks : RecyclerView = view!!.findViewById(R.id.rvBooksList)
        fillExampleList()
        rvBooks.layoutManager = LinearLayoutManager(activity);
        //recyclerAdapter = BooksRecyclerAdapter(Util().getBooks())
        recyclerAdapter = ExampleAdapter(exampleList)
        rvBooks.adapter = recyclerAdapter
        return view
    }
    private fun fillExampleList() {
        //exampleList = arrayListOf<ExampleItem>()
        exampleList.add(ExampleItem(R.drawable.ic_audio, "One", "Ten"))
        exampleList.add(ExampleItem(R.drawable.ic_launcher_foreground, "Two", "Eleven"))
        exampleList.add(ExampleItem(R.drawable.ic_sun, "Three", "Twelve"))
        exampleList.add(ExampleItem(R.drawable.ic_launcher_foreground, "Four", "Thirteen"))
        exampleList.add(ExampleItem(R.drawable.ic_audio, "Five", "Fourteen"))
        exampleList.add(ExampleItem(R.drawable.ic_sun, "Six", "Fifteen"))
        exampleList.add(ExampleItem(R.drawable.ic_launcher_foreground, "Seven", "Sixteen"))
        exampleList.add(ExampleItem(R.drawable.ic_audio, "Eight", "Seventeen"))
        exampleList.add(ExampleItem(R.drawable.ic_sun, "Nine", "Eighteen"))
    }
}