package com.example.myposts

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

class CommentAdapter(var commentlist: List<Comment>,var context: Context):RecyclerView.Adapter<CommentViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        var Itemview=LayoutInflater .from(parent.context).inflate(R.layout.comment_list_items,parent ,false)
        return CommentViewHolder(Itemview)
    }

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        var currentcomment= commentlist.get(position)
        holder.tvname.text=currentcomment.name
        holder.tvemail.text=currentcomment.email
        holder.tvbody3.text=currentcomment.body

    }

    override fun getItemCount(): Int {
        return commentlist.size
    }


}
class CommentViewHolder(Itemview:View):RecyclerView.ViewHolder(Itemview){
    var tvname=Itemview.findViewById<TextView>(R.id.tvname)
    var tvemail=Itemview.findViewById<TextView>(R.id.tvemail)
    var tvbody3=Itemview.findViewById<TextView>(R.id.tvbody3)
    var cvcomment=Itemview.findViewById<CardView>(R.id.cvcomment)
}