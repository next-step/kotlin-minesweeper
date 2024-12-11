package ui

import GameBoard

fun main() {
    println("높이를 입력하세요.")
    val rowLength = readln().toInt()
    println()

    println("너비를 입력하세요.")
    val columnLength = readln().toInt()
    println()

    println("지뢰는 몇 개인가요?")
    val countOfLandmine = readln().toInt()
    println()

    println("지뢰찾기 게임 시작")
    val gameBoard = GameBoard.of(rowLength = rowLength, columnLength = columnLength).plantMines(countOfLandmine)

    val rows = gameBoard.rows()
    for (row in rows) {
        println(row.display())
    }
}
