package com.sunmoon.helper.modules.search

/**
 * Created by SunMoon on 2017/8/13.
 */
class SearchViewModel(keyword:String) {
    var baseUrl = "https://www.baidu.com/s?word="
    val url:String = baseUrl + keyword

}