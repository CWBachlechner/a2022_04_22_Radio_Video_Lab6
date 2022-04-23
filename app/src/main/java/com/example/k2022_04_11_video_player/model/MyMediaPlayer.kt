package com.example.a2022_03_25_Radio_Lab5.model

import android.media.AudioAttributes
import android.media.MediaPlayer

class MyMediaPlayer {
    private var mediaPlayer: MediaPlayer = MediaPlayer()

    fun pause() {
        mediaPlayer.pause()
    }
    fun start() {
        mediaPlayer.start()
    }


    fun setUpRadio(url: String) {
        mediaPlayer = MediaPlayer().apply{
            setAudioAttributes(
                AudioAttributes.Builder()
                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                    .setUsage(AudioAttributes.USAGE_MEDIA)
                    .build()
            )
        }
        setAndPrepareRadioLink(url)
    }

    fun startRadio(mediaPlayer: MyMediaPlayer) {
        mediaPlayer.start()
    }
    fun setAndPrepareRadioLink(url: String) {
        mediaPlayer.reset()
        mediaPlayer.setDataSource(url)
        mediaPlayer.setOnPreparedListener {
            it.start()
        }
        mediaPlayer.prepareAsync()
    }
}