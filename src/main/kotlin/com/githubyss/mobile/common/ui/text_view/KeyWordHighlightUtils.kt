package com.githubyss.mobile.common.ui.text_view

import android.text.Html
import android.text.Spanned
import com.githubyss.mobile.common.kit.util.ResourceUtils
import com.githubyss.mobile.common.ui.R
import java.util.*
import java.util.regex.Matcher
import java.util.regex.Pattern


/**
 * KeyWordHighlightUtils
 * 关键字变色类
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/04/28 10:01:30
 */
object KeyWordHighlightUtils {
    
    /** ********** ********** ********** Properties ********** ********** ********** */
    
    private val TAG: String = KeyWordHighlightUtils::class.java.simpleName
    private val defaultColorId: Int = R.color.comui_color_3399ff
    
    
    /** ********** ********** ********** Functions ********** ********** ********** */
    
    /**
     * 关键字高亮处理
     *
     * @param text     全部文字（String）
     * @param keywords 关键字集合（vararg）
     * @param color    颜色值
     * @param vague    是否为模糊匹配，单个字节逐一匹配
     * @return 关键字高亮处理后的全部文字（Spanned）
     */
    fun matcherText(text: String?, vararg keywords: String?, color: Int? = null, vague: Boolean = false): Spanned? {
        text ?: return null
        keywords ?: return Html.fromHtml(text)
        if (keywords.isEmpty()) return Html.fromHtml(text)
        
        val color = color ?: ResourceUtils.getColor(defaultColorId)
        val keywordSet: MutableSet<String?> = HashSet<String?>()
        for (key in keywords) {
            keywordSet.add(key)
        }
        return Html.fromHtml(getHtmlText(text, keywordSet, color, vague))
    }
    
    /**
     * 关键字高亮处理
     *
     * @param text     全部文字（String）
     * @param keywords 关键字集合（List）
     * @param color    颜色值
     * @param vague    是否为模糊匹配，单个字节逐一匹配
     * @return 关键字高亮处理后的全部文字（Spanned）
     */
    fun matcherText(text: String?, keywords: List<String?>?, color: Int? = null, vague: Boolean = false): Spanned? {
        text ?: return null
        keywords ?: return Html.fromHtml(text)
        if (keywords.isEmpty()) return Html.fromHtml(text)
        
        val color = color ?: ResourceUtils.getColor(defaultColorId)
        val keywordSet: MutableSet<String?> = HashSet<String?>(keywords)
        return Html.fromHtml(getHtmlText(text, keywordSet, color, vague))
    }
    
    /**
     * 获取Html格式化的文字
     *
     * @param text     全部文字
     * @param keywords 关键字集合（Set）
     * @param color    颜色值
     * @param vague    是否为模糊匹配，单个字节逐一匹配
     * @return Html格式化的文字（String）
     */
    private fun getHtmlText(text: String?, keywords: Set<String?>?, color: Int? = null, vague: Boolean = false): String? {
        text ?: return text
        keywords ?: return text
        if (keywords.isEmpty()) return text
        
        val color = color ?: ResourceUtils.getColor(defaultColorId)
        val sb = StringBuffer()
        val matcher = Pattern.compile(getKeyMatchRegex(keywords, vague), Pattern.CASE_INSENSITIVE or Pattern.UNICODE_CASE or Pattern.COMMENTS)
            .matcher(text)
        while (matcher.find()) {
            matcher.appendReplacement(sb, getColorHtmlStr(matcher, color))
        }
        matcher.appendTail(sb)
        val ret = sb.toString()
        // LogUtils.d(TAG, "KeyWordHighlightUtils >>> getHtmlText >> ret: $ret")
        return ret
    }
    
    /**
     * 关键字高亮处理
     *
     * @param text            全部文字（String）
     * @param keywordColorMap <关键字-颜色值>映射（HashMap），用于一段话有多个、颜色要求不同的关键字，Key是关键字（String），Value是颜色值（Int）
     * @param vague           是否为模糊匹配，单个字节逐一匹配
     * @return 关键字高亮处理后的全部文字（Spanned）
     */
    fun matcherText(text: String?, keywordColorMap: Map<String?, Int?>?, vague: Boolean = false): Spanned? {
        text ?: return null
        keywordColorMap ?: return Html.fromHtml(text)
        if (keywordColorMap.isEmpty()) return Html.fromHtml(text)
        
        val keywordSet = keywordColorMap.keys
        
        val sb = StringBuffer()
        val matcher = Pattern.compile(getKeyMatchRegex(keywordSet, vague), Pattern.CASE_INSENSITIVE or Pattern.UNICODE_CASE or Pattern.COMMENTS)
            .matcher(text)
        while (matcher.find()) {
            for (key in keywordSet) {
                if (key.equals(matcher.group(), ignoreCase = true)) {
                    val color = keywordColorMap[key]
                    if (color != null) {
                        matcher.appendReplacement(sb, getColorHtmlStr(matcher, color))
                    }
                }
            }
        }
        matcher.appendTail(sb)
        val htmlText = sb.toString()
        // LogUtils.d(TAG, "KeyWordHighlightUtils >>> matcherText >> htmlText: $htmlText")
        return Html.fromHtml(htmlText)
    }
    
    /**
     * 获取正则表达式
     *
     * @param keywords 关键字唯一不重复列表
     * @param vague    是否为模糊匹配，单个字节逐一匹配
     * @return 关键字匹配正则表达式（String）
     */
    private fun getKeyMatchRegex(keywords: Set<String?>?, vague: Boolean = false): String {
        keywords ?: return ""
        if (keywords.isEmpty()) return ""
        
        val reg = StringBuffer()
        if (vague) {
            for (s in keywords) {
                reg.append("[")
                reg.append(s)
                reg.append("]|")
            }
        } else {
            for (s in keywords) {
                reg.append("(")
                reg.append(s)
                reg.append(")|")
            }
        }
        val ret = reg.deleteCharAt(reg.length - 1)
            .toString()
        // LogUtils.d(TAG, "KeyWordHighlightUtils >>> getKeyMatchRegex >> ret: $ret")
        return ret
    }
    
    /**
     * 获取Html格式化的颜色
     *
     * @param matcher 正则匹配
     * @param color   颜色值
     * @return Html格式化的颜色（String）
     */
    private fun getColorHtmlStr(matcher: Matcher, color: Int? = null): String {
        val color = color ?: ResourceUtils.getColor(defaultColorId)
        val colorStr = String.format("#%06X", 0xFFFFFF and color)
        val ret = "<font color=\"${colorStr}\">${matcher.group()}</font>"
        // LogUtils.d(TAG, "KeyWordHighlightUtils >>> getColorHtmlStr >> ret: $ret")
        return ret
    }
}
