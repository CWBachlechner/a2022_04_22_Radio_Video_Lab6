package com.example.k2022_04_11_video_player

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.a2022_03_25_Radio_Lab5.model.MyMediaPlayer
import com.example.a2022_03_25_Radio_Lab5.model.RadioStation
import com.example.a2022_03_25_Radio_Lab5.model.RadioStations


lateinit var allStations : MutableList<RadioStation>

private var radioOn: Boolean = false
private var initialized: Boolean = false

var myMediaPlayer: MyMediaPlayer = MyMediaPlayer()

class RadioAdapter(var radioStations: RadioStations) : RecyclerView.Adapter<RadioAdapter.RadioViewHolder> () {

    init {
        allStations = radioStations.getStations()
    }

    class RadioViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

        init {
            itemView.setOnClickListener(this)
        }

        var name : TextView = itemView.findViewById(R.id.name_text)
        var uri : TextView = itemView.findViewById(R.id.uri_text)
        var whichCard: Int = 0
        var selected: Int = 0


        fun bind(position: Int) {
            name.text = allStations[position].name
            uri.text = allStations[position].uri

            whichCard = position
        }

        override fun onClick(p0: View?) {
            var url = allStations[whichCard].uri.toString()
            if(!initialized) {
                myMediaPlayer?.setUpRadio(url)
                initialized = true
            }

            if (radioOn) {
                if(selected == whichCard){
                    myMediaPlayer?.pause()
                    Toast.makeText(p0?.context, "Pausing: " + allStations[whichCard].name, Toast.LENGTH_LONG).show()
                }
                else {
                    myMediaPlayer?.setAndPrepareRadioLink(url)
                    Toast.makeText(p0?.context, "Listening To: " + allStations[whichCard].name, Toast.LENGTH_LONG).show()
                }
            }
            else {
                myMediaPlayer?.setAndPrepareRadioLink(url)
                Toast.makeText(p0?.context, "Listening To: " + allStations[whichCard].name, Toast.LENGTH_LONG).show()
            }
            radioOn = !radioOn
            selected = whichCard
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RadioViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.radio_card, parent, false)

         return RadioViewHolder(view)
    }

    override fun onBindViewHolder(holder: RadioViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int {
        return allStations.size
    }

}