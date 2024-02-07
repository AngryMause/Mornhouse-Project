package com.example.mornhouseproject.model

import androidx.recyclerview.widget.DiffUtil

data class NumberFactModel(
    var id: Long,
    var number: String,
    var fact: String,
) {
    object ResponseDifUtil : DiffUtil.ItemCallback<NumberFactModel>() {
        override fun areItemsTheSame(oldItem: NumberFactModel, newItem: NumberFactModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: NumberFactModel,
            newItem: NumberFactModel,
        ): Boolean {
            return oldItem.id == newItem.id
        }
    }

}
