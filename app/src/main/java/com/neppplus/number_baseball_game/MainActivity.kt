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

        okBtn.setOnClickListener {
            val inputNumStr = numberEdt.text.toString()
//                실제로는 String값으로 뽑혀오니까 이름 Str로 짓는거
            val msg = MessageData( inputNumStr, "USER" )
            mMessagelist.add(msg)
//            내용물 변겅이 있으면 습관적으로 mAdapter에 notifyDataSetChanged()하자
            mAdapter.notifyDataSetChanged()


//            numberEdt의 문구를 비워주고 싶다
//            numberEdt.text = ""
//            이렇게 하면 오류남 스트링 값이라
            numberEdt.setText("")

        }

    }
}