package me.akherbouch.wsg.util

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import me.akherbouch.wsg.module
import java.lang.Integer.max
import java.lang.Integer.min

object RandomWordsGenerator {

    private val moshi = Moshi.Builder().build()
    private val listType = Types.newParameterizedType(MutableList::class.java, String::class.java)
    private val adapter: JsonAdapter<MutableList<String>> = moshi.adapter(listType)
    private val arJson = this::class.java.classLoader.getResource("words/ar.json")!!.readText()
    private val enJson = this::class.java.classLoader.getResource("words/en.json")!!.readText()
    private val frJson = this::class.java.classLoader.getResource("words/fr.json")!!.readText()
    private val esJson = this::class.java.classLoader.getResource("words/es.json")!!.readText()


    fun getWords(num : Int, language: Language) : List<String> {
        val allWords : MutableList<String> = when (language) {
            Language.AR -> adapter.fromJson(arJson) ?: mutableListOf()
            Language.EN -> adapter.fromJson(enJson) ?: mutableListOf()
            Language.ES -> adapter.fromJson(esJson) ?: mutableListOf()
            Language.FR -> adapter.fromJson(frJson) ?: mutableListOf()
        }
        val randomWords = mutableListOf<String>()
        var n = min(allWords.size,num)
        while (n-- > 0) {
            val r = java.util.Random().nextInt(allWords.size)
            randomWords += allWords.removeAt(r)
        }
        return randomWords
    }

}