package com.xridwan.news.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.xridwan.news.databinding.HeadlinesItemBinding
import com.xridwan.news.model.Article

class NewsAdapter(
    private val articleList: MutableList<Article>,
    val onItemClickCallBack: OnItemClickCallBack
) :
    RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val binding =
            HeadlinesItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NewsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bind(articleList[position])
    }

    override fun getItemCount(): Int = articleList.size

    inner class NewsViewHolder(private val binding: HeadlinesItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(article: Article) {
            with(binding) {
                tvTitle.text = article.title
                tvDate.text = article.publishedAt
                Picasso.get().load(article.urlToImage).into(imgNews)

                itemView.setOnClickListener {
                    onItemClickCallBack.onItemClicked(article)
                }
            }
        }
    }

    interface OnItemClickCallBack {
        fun onItemClicked(data: Article)
    }
}