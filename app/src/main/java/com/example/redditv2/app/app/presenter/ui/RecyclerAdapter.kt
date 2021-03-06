package com.example.redditv2.app.app.presenter.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.redditv2.R
import com.example.redditv2.app.app.domain.ChildrenData
import com.example.redditv2.app.app.domain.News
import com.squareup.picasso.Picasso

class RecyclerAdapter(private val context: Context, private val list: ArrayList<ChildrenData>) :
    RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.details_view,
                parent,
                false
            )
        )

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data: News = list[position].data
        holder.title.text = data.title
        holder.author.text = data.author
        if(data.thumbnail.isBlank()){
            Picasso.get().load(data.thumbnail).resize(0,0).into(holder.thumbnail)
        }else{
            Picasso.get().load(data.thumbnail).resize(250,180).into(holder.thumbnail)
        }

        holder.click.setOnClickListener {

        }
    }

    class ViewHolder(item: View) : RecyclerView.ViewHolder(item) {
        val title: TextView = itemView.findViewById(R.id.lblTitle)
        val author: TextView = itemView.findViewById(R.id.lblAuthor)
        val thumbnail: ImageView = itemView.findViewById(R.id.imgCard)
        val click: CardView = itemView.findViewById(R.id.cardView)
    }
}
