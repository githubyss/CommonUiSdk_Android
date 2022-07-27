package com.githubyss.mobile.common.ui.dialog.voice_select

import com.githubyss.mobile.common.ui.R
import com.githubyss.mobile.common.ui.databinding.ComuiItemVoiceToneBinding


/**
 * VoiceToneListAdapter
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2022/07/26 13:50:21
 */
class VoiceToneListAdapter : BaseBindingRecyclerViewAdapter<ComuiItemVoiceToneBinding, VoiceTone>(R.layout.comui_item_voice_tone) {
    override fun updateDataBinding(binding: ComuiItemVoiceToneBinding, data: VoiceTone) {
        binding.voiceTone = data
        binding.onItemClickListener = onItemClickListener
    }
}
