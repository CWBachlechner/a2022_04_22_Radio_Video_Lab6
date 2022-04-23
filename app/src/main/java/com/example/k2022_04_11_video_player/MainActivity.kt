package com.example.k2022_04_11_video_player

import android.media.MediaDrm
import android.media.MediaPlayer
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.SurfaceHolder
import android.view.View
import android.view.WindowManager
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.a2022_03_25_Radio_Lab5.model.RadioStations

class MainActivity : AppCompatActivity(), MediaPlayer.OnPreparedListener, MediaPlayer.OnDrmInfoListener,
    SurfaceHolder.Callback, SeekBar.OnSeekBarChangeListener {

    lateinit var videoView: VideoView
    lateinit var videoToggle: Button
    lateinit var skip5Button: Button
    lateinit var rew5Button: Button
    lateinit var mediaController: MediaController
    lateinit var mediaPlayer: MediaPlayer
    private lateinit var recyclerView: RecyclerView
    var videoOn: Boolean = false
    var firstTimeOn: Boolean = true
    var position: Int = 0

    val videoStr = "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/TearsOfSteel.mp4"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var radioStations = RadioStations()

        recyclerView = findViewById(R.id.recycleview)
        recyclerView.adapter = RadioAdapter(radioStations)
        recyclerView.layoutManager = LinearLayoutManager(baseContext)

        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)

        videoView = findViewById(R.id.videoView)
        videoToggle = findViewById(R.id.video_toggle_button)
        skip5Button = findViewById(R.id.skipButton)
        rew5Button = findViewById(R.id.rewButton)
        mediaPlayer = MediaPlayer()

        mediaController = MediaController(this)
        mediaController.setAnchorView(videoView)

        videoView.setMediaController(mediaController)
        videoView.holder.addCallback(this)

        mediaController.setPrevNextListeners({
           object: View.OnClickListener {
               override fun onClick(p0: View?) {
                   Toast.makeText(baseContext,"Previous", Toast.LENGTH_SHORT).show()
               }
           }

        }, {
            object: View.OnClickListener {
                override fun onClick(p0: View?) {
                    Toast.makeText(baseContext,"Next", Toast.LENGTH_SHORT).show()
                }
            }
        });

        mediaPlayer.setOnPreparedListener(this)

        skip5Button.setOnClickListener {
            if (videoOn) {
                position = mediaPlayer.getCurrentPosition()
                mediaPlayer.seekTo(position + 5000)
            }
        }

        rew5Button.setOnClickListener {
            if (videoOn) {
                position = mediaPlayer.getCurrentPosition()
                mediaPlayer.seekTo(position - 5000)
            }
        }

        videoToggle.setOnClickListener {
            if (videoOn) {
                mediaPlayer.pause()
                mediaPlayer.stop()
            } else {
                if (firstTimeOn) {
                    mediaPlayer.prepareAsync()
                    firstTimeOn = !firstTimeOn
                } else {
                    mediaPlayer.reset()
                    mediaPlayer.setDataSource(applicationContext,Uri.parse(videoStr))
                    mediaPlayer.prepareAsync()
                }
            }
            videoOn = ! videoOn
        }
    }

    override fun onPrepared(mediaPlayer: MediaPlayer) {
        mediaPlayer.start()
        mediaController.requestFocus()
    }

    override fun onDrmInfo(mediaPlayer: MediaPlayer, drmInfo: MediaPlayer.DrmInfo?) {
        mediaPlayer.apply {
            val key = drmInfo?.supportedSchemes?.get(0)
            key?.let {
                prepareDrm(key)
                val keyRequest = getKeyRequest(
                    null, null, null,
                    MediaDrm.KEY_TYPE_STREAMING, null
                )
                provideKeyResponse(null, keyRequest.data)
            }
        }
    }

    override fun surfaceCreated(surfaceHolder: SurfaceHolder) {
        mediaPlayer.apply {
            setOnDrmInfoListener(this@MainActivity)
            //                 Uri.parse())
            setDataSource(applicationContext, Uri.parse(videoStr))
            setDisplay(surfaceHolder)
        }
    }

    override fun onProgressChanged(p0: SeekBar?, p1: Int, fromUser: Boolean) {
        if(fromUser) {
        }
    }


    override fun surfaceChanged(p0: SurfaceHolder, p1: Int, p2: Int, p3: Int) {
        // Nothing
    }

    override fun surfaceDestroyed(p0: SurfaceHolder) {
        // Nothing
    }

    override fun onStartTrackingTouch(p0: SeekBar?) {
        // Nothing
    }

    override fun onStopTrackingTouch(p0: SeekBar?) {
        // Nothing
    }
}


