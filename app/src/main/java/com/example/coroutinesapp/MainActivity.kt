package com.example.coroutinesapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response
import retrofit2.Callback
import retrofit2.Call

class MainActivity : AppCompatActivity() {

    lateinit var bAdvice : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val responseText = findViewById<View>(R.id.text) as TextView
        bAdvice = findViewById(R.id.bAdvice)

        CoroutineScope(Dispatchers.IO).launch{

            bAdvice.setOnClickListener {

                val apiInterface = APIClient().getClient()?.create(APIInterface::class.java)
                val call: Call<Advice>? = apiInterface!!.doGetListResources()

                call?.enqueue(object : Callback<Advice> {
                    override fun onResponse(
                        call: Call<Advice>,
                        response: Response<Advice>
                    ) {
                        val resource: Advice? = response.body()
                        var temp = resource?.slip?.advice.toString()
                        responseText.text = temp
                    }

                    override fun onFailure(call: Call<Advice>, t: Throwable?) {
                        call.cancel()
                    }
                })
            }

            withContext(Main){

            }
        }

    }

    private suspend fun getResult(){

    }

}