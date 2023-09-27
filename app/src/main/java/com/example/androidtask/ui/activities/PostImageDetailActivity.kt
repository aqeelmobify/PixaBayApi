package com.example.androidtask.ui.activities

import android.os.Bundle
import android.widget.ImageView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.androidtask.databinding.ActivityPostImageDetailBinding
import com.example.androidtask.viewmodels.PostImageDetailViewModel
import com.igreenwood.loupe.Loupe
import dagger.hilt.android.AndroidEntryPoint

const val POST_HIT_IMAGE_ITEM="post_hit_image_item"
@AndroidEntryPoint
class PostImageDetailActivity : AppCompatActivity() {
    private val binding by lazy { ActivityPostImageDetailBinding.inflate(layoutInflater) }
    private val viewModel by viewModels<PostImageDetailViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        viewModel.selectedImagePost=intent?.getParcelableExtra(POST_HIT_IMAGE_ITEM)
        binding.apply {
            viewModel.selectedImagePost?.let {
                Glide.with(this@PostImageDetailActivity).load(it.largeImageURL).into(imgPostDetail)
                loadZoomAbleImage()
            }
        }
    }

    private fun ActivityPostImageDetailBinding.loadZoomAbleImage(){

        Loupe.create(imgPostDetail, mainContainer) { // imageView is your ImageView
            onViewTranslateListener = object : Loupe.OnViewTranslateListener {

                override fun onStart(view: ImageView) {
                    // called when the view starts moving
                }

                override fun onViewTranslate(view: ImageView, amount: Float) {
                    // called whenever the view position changed
                }

                override fun onRestore(view: ImageView) {
                    // called when the view drag gesture ended
                }

                override fun onDismiss(view: ImageView) {
                    // called when the view drag gesture ended
                    finish()
                }
            }
        }
    }
}