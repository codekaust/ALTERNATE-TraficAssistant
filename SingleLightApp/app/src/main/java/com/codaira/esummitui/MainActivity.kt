package com.codaira.esummitui

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.google.firebase.database.*
import java.util.*
import kotlin.math.E
import kotlin.math.log
import kotlin.math.pow


class MainActivity : AppCompatActivity() {
    val controller = "p2"
    val controllerNumber = 2-1
    val tiebreaker = 2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<View>(R.id.imageButtonGreen).setBackgroundColor(Color.GRAY)

        FirebaseDatabase.getInstance().reference.child("p1").setValue(TrafficFactors(controller = "p1",tieBreaker = 1, green = false))
        FirebaseDatabase.getInstance().reference.child("p2").setValue(TrafficFactors(controller = "p2",tieBreaker = 2, green = false))
        FirebaseDatabase.getInstance().reference.child("p3").setValue(TrafficFactors(controller = "p3",tieBreaker = 3, green = false))
        FirebaseDatabase.getInstance().reference.child("p4").setValue(TrafficFactors(controller = "p4",tieBreaker = 4, green = false))

        val k = (log(15.0, E)) / 30
        var t: Int = 0
        var a = E.pow(k * (t - 30)).toInt()


        //get number of cars from firebase and store in variable c, then update on firebase

        Timer().scheduleAtFixedRate(object : TimerTask() {
            override fun run() {
                t += 5
                a = E.pow(k * (t - 30)).toInt()

                FirebaseDatabase.getInstance().reference.child(controller).child("age").setValue(a)
                FirebaseDatabase.getInstance().reference.child(controller).child("cars").setValue((0..10).random())
            }
        }, 0, 5000)



        FirebaseDatabase.getInstance().reference.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onDataChange(p0: DataSnapshot) {

                var p: Array<TrafficFactors> = arrayOf<TrafficFactors>(TrafficFactors(),TrafficFactors(),TrafficFactors(),TrafficFactors())
                var i = 0
                var greenP =0;
                for (datasnapshot in p0.children) {
                    if (i < 4) {
                        p[i] = datasnapshot.getValue(TrafficFactors::class.java)!!
                        if (p[i].green==true){
                            greenP=i;
                        }
                    }
                    i++
                }

                if(System.currentTimeMillis()-p[greenP].greenStartTime>p[greenP].greenForTime){
                    val arr = arrayOf(p[0].cars+p[0].age,p[1].cars+p[1].age,p[2].cars+p[2].age,p[3].cars+p[3].age)
                    Arrays.sort(arr)

                    when(arr[3]){
                        p[controllerNumber].cars+p[controllerNumber].age -> {
                            for (i in 0..2)
                                if ((arr[3] == arr[i] && p[controllerNumber].age>p[i].age)|| arr[3]!=arr[i]) {1


                                    findViewById<View>(R.id.imageButtonGreen).setBackgroundColor(Color.GREEN)
                                    findViewById<View>(R.id.imageButtonRed).setBackgroundColor(Color.GRAY)

                                    var stdDev = 0

                                    for (s in p.iterator()) {
                                        stdDev += Math.abs(s.cars - p[controllerNumber].cars)
                                    }

                                    stdDev /= 3

                                    val greenForTime: Long = ((10 + 3 * stdDev) * 1000).toLong()

                                    FirebaseDatabase.getInstance().reference.child(controller).child("greenForTime")
                                        .setValue(greenForTime)
                                    FirebaseDatabase.getInstance().reference.child(controller).child("greenStartTime")
                                        .setValue(System.currentTimeMillis())
                                    FirebaseDatabase.getInstance().reference.child(controller).child("green")
                                        .setValue(true)
                                }
                        }


                        else -> {
                            findViewById<View>(R.id.imageButtonGreen).setBackgroundColor(Color.GRAY)
                            findViewById<View>(R.id.imageButtonRed).setBackgroundColor(Color.RED)

                            if(greenP == controllerNumber){
                                FirebaseDatabase.getInstance().reference.child(controller).child("greenForTime").setValue(0L)
                                FirebaseDatabase.getInstance().reference.child(controller).child("greenStartTime").setValue(0L)
                                FirebaseDatabase.getInstance().reference.child(controller).child("green").setValue(false)
                            }
                        }
                    }
                }

            }


        })


    }
}




