package com.example.redditv2.app.sample.presenter.ui

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.redditv2.R
import com.example.redditv2.app.sample.domain.ChildrenData
import com.example.redditv2.app.sample.domain.News
import com.example.redditv2.app.sample.presenter.RedditActivity
import com.squareup.picasso.Picasso

class RecyclerAdapter(private val context: Context, private val list: List<ChildrenData>) :
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
        holder.contentPost.text = data.selftext
        holder.urlInfo.text = data.url
        Picasso.get().load(data.thumbnail).into(holder.thumbnail)

        holder.click.setOnClickListener {
            val openCard = Intent(context, RedditActivity::class.java)
            openCard.putExtra("title", holder.title.text as String)
            openCard.putExtra("content", holder.contentPost.text as String)
            openCard.putExtra("url", holder.urlInfo.text as String)
            context.startActivity(openCard)
        }

    }

    class ViewHolder(item: View) : RecyclerView.ViewHolder(item) {
        val title: TextView = itemView.findViewById(R.id.lblTitle)
        val author: TextView = itemView.findViewById(R.id.lblAuthor)
        val thumbnail: ImageView = itemView.findViewById(R.id.imgCard)
        val contentPost: TextView = itemView.findViewById(R.id.lblSelf)
        val urlInfo: TextView = itemView.findViewById(R.id.txtUrl)
        val click: CardView = itemView.findViewById(R.id.cardView)
    }
}
