package io.rotlabs.flakersampleapp.home.data.remote

import io.rotlabs.flakersampleapp.home.data.remote.dto.UserData
import retrofit2.Response
import retrofit2.http.GET

interface UsersApiService {

    @GET("/api/users?page=2")
    suspend fun getUsers(): Response<UserData>
}
