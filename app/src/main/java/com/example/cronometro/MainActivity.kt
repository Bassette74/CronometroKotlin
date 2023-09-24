package com.example.cronometro

import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity




class MainActivity : AppCompatActivity() {


    private lateinit var chronometerTextView: TextView
    private lateinit var ButtonStart: Button
    private lateinit var PauseButton: Button

    private var cronometro: CountDownTimer? = null
    private var tempoRestante: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        chronometerTextView = findViewById(R.id.chronometerTextView)
        ButtonStart = findViewById(R.id.ButtonStart)
        PauseButton = findViewById(R.id.PauseButton)

        ButtonStart.setOnClickListener {
            iniciarCronometro()
        }

        PauseButton.setOnClickListener {
            pararCronometro()
        }
    }

    private fun iniciarCronometro() {
        cronometro = object : CountDownTimer(tempoRestante, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                tempoRestante = millisUntilFinished
                atualizarInterface()
            }

            override fun onFinish() {
                tempoRestante = 0
                atualizarInterface()
            }
        }.start()
    }

    private fun pararCronometro() {
        cronometro?.cancel()
        cronometro = null
        atualizarInterface()
    }

    private fun atualizarInterface() {
        val minutos = (tempoRestante / 1000) / 60
        val segundos = (tempoRestante / 1000) % 60

        val textoTempoRestante = String.format("%02d:%02d", minutos, segundos)
        chronometerTextView.text = textoTempoRestante

        if (tempoRestante == 0L) {
            pararCronometro()
        }
    }


}
