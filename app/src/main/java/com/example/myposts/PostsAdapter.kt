package com.example.myposts

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.TextView
import androidx.appcompat.view.menu.MenuView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import retrofit2.http.POST

class PostsAdapter(var postlist:List<Post> ,var context:Context):RecyclerView.Adapter<PostsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostsViewHolder {

        var itemView=LayoutInflater.from(parent.context).inflate(R.layout.posts_lists_items ,parent,false)
        return PostsViewHolder(itemView)
    }


    override fun onBindViewHolder(holder: PostsViewHolder, position: Int) {
        var currentPost=postlist.get(position)

        holder.tvtitle .text=currentPost.title
        holder.tvbody.text=currentPost.body
        holder.cvposts.setOnClickListener {
            var intent=Intent(context,commentsActivity::class.java)
            intent.putExtra("post_Id",currentPost.id)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(intent)
        }


    }

     override fun getItemCount(): Int {
       return postlist.size
    }


}





class PostsViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
    var tvtitle=itemView.findViewById<TextView>(R.id.tvtitle)
    var tvbody=itemView.findViewById<TextView>(R.id.tvbody)
    var cvposts=itemView.findViewById<CardView>(R.id.cvposts)



}