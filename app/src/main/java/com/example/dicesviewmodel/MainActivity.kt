package com.example.dicesviewmodel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.SeekBar
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val model = ViewModelProvider(this).get(DesViewModel::class.java)
        val bouton = findViewById<Button>(R.id.rollButton)
        val image = findViewById<ImageView>(R.id.faceImage)
        val image2 = findViewById<ImageView>(R.id.faceImage2)
        val image3 = findViewById<ImageView>(R.id.faceImage2)
        val score = findViewById<TextView>(R.id.score)
        val seekBar = findViewById<SeekBar>(R.id.seekBar)

        bouton.setOnClickListener {
            model.roll()
        }

        seekBar.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener
        {
            override fun onProgressChanged(seekBar: SeekBar?, p1: Int, p2: Boolean) {
                model.nombreDes = p1
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
                TODO("Not yet implemented")
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
                TODO("Not yet implemented")
            }

        })

        model.score.observe(this, Observer { valeur -> score.text = valeur.toString() })

        model.premier.observe(this, Observer {  etat -> setImage(etat, image) })

        model.second.observe(this, Observer {etat -> setImage(etat, image2) })

        model.troisieme.observe(this, Observer { etat -> setImage(etat, image3) })


    }

    private fun  setImage(etat: Etat, image: ImageView) {
        if (etat != Etat.HIDE)  image.visibility = View.VISIBLE
        when (etat) {
            Etat.HIDE -> image.visibility = View.GONE
            Etat.UNKNOWN -> image.setImageResource(android.R.drawable.ic_menu_help)
            Etat.ONE -> image.setImageResource(R.drawable.un)
            Etat.TWO -> image.setImageResource(R.drawable.deux)
            Etat.THREE -> image.setImageResource(R.drawable.trois)
            Etat.FOUR -> image.setImageResource(R.drawable.quatre)
            Etat.FIVE -> image.setImageResource(R.drawable.cinq)
            Etat.SIX -> image.setImageResource(R.drawable.six)
        }
    }
}