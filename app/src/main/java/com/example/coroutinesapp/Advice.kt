package com.example.coroutinesapp

import com.google.gson.annotations.SerializedName

class Advice {

    @SerializedName("slip")
    var slip:advice?  = null

    class advice{
        @SerializedName("id")
        var id: Int? = null

        @SerializedName("advice")
        var advice: String? = null
    }
}