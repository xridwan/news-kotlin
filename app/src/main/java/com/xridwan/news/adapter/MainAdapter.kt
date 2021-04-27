package com.xridwan.news.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.xridwan.news.databinding.SourceListItemBinding
import com.xridwan.news.model.SourceList
import java.util.*
import kotlin.collections.ArrayList

class MainAdapter(
    private val sourceList: MutableList<SourceList>,
    var itemClickCallBack: OnItemClickCallBack
) :
    RecyclerView.Adapter<MainAdapter.MainViewHolder>(), Filterable {

    var filterList = ArrayList<SourceList>()

    init {
        filterList = sourceList as ArrayList<SourceList>
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val binding =
            SourceListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MainViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bind(filterList[position])
    }

    override fun getItemCount(): Int = filterList.size

    inner class MainViewHolder(private val binding: SourceListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(sourceList: SourceList) {
            with(binding) {
                tvName.text = sourceList.name
                tvDesc.text = sourceList.description
                tvCategory.text = sourceList.category
                tvCountry.text = sourceList.country
                tvLanguage.text = sourceList.language

                itemView.setOnClickListener {
                    itemClickCallBack.onItemClicked(sourceList)
                }
            }
        }
    }

    interface OnItemClickCallBack {
        fun onItemClicked(data: SourceList)
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val search = constraint.toString()
                filterList = if (search.isEmpty()) {
                    sourceList as ArrayList<SourceList>
                } else {
                    val results = ArrayList<SourceList>()
                    for (row in sourceList) {
                        if (row.name?.toLowerCase(Locale.getDefault())
                                ?.contains(
                                    constraint.toString().toLowerCase(Locale.getDefault())
                                ) == true
                        ) {
                            results.add(row)
                        }
                    }
                    results
                }
                val filterResult = FilterResults()
                filterResult.values = filterList
                return filterResult
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                filterList = results?.values as ArrayList<SourceList>
                notifyDataSetChanged()
            }
        }
    }
}