package me.akherbouch.wsg.model

data class Line (
        val row : Int,
        val col : Int,
        val length: Int,
        val dir : Direction
)