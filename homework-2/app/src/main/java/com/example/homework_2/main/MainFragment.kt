package com.example.homework_2.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.homework_2.R
import com.example.homework_2.gif_proccessing.GifAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private val viewModel by viewModels<MainViewModel>()

    private val gifAdapter = GifAdapter()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<RecyclerView>(R.id.recycler).apply {
            layoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
            adapter = gifAdapter
        }
        val stub = view.findViewById<ProgressBar>(R.id.stub)

        viewLifecycleOwner.lifecycleScope.launch {
            stub.isVisible = true
//            stub.text = "Pending"
            stub.setOnClickListener(null)

            try {
                val list = withContext(Dispatchers.IO) {viewModel.getGiphy()}
                gifAdapter.submitList(list)

                stub.isVisible = false
            } catch (error: Throwable){
                stub.isVisible = true
//                stub.text = "ERROR: ${error.message}"
                error.printStackTrace()
                stub.setOnClickListener {

                }
            }
        }
    }

}