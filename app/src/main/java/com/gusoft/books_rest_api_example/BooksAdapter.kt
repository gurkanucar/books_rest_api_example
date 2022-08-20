package com.gusoft.books_rest_api_example

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gusoft.books_rest_api_example.databinding.BookItemLayoutBinding
import com.gusoft.books_rest_api_example.model.Book
import com.squareup.picasso.Picasso

class BooksAdapter(private var data: ArrayList<Book>) :
    RecyclerView.Adapter<BooksAdapter.ViewHolder>() {

    var onItemClick: (Book) -> Unit = {}

    class ViewHolder(val binding: BookItemLayoutBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = BookItemLayoutBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item: Book = data[position]

        holder.binding.apply {
            bookName.text = item.name
            Picasso.get().load(item.image_url).into(bookImage)
            card.setOnClickListener { onItemClick(item) }
        }

    }

    override fun getItemCount(): Int {
        return data.size
    }


}