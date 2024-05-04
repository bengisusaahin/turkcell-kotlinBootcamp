package com.bengisusahin.days_7.services

import android.util.Log
import com.bengisusahin.days_7.models.CD
import com.bengisusahin.days_7.models.Currency
import org.jsoup.Jsoup
import org.jsoup.parser.Parser
import java.lang.Exception

class XmlService {

    // Genelde parametre almazlar
    fun xmlLoad(): MutableList<Currency> {

        val currencyDataList = mutableListOf<Currency>()

        try {
            val url = "https://www.tcmb.gov.tr/kurlar/today.xml"
            // timeout -> max 15 saniye veri gelmezse iptal ediyor
            // ignoreContentType -> yalnızca string mi geliyordu verilerimiz xml'de her türlü veri tipi gelsin diye ekliyoruz
            val stData = Jsoup.connect(url).timeout(15000).ignoreContentType(true).get().toString()
            val doc = Jsoup.parse(stData, Parser.xmlParser())
            val elements = doc.getElementsByTag("Currency")

            for (item in elements){
                val currencyName = item.getElementsByTag("CurrencyName").text()
                //Log.d("CurrencyName", currencyName)
                val forexBuying = item.getElementsByTag("ForexBuying").text()
                val forexSelling = item.getElementsByTag("ForexSelling").text()
                val banknoteBuying = item.getElementsByTag("BanknoteBuying").text()
                val banknoteSelling = item.getElementsByTag("BanknoteSelling").text()

                val currencyData = Currency(currencyName, forexBuying, forexSelling, banknoteBuying, banknoteSelling)
                currencyDataList.add(currencyData)
                //Log.d("CurrencyName", currencyName)
            }
            //Log.d("xml", stData)
            Log.d("xmlCurrency", currencyDataList.toString())

        }catch (ex: Exception){
            Log.e("xmlLoad", ex.message.toString() )
        }
        return currencyDataList
    }

    fun cdLoad(): MutableList<CD>{

        val cdDataList = mutableListOf<CD>()

        try {
            val url = "https://www.w3schools.com/xml/cd_catalog.xml"
            val cdData = Jsoup.connect(url).timeout(15000).ignoreContentType(true).get().toString()
            val doc = Jsoup.parse(cdData, Parser.xmlParser())
            val elements = doc.getElementsByTag("CD")

            for (item in elements){
                val title = item.getElementsByTag("TITLE").text()
                val artist = item.getElementsByTag("ARTIST").text()
                val country = item.getElementsByTag("COUNTRY").text()
                val company = item.getElementsByTag("COMPANY").text()
                val price = item.getElementsByTag("PRICE").text()
                val year = item.getElementsByTag("YEAR").text()

                val cdDataElements = CD(title,artist,country,company,price,year)
                cdDataList.add(cdDataElements)
            }
            Log.d("xmlCd", cdDataList.toString())
        }catch (ex: Exception){
            Log.e("CD_xmlLoad", ex.message.toString() )
        }
        return cdDataList
    }
}