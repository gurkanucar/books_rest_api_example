package com.gusoft.books_rest_api_example.network

import com.gusoft.books_rest_api_example.model.Book
import retrofit2.Call
import retrofit2.http.GET

interface BookDAO {

    @GET("/books")
    fun getBooks(): Call<List<Book>>

}