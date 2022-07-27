package com.githubyss.mobile.common.ui.dialog.voice_select


object VoiceToneDataCenter {
    val voiceToneList = ArrayList<VoiceTone>()

    init {
        initData()
    }

    private fun initData() {
        voiceToneList.add(VoiceTone(0, "茉莉", "", VoiceToneSelectState.YES))
        voiceToneList.add(VoiceTone(1, "蜜桃", "", VoiceToneSelectState.NO))
        voiceToneList.add(VoiceTone(2, "丁丁", "", VoiceToneSelectState.NO))
        voiceToneList.add(VoiceTone(3, "翠喜", "", VoiceToneSelectState.NO))
        voiceToneList.add(VoiceTone(4, "小二", "", VoiceToneSelectState.NO))
    }
}