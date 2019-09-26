package me.akherbouch.wsg.core


fun String.getLetter(i: Int, isReversed: Boolean): String
        = this[if (isReversed) length-i-1 else i].toString()