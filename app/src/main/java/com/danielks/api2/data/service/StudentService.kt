package com.danielks.api2.data.service

import com.danielks.api2.data.model.Student
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface StudentService {
    @GET("students")
    suspend fun getStudents(): List<Student>

    @POST
    suspend fun addStudent(@Body student: Student): Student

    @DELETE("students/{id}")
    suspend fun deleteStudent(@Path("id") id: Int)
}