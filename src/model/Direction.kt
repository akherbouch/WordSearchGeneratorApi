package me.akherbouch.wsg.model

enum class Direction (val row : Int, val col : Int) {
    HORIZONTAL(0,1),
    VERTICAL(1,0),
    DIAGONAL(1,1),
    ANTI_DIAGONAL(1,-1)
}