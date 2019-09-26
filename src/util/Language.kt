package me.akherbouch.wsg.util

enum class Language(val abc : List<String>) {
    AR(listOf("ي","و","ه","ن","م","ل","ك","ق","ف","غ","ع","ظ","ط","ض","ص","ش","س","ز","ر","ذ","د","خ","ح","ج","ث","ة","ت","ب","أ","ا")),
    EN(listOf("a","b","c","d","e","f","g","h","i","k","l","m","n","o","p","q","r","s","t","v","x","y","z")),
    FR(listOf("a","b","c","d","e","f","g","h","i","k","l","m","n","o","p","q","r","s","t","v","x","y","z","à","è","ù","é","â","ê","î","ô","û","ç")),
    ES(listOf("a","b","c","d","e","f","g","h","i","k","l","m","n","o","p","q","r","s","t","v","x","y","z","ñ"));
    companion object {
        fun fromString (lg : String): Language {
            return when (lg.toUpperCase()) {
                "AR" -> AR
                "ES" -> ES
                "FR" -> FR
                else -> EN
            }
        }
    }
}