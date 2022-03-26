package com.kingfishapps.fx

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.navigation.findNavController

class MainActivity : AppCompatActivity() {


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
       /* (applicationContext as MyApplication).appComponent.inject(this)
        println("Retrofit Object : ${retrofit}")*/
        findNavController(R.id.nav_host_fragment)



    }
}