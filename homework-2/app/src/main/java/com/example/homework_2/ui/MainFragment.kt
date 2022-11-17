package com.example.homework_2.ui

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.homework_2.R
import com.example.homework_2.model.GifPagerConfig
import com.example.homework_2.model_processing.GifPagerAdapter
import com.example.homework_2.network.IAccessor

import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.coroutines.launch

class MainFragment: Fragment() {
    companion object {
        fun newInstance() = MainFragment()
    }

    private val retrofitService = IAccessor.create()
    private val gifPagerConfig = GifPagerConfig(retrofitService)

    private val viewModel by viewModels<MainViewModel>(ownerProducer = { this }, factoryProducer = { MyViewModelFactory(gifPagerConfig) })

    private val gifPagerAdapter = GifPagerAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<RecyclerView>(R.id.recyclerview).apply {
            layoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
            adapter = gifPagerAdapter
        }
//        recyclerview.adapter = gifPagerAdapter

//        val retrofitService = IAccessor.create()
//        val gifPagerConfig = GifPagerConfig(retrofitService)

//        viewModel = ViewModelProvider(
//            this,
//            MyViewModelFactory(gifPagerConfig)
//        ).get(MainViewModel::class.java)

        val updateButton = view.findViewById<Button>(R.id.updateButton)
        updateButton.isVisible = false

        viewModel.errorMessage.observe(viewLifecycleOwner) {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
            updateButton.isVisible = true
        }

        val progressDialog = view.findViewById<ProgressBar>(R.id.progressDialog)

        gifPagerAdapter.addLoadStateListener { loadState ->
            // show empty list
            if (loadState.refresh is LoadState.Loading ||
                loadState.append is LoadState.Loading
            )
                progressDialog.isVisible = true
            else {
                val handler = Handler()
                handler.postDelayed({
                    progressDialog.isVisible = false

                }, 5000)
                // If we have an error, show a toast
                val errorState = when {
                    loadState.append is LoadState.Error -> loadState.append as LoadState.Error
                    loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
                    loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
                    else -> null
                }
                errorState?.let {
                    Toast.makeText(context, it.error.toString(), Toast.LENGTH_LONG).show()
                    updateButton.isVisible = true
                }

            }
        }

        updateButton.setOnClickListener {
            Toast.makeText(context, "updating...", Toast.LENGTH_LONG).show()
            updateButton.isVisible = false
            viewLifecycleOwner.lifecycleScope.launch {
                activity?.let {
                    viewModel.getGifsList().observe(it) {
                        it?.let {
                            gifPagerAdapter.submitData(lifecycle, it)
                        }
                    }
                }
            }
        }


        viewLifecycleOwner.lifecycleScope.launch {
            activity?.let {
                viewModel.getGifsList().observe(it) {
                    it?.let {
                        gifPagerAdapter.submitData(lifecycle, it)
                    }
                }
            }
        }
    }
}

