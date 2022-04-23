package com.example.a2022_03_25_Radio_Lab5.model

import kotlin.collections.MutableList

var stations: MutableList<RadioStation> = mutableListOf(RadioStation())


class RadioStations() {

    init {
        stations.add(RadioStation("WHUS", "http://stream.whus.org:8000/whusfm"))
        stations.add(RadioStation("Basefmuk", "https://eu10.fastcast4u.com/basefmuk877"))
        stations.add(RadioStation("Chilltrax", "https://streamssl.chilltrax.com/index.html?sid=1"))
        stations.add(RadioStation("rbb Radio Eins Germany", "http://www.radioeins.de/livemp3"))
        stations.add(RadioStation("Nativa FM Brazil", "https://servidor28.brlogic.com:8264/live"))
        stations.add(RadioStation("The Current", "https://current.stream.publicradio.org/kcmp.mp3"))
        stations.add(RadioStation("Classic MPR", "https://cms.stream.publicradio.org/cms.mp3"))
        stations.add(RadioStation("WNYC", "http://sgrewind.streamguys1.com/wnycfm/wnycfm.aac/playlist.m3u8"))
        stations.add(RadioStation("KMOJ", "https://kmojfm.streamguys1.com/live-mp3"))
        stations.add(RadioStation("The Krush Wine Country Netherlands", "https://19213.live.streamtheworld.com/KRSHFM.mp3"))
        stations.add(RadioStation("Haarlem 105 Germany", "http://stream.haarlem105.nl:8000/haarlem105low.mp3"))
        stations.add(RadioStation("Radio Caroline", "http://sc6.radiocaroline.net:8040/mp3"))

    }

    public fun getStations() : MutableList<RadioStation> {

        return stations
    }

    public fun size() : Int {
        return stations.size
    }
}

