package com.example.luckdraw

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.WindowManager
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {
    //保存抽奖名单
    var names= listOf<String>("王重阳","黄药师","欧阳峰","一灯大师","洪七公")
    //定时器 每隔一段时间切换一次名字
    lateinit var timer: Timer
    //记录当前索引
    var index=0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        init()

    }

    private fun init(){
        //设置默认显示第一个人
        mNameTextView.text=names[0]

        //给按钮添加点击事件
        mStartButton.setOnClickListener {
            //判断标题是Start还是Stop
            if(mStartButton.text.toString()=="START"){
                mStartButton.text="STOP"
                //创建定时器
                timer= Timer()
                //分配一个定时任务
                timer.schedule(object :TimerTask(){
                    override fun run() {
                        //判断是否越界
                        index=if(index+1>names.size-1) 0 else index+1
                        //取出对应名字
                        mNameTextView.text=names[index]
                        println("$index")
                    }
                },0,100)
            }else{
                mStartButton.text="START"
                timer.cancel()
            }
        }
    }

}
