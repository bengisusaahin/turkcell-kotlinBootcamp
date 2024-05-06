package com.bengisusahin.vize02_calisma.services

import android.util.Log
import com.bengisusahin.vize02_calisma.models.Currency
import org.jsoup.Jsoup
import org.jsoup.parser.Parser

class XmlService {
    fun xmlLoad() : List<Currency> {
        val list = mutableListOf<Currency>()
        try {
            val url = "https://www.tcmb.gov.tr/kurlar/today.xml"
            val stData = Jsoup.connect(url).timeout(15000).ignoreContentType(true).get().toString()
            val doc = Jsoup.parse(stData, Parser.xmlParser())
            val elements = doc.getElementsByTag("Currency")
            for (item in elements) {
                val CurrencyName = item.getElementsByTag("CurrencyName").text()
                val ForexBuying = item.getElementsByTag("ForexBuying").text()
                val c = Currency(CurrencyName, ForexBuying)
                list.add(c)
                Log.d("xml", list.toString())
            }
        }catch (ex: Exception) {
            Log.e("xmlLoad", ex.message.toString() )
        }
        return list
    }
}