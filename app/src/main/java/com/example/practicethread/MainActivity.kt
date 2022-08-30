package com.example.practicethread

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import com.example.practicethread.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var pictureChangeArraylist = ArrayList<Int>()

        pictureChangeArraylist.add(R.drawable.tiger)
        pictureChangeArraylist.add(R.drawable.cat)
        pictureChangeArraylist.add(R.drawable.dog)
        pictureChangeArraylist.add(R.drawable.rabbbit)

        //메인 쓰레드를 제외한 쓰레드에서는 view를 다룰 수 없음 -> handler사용
        var handler = Handler(Looper.getMainLooper())

        Thread() { // 우리가 생성한 쓰레드
            for (i in pictureChangeArraylist) //foreach 문
            {

                Thread.sleep(1000) // 1초단위로 화면이 멈췄다가 다음 화면으로 넘어감

                handler.post{ // 핸들러를 사용하면 원래 쓰레드가 아닌 다른 쓰레드에서도 뷰를 다룰 수 있
                    binding.imgMainPicture.setImageResource(i)
                }

            }

        }.start()
    }
}