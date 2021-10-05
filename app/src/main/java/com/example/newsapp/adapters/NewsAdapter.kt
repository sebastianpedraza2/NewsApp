package com.example.newsapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newsapp.data.entities.Article
import com.example.newsapp.databinding.ItemArticlePreviewBinding
import kotlinx.android.synthetic.main.item_article_preview.view.*

class NewsAdapter : RecyclerView.Adapter<NewsAdapter.ArticleViewHolder>() {

    // ViewHolder
    inner class ArticleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    /**
     * Created as an anonymous class instance
     */
    // Callback for calculating the difference between two non-null items in a list
    private val differCallback = object : DiffUtil.ItemCallback<Article>() {
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            /**
             * Im not using the id to compare because that's only set for room, the api object doesn't have it
             */
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
       val binding = ItemArticlePreviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ArticleViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val article = differ.currentList[position]

        holder.itemView.apply {
            Glide.with(this).load(article.urlToImage).into(ivArticleImage)
            tvSource.text = article.source.name
            tvTitle.text = article.description
            tvDescription.text = article.description
            tvPublishedAt.text = article.publishedAt

            setOnClickListener { onItemClickListener?.let {
                it(article)
            } }
        }
    }

    /**
     * onClickListener for navigating to details page
     */
    private var onItemClickListener: ((Article)-> Unit)? = null

    /**
     * I can assign the value of onItemClickListener from the outside
     * Function gets a param of type Article and returns nothing
     */
    fun setOnItemClickListener(listener: (Article) -> Unit){
        onItemClickListener = listener
    }

    override fun getItemCount(): Int = differ.currentList.size
}