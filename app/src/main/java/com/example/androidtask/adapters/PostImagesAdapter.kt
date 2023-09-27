package com.example.androidtask.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.androidtask.databinding.ContentPixabayPostImageItemBinding
import com.example.androidtask.datamodels.Hit
import com.example.androidtask.listeners.PostImageItemClick
import com.example.androidtask.utils.setOnSingleClickListener

class PostImagesAdapter(private var context: FragmentActivity, private var onPostImageItemClick: PostImageItemClick?) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var pixaBayImagesData: ArrayList<Hit> = ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val rootView =
            ContentPixabayPostImageItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return PostImageViewHolder(rootView)
    }

    override fun getItemCount(): Int {
        return pixaBayImagesData.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(position<0 || position>pixaBayImagesData.size) return
        val item =pixaBayImagesData.get(position)
        if (holder is PostImageViewHolder) {
            holder.view.apply {
                Glide.with(holder.itemView).load(item.largeImageURL).into(imgHostLargeImage)
                Glide.with(holder.itemView).load(item.userImageURL).into(imgUserProfile)
                txtUserName.text=item.user
                txtLikeCount.text=item.likes.toString()
                txtPostTags.text="# ${item.tags}"
                holder.itemView.setOnSingleClickListener {
                    onPostImageItemClick?.onPostImageClick(position,item)
                }
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return super.getItemViewType(position)
    }

    private inner class PostImageViewHolder(val view: ContentPixabayPostImageItemBinding) :
        RecyclerView.ViewHolder(view.root) {

    }
    fun updateImagesData(updatedPostImagesList:ArrayList<Hit>){
        pixaBayImagesData.clear()
        pixaBayImagesData.addAll(updatedPostImagesList)
        notifyDataSetChanged()
    }
}