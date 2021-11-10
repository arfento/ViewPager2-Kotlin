package com.ims.viewpager2_kotlin

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class IntroSliderAdapter(private val introSlide: List<IntroSlide>) :
    RecyclerView.Adapter<IntroSliderAdapter.IntroViewHolder>() {


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): IntroSliderAdapter.IntroViewHolder {
        return IntroViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.slider_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: IntroSliderAdapter.IntroViewHolder, position: Int) {
        holder.bind(introSlide[position])
    }

    override fun getItemCount(): Int {
        return introSlide.size
    }

    class IntroViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val textTitle = itemView.findViewById<TextView>(R.id.txtTitle)
        private val textDesc = itemView.findViewById<TextView>(R.id.txtDesc)
        private val imageIcon = itemView.findViewById<ImageView>(R.id.imageSlideIcon)

        fun bind(introSlide: IntroSlide) {
            textTitle.text = introSlide.title
            textDesc.text = introSlide.description
            imageIcon.setImageResource(introSlide.icon)
        }
    }


}