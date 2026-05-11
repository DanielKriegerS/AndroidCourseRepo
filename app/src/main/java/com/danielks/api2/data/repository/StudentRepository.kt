package com.danielks.api2.data.repository

import com.danielks.api2.data.model.Student
import com.danielks.api2.data.remote.RetrofitClient

class StudentRepository {

    val api = RetrofitClient.api

    suspend fun getStudents(): List<Student> {
        return api.getStudents()
    }

    suspend fun addStudent(student: Student): Student {
        return api.addStudent(student)
    }

    suspend fun deleteStudent(id: Int) {
        api.deleteStudent(id)
    }

}