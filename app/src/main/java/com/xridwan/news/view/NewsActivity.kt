package com.xridwan.news.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.xridwan.news.model.SourceList
import com.xridwan.news.adapter.NewsAdapter
import com.xridwan.news.databinding.ActivityNewsBinding
import com.xridwan.news.model.Article
import com.xridwan.news.presenter.NewsPresenter
import com.xridwan.news.presenter.NewsView

class NewsActivity : AppCompatActivity(), NewsView {
    private lateinit var binding: ActivityNewsBinding
    private var newsPresenter: NewsPresenter? = null

    companion object {
        const val EXTRA_ID = "extra_id"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sources = intent.getParcelableExtra<SourceList>(EXTRA_ID) as SourceList

        Log.d("TAG", "onCreate: ${sources.id}")

        newsPresenter = NewsPresenter(this)
        newsPresenter?.getNews(sources.id.toString())

        binding.tvName.text = sources.name
        binding.tvDesc.text = sources.description

        binding.imgBack.setOnClickListener {
            onBackPressed()
        }
    }

    override fun onSuccess(message: String?, articleList: MutableList<Article>?) {
        binding.rvHeadlines.setHasFixedSize(true)
        binding.rvHeadlines.layoutManager = LinearLayoutManager(this)
        val adapter = articleList?.let {
            NewsAdapter(it, object : NewsAdapter.OnItemClickCallBack {
                override fun onItemClicked(data: Article) {
                    val intent = Intent(this@NewsActivity, DetailActivity::class.java)
                    intent.putExtra(DetailActivity.EXTRA_URL, data.url)
                    startActivity(intent)
                }
            })
        }
        binding.rvHeadlines.adapter = adapter

        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun onFailure(message: String?) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun onLoading(state: Boolean) {
        if (state) {
            binding.progressNews.visibility = View.VISIBLE
        } else {
            binding.progressNews.visibility = View.GONE
        }
    }
}