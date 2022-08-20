package com.gusoft.books_rest_api_example.network

class ApiUtils {

    companion object {
        private const val BASE_URL = "http://192.168.0.10:8080"

        fun getBooksDAO(): BookDAO {
            return RetrofitClient.getClient(BASE_URL).create(BookDAO::class.java)
        }
    }
}