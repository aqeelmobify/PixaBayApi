package com.example.androidtask.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DefaultItemAnimator
import com.example.androidtask.adapters.PostImagesAdapter
import com.example.androidtask.databinding.ActivityMainHomeBinding
import com.example.androidtask.datamodels.Hit
import com.example.androidtask.listeners.PostImageItemClick
import com.example.androidtask.utils.setOnSingleClickListener
import com.example.androidtask.viewmodels.MainActivityViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainHomeActivity : AppCompatActivity(),PostImageItemClick {
    private val binding by lazy { ActivityMainHomeBinding.inflate(layoutInflater) }
    private val viewModel by viewModels<MainActivityViewModel>()
    var postImagesAdapter: PostImagesAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.apply {
            initRecyclerView()
            setListeners()
        }
        lifecycleScope.launch(Dispatchers.IO){
            viewModel.getPixaBayImagesData()
        }
        setDataObservers()

    }

    private fun ActivityMainHomeBinding.setListeners(){
        btnCheckUniqueWord.setOnSingleClickListener {
            startActivity(Intent(this@MainHomeActivity,UniqueWordsCheckerActivity::class.java))
        }
    }
    private fun ActivityMainHomeBinding.initRecyclerView() {
        postImagesAdapter = PostImagesAdapter(this@MainHomeActivity,this@MainHomeActivity)
        rvHostImages.adapter = postImagesAdapter
        rvHostImages.itemAnimator = DefaultItemAnimator()
        rvHostImages.isNestedScrollingEnabled = true
    }

    private fun setDataObservers() {
        viewModel.pixBayImages?.observe(this) {
            Log.e("MainActivity", it.hits.size.toString())
            postImagesAdapter?.updateImagesData(it.hits as ArrayList<Hit>)
        }
        viewModel.requestState.observe(this) {
            Log.e("MainActivity", it.name)
        }
    }

    override fun onPostImageClick(position: Int?, postHit: Hit) {
        startActivity(Intent(this,PostImageDetailActivity::class.java).putExtra(POST_HIT_IMAGE_ITEM,postHit))
    }
}