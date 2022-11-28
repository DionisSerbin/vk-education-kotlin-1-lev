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
import com.example.homework_2.model.NETWORK_PAGE_SIZE
import com.example.homework_2.model_processing.GifPagerAdapter
import com.example.homework_2.network.IAccessor

import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainFragment : Fragment(R.layout.fragment_main) {

    private val viewModel by viewModels<MainViewModel>(
        ownerProducer = { this },
        factoryProducer = { MyViewModelFactory() })

    private val gifPagerAdapter = GifPagerAdapter()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<RecyclerView>(R.id.recyclerview).apply {
            layoutManager = StaggeredGridLayoutManager(COLUMNS, LinearLayoutManager.VERTICAL)
            adapter = gifPagerAdapter
        }

        val updateButton = view.findViewById<Button>(R.id.updateButton)
        updateButton.isVisible = false

        val progressDialog = view.findViewById<ProgressBar>(R.id.progressDialog)

        gifPagerAdapter.addLoadStateListener { loadState ->
            // show empty list
            if ((loadState.refresh is LoadState.Loading)
                || (loadState.append is LoadState.Loading))

                progressDialog.isVisible = true

            else {

                lifecycleScope.launch{
                    delay(DELAY_TIME)
                    progressDialog.isVisible = false
                }
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
            gifPagerAdapter.retry()
        }


        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.getGifsList().observe(viewLifecycleOwner) {
                it?.let {
                    gifPagerAdapter.submitData(lifecycle, it)
                }
            }
        }
    }

    companion object {
        fun newInstance() = MainFragment()
        const val DELAY_TIME: Long = 5000
        const val COLUMNS = 2
    }
}

