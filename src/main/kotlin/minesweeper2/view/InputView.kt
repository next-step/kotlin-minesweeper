package minesweeper2.view

import minesweeper2.model.PositionLocation

const val POSITION_SPLIT_SYMBOL = ","

fun enterCols(): Int {
    println("높이를 입력하세요.")
    return readln().toInt()
}

fun enterRows(): Int {
    println("너비를 입력하세요.")
    return readln().toInt()
}

fun enterMineCount(): Int {
    println("지뢰는 몇 개인가요?")
    return readln().toInt()
}

fun enterOpenPosition(): PositionLocation {
    print("open: ")
    val positions = readln().split(POSITION_SPLIT_SYMBOL).map { it.trim().toInt() }
    return PositionLocation(positions[0], positions[1])
}
