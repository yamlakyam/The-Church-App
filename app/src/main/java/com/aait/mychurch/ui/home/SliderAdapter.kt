package com.aait.mychurch.ui.home

import android.R
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.smarteist.autoimageslider.SliderViewAdapter
import com.squareup.picasso.Picasso


private class SliderAdapter(// creating a variable for
    // context and array list.
    private val context: Context, mSliderItems: List<SliderData>) :
    SliderViewAdapter<SliderAdapter.SliderAdapterVH>() {
    private var mSliderItems: List<SliderData> = ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup): SliderAdapterVH {
        // inside the on Create view holder method we are
        // inflating our layout file which we have created.
        val inflate: View =
            LayoutInflater.from(parent.context).inflate(com.aait.mychurch.R.layout.image_slider_item, null)
        return SliderAdapterVH(inflate)
    }

    override fun onBindViewHolder(viewHolder: SliderAdapterVH, position: Int) {
        // inside on bind view holder method we are
        // getting url of image from our modal class
        // and setting that url for image inside our
        // image view using Picasso.
        val sliderItem = mSliderItems[position]
        Picasso.get().load(sliderItem.imageUrl).into(viewHolder.imageView)
    }

    override fun getCount(): Int {
        // returning the size of our array list.
        return mSliderItems.size
    }

    // view holder class for initializing our view holder.
    internal inner class SliderAdapterVH(itemView: View) : ViewHolder(itemView) {
        // variables for our view and image view.
        lateinit var itemView: View
        var imageView: ImageView

        init {
            // initializing our views.
            imageView = itemView.findViewById(com.aait.mychurch.R.id.idIVimage)
        }
    }

    // constructor for our adapter class.
    init {
        this.mSliderItems = mSliderItems
    }
}