package com.reactnativetestexoplayer

import com.facebook.react.bridge.Promise
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReactContextBaseJavaModule
import com.facebook.react.bridge.ReactMethod
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import androidx.annotation.MainThread

class TestExoplayerModule(reactContext: ReactApplicationContext) :
        ReactContextBaseJavaModule(reactContext) {
    protected var _setUp = false
    protected var _player : ExoPlayer? = null
    protected val _context = reactContext

    override fun getName(): String {
        return "TestExoplayer"
    }

    @MainThread
    public fun setUp() {
        if (!`_setUp`) _player = ExoPlayer.Builder(_context).build()
        `_setUp` = true
    }

    // Example method
    // See https://reactnative.dev/docs/native-modules-android
    @ReactMethod
    fun multiply(a: Int, b: Int, promise: Promise) {
        promise.resolve(a * b)
    }

    @ReactMethod
    fun play(url: String) {
        setUp()

        // Build the media item.
        val mediaItem = MediaItem.fromUri(url)
        // Set the media item to be played.
        this._player?.setMediaItem(mediaItem)
        // Prepare the player.
        this._player?.prepare()
        // Start the playback.
        this._player?.play()
    }

    @ReactMethod
    fun getPlayer(promise: Promise) {
        promise.resolve(this._player == null)
    }

    @ReactMethod
    fun getPlaybackState(promise: Promise) {
        promise.resolve(_player?.playbackState)
    }

    @ReactMethod
    fun getPlaybackError(promise: Promise) {
        promise.resolve(_player?.playerError?.rendererName)
    }
}
