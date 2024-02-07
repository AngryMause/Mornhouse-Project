package com.example.mornhouseproject.ui.fragment.mainscreens

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.mornhouse.test.project.databinding.ItemAdapterBinding
import com.example.mornhouseproject.model.NumberFactModel

class MainScreenAdapter(private val myListener: ((id: Long) -> Unit)) :
    ListAdapter<NumberFactModel, MainScreenAdapter.MyHolder>(NumberFactModel.ResponseDifUtil) {
    private var mRecyclerView: RecyclerView? = null

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        mRecyclerView = recyclerView
    }

    fun scrollToPosition(position: Int) {
        mRecyclerView?.smoothScrollToPosition(position)
    }

    inner class MyHolder(private val binding: ItemAdapterBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(numberFact: NumberFactModel) {
            binding.factTv.text = numberFact.fact
            itemView.setOnClickListener {
                myListener.invoke(numberFact.id)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemAdapterBinding.inflate(inflater, parent, false)
        return MyHolder(binding)
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        val numberFactList = getItem(position)
        holder.bind(numberFactList)
    }
}






