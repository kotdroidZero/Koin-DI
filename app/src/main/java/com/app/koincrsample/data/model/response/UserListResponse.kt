package com.app.koincrsample.data.model.response

import com.google.gson.annotations.SerializedName


/**
 * @AUTHOR Pushkar Srivastava
 * @date 12/02/2020
 */
data class UserListResponse(
    @SerializedName("data")
    val user: List<User>,
    @SerializedName("page")
    val page: Int, // 2
    @SerializedName("per_page")
    val perPage: Int, // 6
    @SerializedName("total")
    val total: Int, // 12
    @SerializedName("total_pages")
    val totalPages: Int // 2
) {
    data class User(
        @SerializedName("avatar")
        val avatar: String, // https://s3.amazonaws.com/uifaces/faces/twitter/hebertialmeida/128.jpg
        @SerializedName("email")
        val email: String, // rachel.howell@reqres.in
        @SerializedName("first_name")
        val firstName: String, // Rachel
        @SerializedName("id")
        val id: Int, // 12
        @SerializedName("last_name")
        val lastName: String // Howell
    )
}