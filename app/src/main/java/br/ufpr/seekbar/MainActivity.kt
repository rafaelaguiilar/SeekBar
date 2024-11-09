package br.ufpr.seekbar

import android.os.Bundle
import android.widget.SeekBar
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val sbMen = findViewById<SeekBar>(R.id.seekBarMen)
        val sbWomen = findViewById<SeekBar>(R.id.seekBarWomen)
        val menQtt = findViewById<TextView>(R.id.textViewMen)
        val womenQtt = findViewById<TextView>(R.id.textViewWomen)

        sbMen?.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                menQtt.text = p1.toString()
                calculate(p1, sbWomen.progress)
            }
            override fun onStartTrackingTouch(p0: SeekBar?) {
            }
            override fun onStopTrackingTouch(p0: SeekBar?) {
            }

        })

        sbWomen?.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                womenQtt.text = p1.toString()
                calculate(sbMen.progress, p1)
            }
            override fun onStartTrackingTouch(p0: SeekBar?) {
            }
            override fun onStopTrackingTouch(p0: SeekBar?) {
            }

        })
    }

    fun calculate(menQtt: Int, womenQtt: Int) {
        val outputSausage = findViewById<TextView>(R.id.outputSausage)
        val outputMeat = findViewById<TextView>(R.id.outputMeat)
        val sausageQtt: Double = (menQtt * 250.0 + womenQtt * 150.0)/1000
        val meatQtt: Double = (menQtt * 450.0 + womenQtt * 300.0)/1000
        outputMeat.text = meatQtt.toString() + " Kg"
        outputSausage.text = sausageQtt.toString() + " Kg"
    }

}