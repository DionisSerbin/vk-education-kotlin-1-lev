package com.example.homework_2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import com.example.homework_2.ui.MainFragment


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.activity, MainFragment.newInstance())
                .commitNow()
        }
//        val retrofitService = IAccessor.create()
//        val gifPagerConfig = GifPagerConfig(retrofitService)
//        recyclerview.adapter = adapter
//
//        viewModel = ViewModelProvider(
//            this,
//            MyViewModelFactory(gifPagerConfig)
//        ).get(MainViewModel::class.java)
//
//        viewModel.errorMessage.observe(this) {
//            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
//        }
//
//        adapter.addLoadStateListener { loadState ->
//            // show empty list
//            if (loadState.refresh is LoadState.Loading ||
//                loadState.append is LoadState.Loading)
//                progressDialog.isVisible = true
//            else {
//                progressDialog.isVisible = false
//                // If we have an error, show a toast
//                val errorState = when {
//                    loadState.append is LoadState.Error -> loadState.append as LoadState.Error
//                    loadState.prepend is LoadState.Error ->  loadState.prepend as LoadState.Error
//                    loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
//                    else -> null
//                }
//                errorState?.let {
//                    Toast.makeText(this, it.error.toString(), Toast.LENGTH_LONG).show()
//                }
//
//            }
//        }
//
//
//        lifecycleScope.launch {
//            viewModel.getGifsList().observe(this@MainActivity) {
//                it?.let {
//                    adapter.submitData(lifecycle, it)
//                }
//            }
//        }


    }

}