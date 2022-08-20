package com.gusoft.books_rest_api_example

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.gusoft.books_rest_api_example.databinding.FragmentHomeBinding
import com.gusoft.books_rest_api_example.model.Book
import com.gusoft.books_rest_api_example.network.ApiUtils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    var bookList: ArrayList<Book> = arrayListOf()

    private lateinit var adapter: BooksAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getBooks()

    }

    private fun getBooks() {
        ApiUtils.getBooksDAO().getBooks().enqueue(
            object : Callback<List<Book>> {
                override fun onResponse(
                    call: Call<List<Book>>,
                    response: Response<List<Book>>
                ) {
                    response.body()?.let {
                        bookList = it as ArrayList<Book>
                    }


                    adapter = BooksAdapter(bookList)

                    binding.apply {
                        booksRcView.layoutManager =
                            StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
                        booksRcView.adapter = adapter
                        booksRcView.setHasFixedSize(true)
                    }
                    adapter.onItemClick = ::onItemClick
                }

                override fun onFailure(call: Call<List<Book>>, t: Throwable) {
                    Log.e("ERROR", "onFailure: " + t.toString())
                }

            }
        )
    }

    fun onItemClick(book: Book) {
        Toast.makeText(
            requireContext(),
            "Item Clicked -> $book", Toast.LENGTH_SHORT
        )
            .show()
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}