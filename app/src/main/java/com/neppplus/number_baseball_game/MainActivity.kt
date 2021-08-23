package com.neppplus.number_baseball_game

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.neppplus.number_baseball_game.adapters.MessageAdapter
import com.neppplus.number_baseball_game.data.MessageData
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val mMessagelist = ArrayList<MessageData>()
    lateinit var mAdapter : MessageAdapter
    
//    세자리 문제 숫자를 저장히기 위한 ArrayList
    val mQuestionNumbers = ArrayList<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
//        세자리 랜덤 문제 기능 만들기
        makeQuestionNumbers()


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

//            리스트뷰를 최하단 (맨 마지막 데이터)으로 내리고 싶다==>
            messageListView.smoothScrollToPosition( mMessagelist.size - 1 )

        }

    }
    
    fun makeQuestionNumbers() {
//        고정된 세개 숫자를 임시 문제로 써보자
        mQuestionNumbers.add(4)
        mQuestionNumbers.add(7)
        mQuestionNumbers.add(1)

//        문제가 출제 되었으니 환영 메시지를 채팅창에 띄우자
//        메시지 리스트에, 띄워줄 말들 데이터를 추가하자
        mMessagelist.add( MessageData("어서오세요", "CPU") )
        mMessagelist.add( MessageData("숫자야구 게임입니다", "CPU") )
        mMessagelist.add( MessageData("세자리 숫자를 맞춰주세요", "CPU") )
        
    }
    
}