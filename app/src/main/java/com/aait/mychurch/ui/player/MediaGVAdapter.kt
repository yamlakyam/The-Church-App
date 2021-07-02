package com.aait.mychurch.ui.player

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import java.util.*

class MediaGVAdapter(context: Context, mediaCardList: ArrayList<MediaData?>?) :
    ArrayAdapter<MediaData?>(context, 0, mediaCardList!!) {
    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var listedView = convertView

        listedView =
            LayoutInflater.from(context)
                .inflate(com.aait.mychurch.R.layout.media_element, parent, false)

//var mediaTitle=findView
        return listedView;
    }
}
