package com.example.quotegeneratorapp
import androidx.recyclerview.widget.ListAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

class FavoriteQuotesAdapter(
    private val onDeleteClicked: (FavoriteQuote) -> Unit
) : ListAdapter<FavoriteQuote, FavoriteQuotesAdapter.QuoteViewHolder>(QuoteDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuoteViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_favorite_quote, parent, false)
        return QuoteViewHolder(view)
    }

    override fun onBindViewHolder(holder: QuoteViewHolder, position: Int) {
        val quote = getItem(position)
        holder.bind(quote)
    }

    inner class QuoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val quoteTextView: TextView = itemView.findViewById(R.id.quoteTextView)
        private val authorTextView: TextView = itemView.findViewById(R.id.authorTextView)

        fun bind(quote: FavoriteQuote) {
            quoteTextView.text = quote.text
            authorTextView.text = quote.author
            itemView.setOnLongClickListener {
                onDeleteClicked(quote)
                true
            }
        }
    }

    class QuoteDiffCallback : DiffUtil.ItemCallback<FavoriteQuote>() {
        override fun areItemsTheSame(oldItem: FavoriteQuote, newItem: FavoriteQuote): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: FavoriteQuote, newItem: FavoriteQuote): Boolean {
            return oldItem == newItem
        }
    }
}
