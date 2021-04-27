package com.xridwan.news.view

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.xridwan.news.adapter.MainAdapter
import com.xridwan.news.databinding.ActivityMainBinding
import com.xridwan.news.model.SourceList
import com.xridwan.news.presenter.MainPresenter
import com.xridwan.news.presenter.MainView

class MainActivity : AppCompatActivity(), MainView {
    private lateinit var binding: ActivityMainBinding
    private var adapter: MainAdapter? = null
    private var mainPresenter: MainPresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mainPresenter = MainPresenter(this)
        mainPresenter?.get()

        binding.etSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                if (s.isEmpty()) {
                    binding.imgCancel.visibility = View.GONE
                } else {
                    binding.imgCancel.visibility = View.VISIBLE
                    try {
                        adapter?.filter?.filter(s)
                    } catch (e: Exception) {
                        e.message
                    }
                }
            }

            override fun afterTextChanged(s: Editable?) {

            }

        })

        binding.imgCancel.setOnClickListener {
            binding.etSearch.setText("")
            try {
                adapter?.filter?.filter(binding.etSearch.text.toString())
            } catch (e: Exception) {
                e.localizedMessage
            }
        }

        window.setSoftInputMode(
            WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN
        )
    }

    override fun onSuccess(message: String?, sourceList: MutableList<SourceList>?) {
        binding.rvNews.setHasFixedSize(true)
        binding.rvNews.layoutManager = LinearLayoutManager(this)
        adapter = sourceList?.let {
            MainAdapter(it, object : MainAdapter.OnItemClickCallBack {
                override fun onItemClicked(data: SourceList) {
                    val sourceList = SourceList(
                        data.id,
                        data.name,
                        data.description
                    )
                    val intent = Intent(this@MainActivity, NewsActivity::class.java)
                    intent.putExtra(NewsActivity.EXTRA_ID, sourceList)
                    startActivity(intent)
                }
            })
        }
        binding.rvNews.adapter = adapter

        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun onFailure(message: String?) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun onLoading(state: Boolean) {
        if (state) {
            binding.progressCircular.visibility = View.VISIBLE
        } else {
            binding.progressCircular.visibility = View.GONE
        }
    }
}