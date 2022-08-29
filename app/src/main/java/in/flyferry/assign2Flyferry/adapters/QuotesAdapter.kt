package `in`.flyferry.assign2Flyferry.adapters

import `in`.flyferry.assign2Flyferry.R
import `in`.flyferry.assign2Flyferry.Result
import android.content.Context
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.view.marginEnd
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.NonDisposableHandle.parent

private const val TAG = "Adapter"
class QuotesAdapter (val context : Context, val quoteResult : List<Result>) : RecyclerView.Adapter<QuotesAdapter.ViewHolder>(){

    inner class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val author = itemView.findViewById<TextView>(R.id.author)
        val authorSlug = itemView.findViewById<TextView>(R.id.authorSlug)
        val content = itemView.findViewById<TextView>(R.id.content)
        val linearLayout = itemView.findViewById<LinearLayout>(R.id.tagLL)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.quoteview, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentQuote = quoteResult[position]
        holder.author.text = currentQuote.author
        holder.authorSlug.text = currentQuote.authorSlug
        holder.content.text = currentQuote.content

        currentQuote.tags.forEach { tag ->
            Log.d(TAG, tag)
            val textView = TextView(context)

            textView.layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT,
            )
            val param = textView.layoutParams as  ViewGroup.MarginLayoutParams
            param.setMargins(10,10,10,10)
            textView.layoutParams = param
            textView.maxLines = 1
            textView.text = "#$tag"
            textView.textSize = 16.0F
            textView.setTextColor(Color.parseColor("#FF000000"))

            holder.linearLayout.addView(textView)
        }
    }

    override fun getItemCount(): Int {
        return quoteResult.size
    }
}