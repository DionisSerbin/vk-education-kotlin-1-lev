package com.example.homework_2

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_recycler.*
import okhttp3.*
import org.json.JSONObject
import java.io.IOException


class RecyclerFragment : Fragment(R.layout.fragment_recycler) {

    lateinit var  gifList: MutableList<String>

    var page = 1
    var isLoading = false
    val limit = 5

    lateinit var adapter: DigitsRVAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_recycler, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        adapter = DigitsRVAdapter(gifList)
//        digitsRV.adapter = adapter

        digitsRV.layoutManager = LinearLayoutManager(context)

        getPage()

//        if (savedInstanceState != null) {
//            adapter.updateGifLinks(Datasource().getGifTrendingLinks())
//        }

        digitsRV.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {


                val visibleItemCount = (digitsRV.layoutManager as LinearLayoutManager).childCount
                val postVisibleItem = (digitsRV.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()
                val total = adapter.itemCount

                if (!isLoading) {
                    if ((visibleItemCount + postVisibleItem) >= total) {
                        page++
                        getPage()
                    }
                }

                super.onScrolled(recyclerView, dx, dy)
            }
        })

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
    }

    fun getPage() {
        isLoading = true
        progressBar.visibility = View.VISIBLE
        val start = (page - 1) * limit
        val end = (page) * limit

        if (page == 1){
            gifList = Datasource().getGifTrendingLinks()
        } else {
            for (i in start..end) {
                gifList.add(Datasource().getGifRandomLink())
            }
        }
        Handler().postDelayed({
            if(::adapter.isInitialized){
                adapter.notifyDataSetChanged()
            } else {
                adapter = DigitsRVAdapter(gifList)
                digitsRV.adapter = adapter
            }
            isLoading = false
            progressBar.visibility = View.GONE
        }, 5000)
    }
}