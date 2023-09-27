package com.example.androidtask.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.androidtask.R
import com.example.androidtask.databinding.ActivityMainHomeBinding
import com.example.androidtask.databinding.ActivityUniqueWordsCheckerActivityBinding
import com.example.androidtask.utils.setOnSingleClickListener
import com.example.androidtask.utils.showToast
import com.example.androidtask.viewmodels.MainActivityViewModel
import com.example.androidtask.viewmodels.UniqueWordsCheckerViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UniqueWordsCheckerActivity : AppCompatActivity() {
    private val binding by lazy { ActivityUniqueWordsCheckerActivityBinding.inflate(layoutInflater) }
    private val viewModel by viewModels<UniqueWordsCheckerViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.apply {
             include2.txtToolbarTitle.text=getString(R.string.count_unique_words)
            setListeners()
        }
    }

    private fun ActivityUniqueWordsCheckerActivityBinding.setListeners(){
        btnCheckResult.setOnSingleClickListener {
            edtxWordsSample.text?.toString()?.let {
                if(it.isNotEmpty()){
                    checkUniqueWordsAndShowResult(it)
                }else showToast(getString(R.string.enter_some_words_to_check))
            }?: kotlin.run {
                showToast(getString(R.string.enter_some_words_to_check))
            }
        }
    }

    private fun checkUniqueWordsAndShowResult(input:String)
    {
        lifecycleScope.launch(Dispatchers.IO){
            val wordCounts= viewModel.countUniqueWords(input)
            withContext(Dispatchers.Main){
                binding.apply {
                    txtUniqueWordsResult.text=wordCounts.toString()
                }
            }
        }
    }


}