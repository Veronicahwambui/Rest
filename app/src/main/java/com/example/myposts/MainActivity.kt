package com.example.myposts

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var  rvposts:RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getPosts()
    }
    fun getPosts(){
        rvposts=findViewById(R.id.rvposts)
        var retrofit=ApiClient.buildApiClient(ApiInterface::class.java)
        var request=retrofit.getPosts()
        request.enqueue(object: Callback<List<Post>>{
            override fun onResponse(call: Call<List<Post>>, response:
            Response<List<Post>>) {
                if (response.isSuccessful) {
                    var posts = response.body()
                    var postsAdapter=PostsAdapter(posts!!,baseContext)
                    rvposts.layoutManager=LinearLayoutManager(baseContext)
                    rvposts.adapter=postsAdapter

                }
            }
            override fun onFailure(call: Call<List<Post>>, t: Throwable){
                Toast.makeText(baseContext,t.message,Toast.LENGTH_LONG).show()
                }
            })
        }
    }

