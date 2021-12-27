package com.githubyss.mobile.common.ui.floating_view.designate.audioplayer

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import androidx.annotation.LayoutRes
import com.githubyss.mobile.common.kit.manager.audio_player.enumeration.AudioState
import com.githubyss.mobile.common.kit.manager.audio_player.enumeration.VoiceType
import com.githubyss.mobile.common.kit.manager.audio_player.model.AudioListModel
import com.githubyss.mobile.common.kit.manager.audio_player.model.AudioModel
import com.githubyss.mobile.common.kit.manager.audio_player.player.AudioPlayListener
import com.githubyss.mobile.common.kit.manager.audio_player.player.AudioPlayManager
import com.githubyss.mobile.common.kit.manager.audio_player.util.ProgressTextUtils
import com.githubyss.mobile.common.ui.R
import com.githubyss.mobile.common.ui.databinding.ComuiFloatingAudioPlayerBinding
import com.githubyss.mobile.common.ui.floating_view.feature.autoshorten.AutoShortenView


/**
 * AudioPlayerViewAutoShorten
 * 音频播放器 View（自动收起）
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/02/22 18:56:17
 */
class AudioPlayerViewAutoShorten : AutoShortenView, AudioPlayerViewInterface {
    
    /** ****************************** Properties ****************************** */
    
    companion object {
        private val TAG: String = AudioPlayerViewAutoShorten::class.java.simpleName
        
        @LayoutRes
        private var designateLayoutId: Int = R.layout.comui_floating_audio_player
    }
    
    private var _binding: ComuiFloatingAudioPlayerBinding? = null
    private val binding: ComuiFloatingAudioPlayerBinding get() = _binding!!
    
    private var designateContext: Context? = null
    private var designateView: AudioPlayerViewAutoShorten? = null
    
    override var isMovable = true
        set(value) {
            field = value
            super.isMovable = value
        }
    
    var designateViewListener: AudioPlayerViewListener? = null
    var audioPlayListener: AudioPlayListener? = null
    
    
    /** ****************************** Constructors ****************************** */
    
    constructor(context: Context, @LayoutRes layoutId: Int = designateLayoutId) : super(context, null) {
        designateContext = context
        if (super.featureView == null) {
            // super.featureView = View.inflate(context, layoutId, this)
            // super.featureView = LayoutInflater.from(context).inflate(layoutId, this)
            _binding = ComuiFloatingAudioPlayerBinding.inflate(LayoutInflater.from(context), this, true)
            super.featureView = binding.root
            designateView = this
            initInBase()
            initInDesignated()
        }
    }
    
    
    /** ****************************** Override ****************************** */
    
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
            //         ComkitToastUtils.INSTANCE.showMessage(containerContext, ResourceUtils.INSTANCE.getString(containerContext, R.string.music_play_no_list), Toast.LENGTH_SHORT, false);
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
    
    
    /** ****************************** Functions ****************************** */
    
    fun isFloatingShow(): Boolean {
        return designateView?.isShown ?: false
    }
    
    fun getCurrentAudio(): AudioModel? {
        return AudioPlayManager.INSTANCE.getCurrentAudio()
    }
    
    fun refreshPlayerVisibility(isMini: Boolean) {
        if (isMini) {
            binding.frameLayoutNormalPlayerContainer.visibility = View.GONE
            binding.frameLayoutMiniPlayerContainer.visibility = View.VISIBLE
        } else {
            binding.frameLayoutNormalPlayerContainer.visibility = View.VISIBLE
            binding.frameLayoutMiniPlayerContainer.visibility = View.GONE
        }
    }
    
    
    /** ****************************** Private ****************************** */
    
    private fun initInDesignated() {
        initView()
        initListener()
    }
    
    private fun initView() {
    }
    
    private fun initListener() {
        binding.imageViewPlayPauseController.setOnClickListener(onClickListener)
        binding.imageViewVoiceSwitch.setOnClickListener(onClickListener)
        binding.imageViewClose.setOnClickListener(onClickListener)
        binding.frameLayoutMiniPlayerContainer.setOnClickListener(onClickListener)
        
        binding.seekBarAudioPlayer.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                // LogUtils.d("seekBar_audioPlayer onProgressChanged", "progress: " + progress);
                binding.textViewTimePosition.text = ProgressTextUtils.getProgressText(progress.toLong())
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
        if (binding.seekBarAudioPlayer != null) {
            stateChanged(AudioState.START)
            // 先把一些漏掉的东西初始化下
            stateChanged(AudioState.READY)
            stateChanged(AudioPlayManager.INSTANCE.audioState)
            binding.seekBarAudioPlayer.progress = AudioPlayManager.INSTANCE.getCurrentPosition()
            binding.seekBarAudioPlayer.secondaryProgress = AudioPlayManager.INSTANCE.updateProgress
        }
    }
    
    private fun refreshVoiceSwitch() {
        if (AudioPlayManager.INSTANCE.getCurrentAudio() == null) return
        
        when (AudioPlayManager.INSTANCE.getCurrentAudio()?.voiceType ?: return) {
            VoiceType.MALE -> binding.imageViewVoiceSwitch.setImageResource(R.drawable.comui_audio_player_voice_male)
            VoiceType.FEMALE -> binding.imageViewVoiceSwitch.setImageResource(R.drawable.comui_audio_player_voice_female)
        }
    }
    
    private fun playProgress(currentPosition: Int) {
        if (!binding.seekBarAudioPlayer.isPressed) {
            binding.seekBarAudioPlayer.progress = currentPosition
        }
    }
    
    private fun bufferingUpdateProgress(percent: Int) {
        binding.seekBarAudioPlayer.secondaryProgress = percent
    }
    
    private fun stateChanged(@AudioState audioState: Int) {
        when (audioState) {
            AudioState.START -> {
                if (AudioPlayManager.INSTANCE.getAudioList() == null || AudioPlayManager.INSTANCE.getAudioList()?.size == 0) {
                    return
                }
                binding.textViewTitle.text = AudioPlayManager.INSTANCE.getAudioList()?.get(AudioPlayManager.INSTANCE.getCurrentIndex())?.title
                
                // mMusicPlayAuthorTv.setText(mContext.getString(R.string.music_play_author) + MusicManager.getInstance().getPlayList().get(MusicManager.getInstance().getPosition()).getAuthor());
                // mMusicPreviousIv.setImageResource(R.drawable.music_play_previous_click_icon);
                // mMusicNextIv.setImageResource(R.drawable.music_play_next_click_icon);
                // mMusicPreviousIv.setEnabled(true);
                // mMusicNextIv.setEnabled(true);
                
                binding.seekBarAudioPlayer.progress = 0
                binding.seekBarAudioPlayer.secondaryProgress = 0
                binding.textViewTimePosition.text = ""
                binding.textViewTimeDuration.text = ""
                if (AudioPlayManager.INSTANCE.isLoop) {
                    return
                }
            }
            AudioState.PREPARE -> {
            }
            AudioState.PLAYING -> {
                binding.imageViewPlayPauseController.setImageResource(R.drawable.comui_audio_player_pause)
                updateAudioInfo(true)
            }
            AudioState.STOP -> {
                binding.imageViewPlayPauseController.setImageResource(R.drawable.comui_audio_player_start)
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
                binding.imageViewPlayPauseController.setImageResource(R.drawable.comui_audio_player_start)
                updateAudioInfo(false)
            }
            AudioState.READY -> {
                binding.seekBarAudioPlayer.max = AudioPlayManager.INSTANCE.MaxProgress
                binding.textViewTimePosition.text = AudioPlayManager.INSTANCE.getCurrentTime()
                binding.textViewTimeDuration.text = AudioPlayManager.INSTANCE.getDurationTime()
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
    
    
    /** ****************************** Implementations ****************************** */
    
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
