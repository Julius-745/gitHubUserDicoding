package com.example.gituser_submission2.View

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.gituser_submission2.Model.Gituser
import com.example.gituser_submission2.databinding.RowItemGhuserBinding

class GitUserAdapter () :
    RecyclerView.Adapter<GitUserAdapter.ListViewHolder>() {

    private val listUser: ArrayList<Gituser> = arrayListOf()

    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    inner class ListViewHolder(private val binding: RowItemGhuserBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(user: Gituser) {
            with(binding) {
                tvUsername.text = user.username
                tvUrl.text = "https://github.com/${user.username}"
                Glide.with(itemView.context)
                    .load(user.avatar)
                    .apply(RequestOptions().override(55, 55))
                    .into(ivAvatar)
                itemView.setOnClickListener { onItemClickCallback?.onItemClicked(user) }

            }
        }


    }


    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ListViewHolder {
        val binding =
            RowItemGhuserBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(listUser[position])
    }

    override fun getItemCount(): Int = listUser.size


    interface OnItemClickCallback {
        fun onItemClicked(data: Gituser)
    }

    fun setData(items: ArrayList<Gituser>) {
        listUser.clear()
        listUser.addAll(items)
        notifyDataSetChanged()
    }
}
