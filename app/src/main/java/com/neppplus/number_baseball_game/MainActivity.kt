package com.neppplus.number_baseball_game

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.neppplus.number_baseball_game.adapters.MessageAdapter
import com.neppplus.number_baseball_game.data.MessageData
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val mMessagelist = ArrayList<MessageData>()

    lateinit var mAdapter : MessageAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mMessagelist.add( MessageData("안녕하세요", "CPU") )
        mMessagelist.add( MessageData("반갑습니다", "USER") )

        mAdapter = MessageAdapter(this, R.layout.message_list_item, mMessagelist)
        messageListView.adapter = mAdapter

    }
}