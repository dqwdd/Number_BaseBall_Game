package com.neppplus.number_baseball_game.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.LinearLayout
import android.widget.TextView
import com.neppplus.number_baseball_game.R
import com.neppplus.number_baseball_game.data.MessageData

class MessageAdapter (
    val mContext : Context,
    val resId : Int,
    val mroomList : ArrayList<MessageData>) : ArrayAdapter<MessageData>(mContext, resId, mroomList){

        val mInflater = LayoutInflater.from(mContext)

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        var tempRow = convertView
        if (tempRow==null) {
            tempRow = mInflater.inflate(R.layout.message_list_item, null)
        }
        val row = tempRow!!

        val data = mroomList[position]

        val cpuMessageLayout = row.findViewById<LinearLayout>(R.id.cpuMessageLayout)
        val cpuMessageTxt = row.findViewById<TextView>(R.id.cpuMessageTxt)
        val userMessageLayout = row.findViewById<LinearLayout>(R.id.userMessageLayout)
        val userMessageTxt = row.findViewById<LinearLayout>(R.id.userMessageTxt)

        return row
    }

    }


