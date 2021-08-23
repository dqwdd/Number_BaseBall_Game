package com.neppplus.number_baseball_game

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.neppplus.number_baseball_game.adapters.MessageAdapter
import com.neppplus.number_baseball_game.data.MessageData
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.math.log

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


//            컴퓨터가 ?S ?B인지 판단해서 메시지 추가 (답장)
//            기니까 밑에 따로 펑션 만들어서 가져오자
            checkAnswer( inputNumStr.toInt() )
//            스트링으로 했으니까 toInt로 Int로 받아오자
        }

    }



    fun checkAnswer( inputNum : Int) {
//        사람이 입력한 숫자가 ?S ?B인지 판단하는 함수
//        사람이 입력한 숫자를 => 각 자리별로 나눠서 => 목록에 대입

        val userInputNumArr = ArrayList<Int>()

        userInputNumArr.add( inputNum / 100 ) //100의 자리가 몇이냐 => 456에서 4
        userInputNumArr.add( (inputNum / 10) % 10 ) //10의 자리가 몇이냐 456에서 5
        userInputNumArr.add( inputNum % 10 ) //1의 자리가 몇이냐 456에서 6

        var strikeCount = 0
        var ballCount = 0
        for ( i  in 0..2) {
            for ( j   in 0..2) {
//                내가 입력한 숫자 i번째랑, 컴퓨터가 낸 숫자 j번째가 같은 값인가?
                if ( userInputNumArr[i] == mQuestionNumbers[j] ) {
                    //같은 숫자 하나 찾았다면~
//                    위치도 같은지 물어보자
                    if ( i == j ) {
                        strikeCount++
                    }
                    else {
                        ballCount++
                    }
                }
            }
        } //for문 맨 바깥꺼

//        ?S ?B 인지를 컴퓨터가 말하는걸 처리
        mMessagelist.add( MessageData("${strikeCount}S ${ballCount}B 입니다", "CPU"))
        mAdapter.notifyDataSetChanged() // 알려야 함

        messageListView.smoothScrollToPosition( mMessagelist.size -1 )

//        만약 3S다? -> 축하한다는 메시지 추가 출력 -> 입력 못하게 막자
        if ( strikeCount == 3 ) {
            mMessagelist.add( MessageData("축하합니다! 정답을 맞췄습니다.", "CPU"))
            mAdapter.notifyDataSetChanged() // 알려야 함
            messageListView.smoothScrollToPosition( mMessagelist.size -1 )
            Toast.makeText(this, "게임을 종료합니다", Toast.LENGTH_SHORT).show()
//            입력을 막는다 => numberEdt를 enabled : false
            numberEdt.isEnabled = false
        }

    } //checkAnswer


    fun makeQuestionNumbers() {
//        고정된 세개 숫자를 임시 문제로 써보자
//        mQuestionNumbers.add(4)
//        mQuestionNumbers.add(7)
//        mQuestionNumbers.add(1)

//        위처럼 고정된 3개의 숫자 말고 랜덤한 숫자로 만들자
//        단 1~9만 사용하고 중복된 숫자는 나오면 안된다.
        for ( i in 0..2) {
            while (true) {
//                1~0 사이의 랜덤 정수 추출
                val randomNum = ( Math.random() * 9 + 1 ).toInt()
//                mQuestionNumbers에 이미 들어있는지 중복 검사
                var isDuplOk = true

                for (num in mQuestionNumbers) {
                    if (num == randomNum) {
//                        if 중복된 숫자를 발견했다?
                        isDuplOk = false
                    }
                }

                if (isDuplOk) {
                    mQuestionNumbers.add( randomNum )
//                    무한 반복 탈출
                    break
                }

            }
        }

        for (num in mQuestionNumbers) {
            Log.d("출체된 숫자", num.toString())
        }


//        문제가 출제 되었으니 환영 메시지를 채팅창에 띄우자
//        메시지 리스트에, 띄워줄 말들 데이터를 추가하자
        mMessagelist.add( MessageData("어서오세요", "CPU") )
        mMessagelist.add( MessageData("숫자야구 게임입니다", "CPU") )
        mMessagelist.add( MessageData("세자리 숫자를 맞춰주세요", "CPU") )
        
    }
    
}