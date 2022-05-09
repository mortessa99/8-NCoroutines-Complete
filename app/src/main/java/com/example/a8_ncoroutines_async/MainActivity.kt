package com.example.a8_ncoroutines_async

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import com.example.a8_ncoroutines_async.databinding.ActivityMainBinding
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var job:Job
    private val TAG = "Coroutine Tag"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Async
        /*CoroutineScope(IO).launch {
            val res1 = async {doTask1()}
            val res2 = async {doTask2()}
            Log.i(TAG,res1.await())
            Log.i(TAG,res2.await())

         //نتیجه: بعد از 3 ثانیه res1 و 1 ثانیه بعد از ان res2 چاپ میشود.
        // دقت کنید اینکه res2 تاخیر 4 تانیه اییش از ابتدای اجرای برنامه لحاظ میشود نه از پایان اجرای res1 یا همان doTask()1.زیرا داریم از async استفاده می کنیم
        }*/

        //Launch
        /*CoroutineScope(IO).launch {
            val res1 = doTask1()
            val res2 = doTask2()
            Log.i(TAG,res1)
            Log.i(TAG,res2)

            //نتیجه:بعد از 7 ثانیه هر دو باهم چاپ میشوند
        }*/

        //RunBlocking
       /* runBlocking {
            delay(5000)
            //نتیجه:اپ بعد از 5 ثانیه یوای ان نشان داده میشود
        }*/


        //Custom Thread
       /* CoroutineScope(newSingleThreadContext("Morteza")).launch {
            Log.i(TAG,Thread.currentThread().name)
        }*/

        //Show Ui and use it after done work by background Thread
        /*CoroutineScope(Dispatchers.IO).launch {
            doTask1()
            withContext(Dispatchers.Main){
                binding.textview.text = "Done"
            }
        }*/

        //Repeat
        /*CoroutineScope(Dispatchers.IO).launch {
            repeat(3){
                doTask2()
            }
        }*/

        //TimeOut
       /* CoroutineScope(Dispatchers.IO).launch {
            withTimeoutOrNull(3000){
                for (i in 1000..1100){
                    Log.i(TAG,i.toString())
                    delay(1000)
                }
            }
        }
        //نتیجه:تا عدد 1002 چاپ میشود و بعد پروسه کنسل میشود*/


        //Job
       /* job = CoroutineScope(Dispatchers.Main).launch {
            delay(1000)
            Log.i(TAG,"Result1")
        }

        job.cancel()

        CoroutineScope(Dispatchers.Main).launch {
            delay(2000)
            Log.i(TAG,"Active:"+job.isActive.toString())
            Log.i(TAG,"Compeleted:"+job.isCompleted.toString())
            Log.i(TAG,"Cancelled:"+job.isCancelled.toString())
        }*/


        // نکته مهم :
//        اگر متد
//                Thread. sleep()
//        را قبلا دیده باشید ممکن است فکر کنید چه تفاوتی با delay()  در کروتین ها دارد؟
//
//        تفاوت این است که delay فقط دستورات را با تاخیر مواجه میکند
//                و ui فریز نمیشود.
//
//        در حالیکه
//                Thread.sleep()
//
//        در تایمی که به ان میدهیم تا تاخیر ایجاد کند علاوه بر دستورات، باعث فریز شدن و از کار انداختن ui نیز میشود.


        //Join and Cancel in Job
        /*CoroutineScope(Main).launch {
            val job = CoroutineScope(Main).launch {
                repeat(3){
                    delay(1000)
                    Log.i(TAG,"Coroutine is Working...")
                }
            }
            //job.join()
            delay(4000)
            job.cancelAndJoin()
            Log.i(TAG,"Done")
        }*/


        //LifeCycle
        lifecycleScope.launch {
            while (true) {
                delay(1000)
                Log.i(TAG,"Coroutine is Working...")
            }
        }

        CoroutineScope(Main).launch {
            delay(3000)
            Intent(this@MainActivity,SecondActivity::class.java).also {
                startActivity(it)
                finish()
            }
        }


    }


    private suspend fun doTask1():String{
        delay(3000)
        Log.i(TAG,"Result1")
        return "Result1"
    }

    private suspend fun doTask2():String{
        delay(4000)
        Log.i(TAG,"Result2")
        return "Result2"
    }
}