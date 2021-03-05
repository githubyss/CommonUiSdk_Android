package com.githubyss.mobile.common.ui.floatingview.designate.audioplayer

import android.content.Context
import android.view.View
import android.view.View.OnClickListener
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import androidx.annotation.LayoutRes
import com.githubyss.mobile.common.kit.manager.audio.enumeration.AudioState
import com.githubyss.mobile.common.kit.manager.audio.enumeration.VoiceType
import com.githubyss.mobile.common.kit.manager.audio.model.AudioListModel
import com.githubyss.mobile.common.kit.manager.audio.model.AudioModel
import com.githubyss.mobile.common.kit.manager.audio.player.AudioPlayListener
import com.githubyss.mobile.common.kit.manager.audio.player.AudioPlayManager
import com.githubyss.mobile.common.kit.manager.audio.util.ProgressTextUtils
import com.githubyss.mobile.common.ui.R
import com.githubyss.mobile.common.ui.floatingview.feature.autoshorten.AutoShortenView
import kotlinx.android.synthetic.main.comui_floating_audio_player.view.*


/**
 * AudioPlayerViewAutoShorten
 * 音频播放器 View（自动收起）
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/02/22 18:56:17
 */
class AudioPlayerViewAutoShorten : AutoShortenView, AudioPlayerViewInterface {
    
    /** ********** ********** ********** Companion ********** ********** ********** */
    
    companion object {
        private val TAG = AudioPlayerViewAutoShorten::class.simpleName ?: "simpleName is null"
        
        @LayoutRes
        private var designateLayoutId: Int = R.layout.comui_floating_audio_player
    }
    
    
    /** ********* ********** ********** Properties ********** ********** ********** */
    
    private var designateContext: Context? = null
    private var designateView: AudioPlayerViewAutoShorten? = null
    
    override var isMovable = true
        set(value) {
            field = value
            super.isMovable = value
        }
    
    var designateViewListener: AudioPlayerViewListener? = null
    var audioPlayListener: AudioPlayListener? = null
    
    
    /** ********* ********** ********** Constructors ********** ********** ********** */
    
    constructor(context: Context, @LayoutRes layoutId: Int = designateLayoutId) : super(context, null) {
        designateContext = context
        if (super.featureView == null) {
            super.featureView = View.inflate(context, layoutId, this)
            designateView = this
            initInBase()
            initInDesignated()
        }
    }
    
    
    /** ********* ********** ********** Override ********** ********** ********** */
    
    override fun lengthen() {
        designateView?.lengthenAnimator()
    }
    
    override fun shorten() {
        designateView?.shortenAnimator()
    }
    
    override fun play(audioList: List<AudioModel?>?) {
        if (audioList == null) return
        
        val audioListModel = AudioListModel()
        audioListModel.audioList = audioList
        audioListModel.currentIndex = 0
        
        val currentAudio = AudioPlayManager.INSTANCE.getCurrentAudio()
        val newListFirstAudio = audioListModel.audioList?.get(0)
        AudioPlayManager.INSTANCE.audioListModel = audioListModel
        
        val isContinue = currentAudio != null && newListFirstAudio != null && currentAudio.id == newListFirstAudio.id
        
        if (isContinue) {
            AudioPlayManager.INSTANCE.start()
        } else {
            AudioPlayManager.INSTANCE.stop()
            AudioPlayManager.INSTANCE.play(designateContext)
        }
        if (designateView != null) {
            // if (AudioPlayManager.getInstance().getAudioList() == null || AudioPlayManager.getInstance().getAudioList().size() == 0) {
            //     AudioListModel audioListModel = new AudioListModel();
            //     audioListModel.setAudioList(audioList);
            //     audioListModel.setCurrentIndex(0);
            //
            //     if (audioListModel != null) {
            //         AudioPlayManager.getInstance().setInfo(audioListModel);
            //         AudioPlayManager.getInstance().play(containerContext);
            //         designatedFloatingView.refreshVoiceSwitch();
            //     } else {
            //         ComkitToastUtils.INSTANCE.showMessage(containerContext, ResUtils.INSTANCE.getString(containerContext, R.string.music_play_no_list), Toast.LENGTH_SHORT, false);
            //     }
            // } else {
            //     designatedFloatingView.refreshData();
            // }
            
            designateView?.refreshVoiceSwitch()
            
            designateView?.audioPlayListener = object : AudioPlayListener {
                override fun onStateChanged(@AudioState audioState: Int) {
                    // if (mMusicNotification != null && !misShow && !mfinished && !isFinishing()) {
                    //     mMusicNotification.showNotify();
                    // }
                }
                
                override fun onPlayProgress(currentPosition: Int) {}
                override fun onBufferingUpdateProgress(percent: Int) {}
            }
        }
    }
    
    override fun start() {
        AudioPlayManager.INSTANCE.start()
    }
    
    override fun pause() {
        AudioPlayManager.INSTANCE.pause()
    }
    
    override fun previous() {
        AudioPlayManager.INSTANCE.previous()
    }
    
    override fun next() {
        AudioPlayManager.INSTANCE.next()
    }
    
    override fun stop() {
        AudioPlayManager.INSTANCE.stop()
    }
    
    override fun switchVoice() {
        AudioPlayManager.INSTANCE.switchVoice()
    }
    
    override fun customView(view: AudioPlayerViewAutoShorten?) {
        designateView = view
    }
    
    override fun customView(layoutId: Int) {
        designateLayoutId = layoutId
    }
    
    
    /** ********** ********** ********** Functions ********** ********** ********** */
    
    fun isFloatingShow(): Boolean {
        return designateView?.isShown ?: false
    }
    
    fun getCurrentAudio(): AudioModel? {
        return AudioPlayManager.INSTANCE.getCurrentAudio()
    }
    
    fun refreshPlayerVisibility(isMini: Boolean) {
        if (isMini) {
            frameLayout_normalPlayerContainer.visibility = View.GONE
            frameLayout_miniPlayerContainer.visibility = View.VISIBLE
        } else {
            frameLayout_normalPlayerContainer.visibility = View.VISIBLE
            frameLayout_miniPlayerContainer.visibility = View.GONE
        }
    }
    
    
    /** ********* ********** ********** Private ********** ********** ********** */
    
    private fun initInDesignated() {
        initView()
        initListener()
    }
    
    private fun initView() {
    }
    
    private fun initListener() {
        imageView_playPauseController.setOnClickListener(onClickListener)
        imageView_voiceSwitch.setOnClickListener(onClickListener)
        imageView_close.setOnClickListener(onClickListener)
        frameLayout_miniPlayerContainer.setOnClickListener(onClickListener)
        
        seekBar_audioPlayer.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                // LogcatUtils.d("seekBar_audioPlayer onProgressChanged", "progress: " + progress);
                textView_timePosition.text = ProgressTextUtils.getProgressText(progress.toLong())
            }
            
            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }
            
            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                AudioPlayManager.INSTANCE.seekTo(seekBar?.progress ?: return)
            }
        })
        
        AudioPlayManager.INSTANCE.audioPlayListener = object : AudioPlayListener {
            override fun onStateChanged(@AudioState audioState: Int) {
                stateChanged(audioState)
                audioPlayListener?.onStateChanged(audioState)
            }
            
            override fun onPlayProgress(currentPosition: Int) {
                playProgress(currentPosition)
                audioPlayListener?.onPlayProgress(currentPosition)
            }
            
            override fun onBufferingUpdateProgress(percent: Int) {
                bufferingUpdateProgress(percent)
                audioPlayListener?.onBufferingUpdateProgress(percent)
            }
        }
    }
    
    /**
     * 当前已经在播放
     * 重新进入初始化当前数据
     */
    private fun refreshData() {
        if (seekBar_audioPlayer != null) {
            stateChanged(AudioState.START)
            // 先把一些漏掉的东西初始化下
            stateChanged(AudioState.READY)
            stateChanged(AudioPlayManager.INSTANCE.audioState)
            seekBar_audioPlayer.progress = AudioPlayManager.INSTANCE.getCurrentPosition()
            seekBar_audioPlayer.secondaryProgress = AudioPlayManager.INSTANCE.updateProgress
        }
    }
    
    private fun refreshVoiceSwitch() {
        if (AudioPlayManager.INSTANCE.getCurrentAudio() == null) return
        
        when (AudioPlayManager.INSTANCE.getCurrentAudio()?.voiceType ?: return) {
            VoiceType.MALE -> imageView_voiceSwitch.setImageResource(R.drawable.comui_audio_player_voice_male)
            VoiceType.FEMALE -> imageView_voiceSwitch.setImageResource(R.drawable.comui_audio_player_voice_female)
        }
    }
    
    private fun playProgress(currentPosition: Int) {
        if (!seekBar_audioPlayer.isPressed) {
            seekBar_audioPlayer.progress = currentPosition
        }
    }
    
    private fun bufferingUpdateProgress(percent: Int) {
        seekBar_audioPlayer.secondaryProgress = percent
    }
    
    private fun stateChanged(@AudioState audioState: Int) {
        when (audioState) {
            AudioState.START -> {
                if (AudioPlayManager.INSTANCE.getAudioList() == null || AudioPlayManager.INSTANCE.getAudioList()?.size == 0) {
                    return
                }
                textView_title.text = AudioPlayManager.INSTANCE.getAudioList()
                        ?.get(AudioPlayManager.INSTANCE.getCurrentIndex())?.title
                
                // mMusicPlayAuthorTv.setText(mContext.getString(R.string.music_play_author) + MusicManager.getInstance().getPlayList().get(MusicManager.getInstance().getPosition()).getAuthor());
                // mMusicPreviousIv.setImageResource(R.drawable.music_play_previous_click_icon);
                // mMusicNextIv.setImageResource(R.drawable.music_play_next_click_icon);
                // mMusicPreviousIv.setEnabled(true);
                // mMusicNextIv.setEnabled(true);
                
                seekBar_audioPlayer.progress = 0
                seekBar_audioPlayer.secondaryProgress = 0
                textView_timePosition.text = ""
                textView_timeDuration.text = ""
                if (AudioPlayManager.INSTANCE.isLoop) {
                    return
                }
            }
            AudioState.PREPARE -> {
            }
            AudioState.PLAYING -> {
                imageView_playPauseController.setImageResource(R.drawable.comui_audio_player_pause)
                updateAudioInfo(true)
            }
            AudioState.STOP -> {
                imageView_playPauseController.setImageResource(R.drawable.comui_audio_player_start)
                // 停止时进度就保留在最后
                // seekBar_audioPlayer.setProgress(0);
                // seekBar_audioPlayer.setSecondaryProgress(0);
                // textView_timePosition.setText("");
                // textView_timeDuration.setText("");
                updateAudioInfo(false)
            }
            AudioState.END -> {
                updateAudioInfo(false)
            }
            AudioState.PAUSE -> {
                imageView_playPauseController.setImageResource(R.drawable.comui_audio_player_start)
                updateAudioInfo(false)
            }
            AudioState.READY -> {
                seekBar_audioPlayer.max = AudioPlayManager.INSTANCE.MaxProgress
                textView_timePosition.text = AudioPlayManager.INSTANCE.getCurrentTime()
                textView_timeDuration.text = AudioPlayManager.INSTANCE.getDurationTime()
            }
            AudioState.SWITCH -> {
                refreshVoiceSwitch()
            }
        }
    }
    
    private fun updateAudioInfo(isPlaying: Boolean) {
        val audioModel = AudioPlayManager.INSTANCE.getCurrentAudio()
        if (audioModel != null) {
            audioModel.isPlaying = isPlaying
        }
        
        designateViewListener?.onUpdateAudioInfo(audioModel)
    }
    
    
    /** ********** ********** ********** Implementations ********** ********** **********  */
    
    /** 点击监听 */
    private val onClickListener = OnClickListener { v ->
        val id = v.id
        if (id == R.id.imageView_playPauseController) {
            if (AudioPlayManager.INSTANCE.audioState == AudioState.PLAYING) {
                AudioPlayManager.INSTANCE.pause()
            } else {
                AudioPlayManager.INSTANCE.start()
            }
        } else if (id == R.id.imageView_voiceSwitch) {
            AudioPlayManager.INSTANCE.switchVoice()
        } else if (id == R.id.imageView_close) {
            AudioPlayManager.INSTANCE.destroy()
            super.removeAnimator()
        } else if (id == R.id.frameLayout_miniPlayerContainer) {
            super.lengthenAnimator()
        }
        
        // MusicManager.getInstance().previous();
        // MusicManager.getInstance().next();
    }
}
