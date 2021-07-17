package com.example.myposts

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class commentsActivity : AppCompatActivity() {
    var postId=0
    lateinit var tvtitle2:TextView
    lateinit var tvbody2:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comments)
        postId=intent.getIntExtra("post_Id",0)
        castViews()
        getPost()
        getComment()



    }
    fun castViews(){
        tvtitle2=findViewById(R.id.tvtittle2)
        tvbody2=findViewById(R.id.tvbody2)
    }
    fun getPost() {
        if (postId == 0) {
            Toast.makeText(baseContext, "post not found", Toast.LENGTH_LONG).show()
            //toast
            //create an intent
            finish()
        }
        var apiclient = ApiClient.buildApiClient(ApiInterface::class.java)
        var request = apiclient.getPost(postId)

        request.enqueue(object : retrofit2.Callback<Post?> {
            override fun onResponse(call: Call<Post?>, response: Response<Post?>) {
                if(response.isSuccessful){
                  var post=response.body()
                  tvtitle2.text=post?.title
                  tvbody2.text=post?.body

                }

            }

            override fun onFailure(call: Call<Post?>, t: Throwable) {
                Toast.makeText(baseContext ,t.message ,Toast.LENGTH_LONG).show()

            }
        })

    }
    fun getComment(){
       var  rvcomments=findViewById<RecyclerView>(R.id.rvcomments)
        var retrof=ApiClient.buildApiClient(ApiInterface::class.java)
        var request=retrof.getComment(postId)
        request.enqueue(object : Callback<List<Comment>>{
            override fun onResponse(call: Call<List<Comment>>, response: Response<List<Comment>>) {
                if(response.isSuccessful){
                    var comments=response.body()!!
                    var commentAdapter=CommentAdapter(comments,baseContext)
                    rvcomments.layoutManager= LinearLayoutManager(baseContext)
                    rvcomments.adapter=commentAdapter



                }

            }

            override fun onFailure(call: Call<List<Comment>>, t: Throwable) {
                Toast.makeText(baseContext,t.message,Toast.LENGTH_LONG).show()


            }


        })



        }
}




