package com.githubyss.common.ui.dialog.voice_select

import com.githubyss.common.ui.R


object VoiceToneDataCenter {
    @JvmStatic
    val voiceToneList = ArrayList<VoiceTone>()

    init {
        initData()
    }

    private fun initData() {
        voiceToneList.add(VoiceTone(0, "叶子", R.drawable.comui_head_yezi, VoiceToneSelectState.YES))
        voiceToneList.add(VoiceTone(1, "虫虫", R.drawable.comui_head_chongchong, VoiceToneSelectState.NO))
        voiceToneList.add(VoiceTone(2, "晓媛", R.drawable.comui_head_xiaoyuan, VoiceToneSelectState.NO))
        voiceToneList.add(VoiceTone(3, "一菲", R.drawable.comui_head_yifei, VoiceToneSelectState.NO))
        voiceToneList.add(VoiceTone(4, "子晴", R.drawable.comui_head_ziqing, VoiceToneSelectState.NO))
    }
}
