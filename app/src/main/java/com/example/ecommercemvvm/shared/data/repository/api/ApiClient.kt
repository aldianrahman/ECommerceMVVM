package com.example.ecommercemvvm.shared.data.repository.api

import com.google.gson.annotations.SerializedName
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient {



    companion object {

        private const val dataLink = "https://ap-southeast-1.aws.data.mongodb-api.com/app/data-oukpf/endpoint/data/v1/action/"

        private const val tutorLink = "https://us-central1-android-course-api.cloudfunctions.net/"

        fun getService(): ProductService {
            return Retrofit.Builder()
                .baseUrl(tutorLink)
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(ProductService::class.java)
        }
    }
}

data class RawRequest(
    @SerializedName("dataSource") val dataSource: String,
    @SerializedName("database") val database: String,
    @SerializedName("collection") val collection: String
)