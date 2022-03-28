package com.pemwa.weatherdaily.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.pemwa.weatherdaily.R
import kotlinx.android.synthetic.main.item_forecast_weather.view.*

/**
 * Adapter class
 */
class ForecastWeatherAdapter :
    ListAdapter<Triple<String, Int, String>, ForecastWeatherAdapter.ForecastWeatherViewHolder>(DiffCallback) {

    private val items: MutableList<Triple<String, Int, String>>

    init {
        items = ArrayList()
    }

    /**
     * Refreshes the list of data to be used by the adapter
     *
     * @param newData The new data
     */
    @SuppressLint("NotifyDataSetChanged")
    fun swapData(newData: List<Triple<String, Int, String>>) {
        items.clear()
        items.addAll(newData)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForecastWeatherViewHolder {
        return ForecastWeatherViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.item_forecast_weather, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ForecastWeatherViewHolder, position: Int) {
        holder.bind(items[position])
    }

    /**
     * ViewHolder class
     */
    class ForecastWeatherViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: Triple<String, Int, String>) {
            itemView.apply {
                tvForecastDay.text = item.first
                tvForecastValue.text =
                    this.context.getString(R.string.text_weather_value, item.third)
                when(item.second) {
                    1 -> ivForecastIcon.setImageDrawable(
                        ContextCompat.getDrawable(this.context, R.drawable.partlysunny_3x)
                    )
                    2 -> ivForecastIcon.setImageDrawable(
                        ContextCompat.getDrawable(this.context, R.drawable.clear_3x)
                    )
                    3 -> ivForecastIcon.setImageDrawable(
                        ContextCompat.getDrawable(this.context, R.drawable.rain_3x)
                    )
                }
            }
        }
    }

    /**
     * Allows the RecyclerView to determine which items have changed when the list of items
     * has been updated.
     */
    companion object DiffCallback: DiffUtil.ItemCallback<Triple<String, Int, String>>() {
        override fun areItemsTheSame(
            oldItem: Triple<String, Int, String>, newItem: Triple<String, Int, String>
        ): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(
            oldItem: Triple<String, Int, String>, newItem: Triple<String, Int, String>
        ): Boolean {
            return oldItem == newItem
        }
    }
}
