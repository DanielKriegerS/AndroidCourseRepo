package com.danielks.lib

data class BookUpdateData(
    val name: String? = null,
    val author: String? = null,
    val genre: String? = null,
    val numPags: Int? = null,
    val coverType: String? = null
)