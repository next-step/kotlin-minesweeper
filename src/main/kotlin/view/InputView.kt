package view

import domain.BoardSize


object InputView {
    fun requestBoardSize(): BoardSize {
        val height = requestHeight()
        val width = requestWidth()

        return BoardSize(width, height)
    }

    private fun requestHeight(): Int {
        println("높이를 입력하세요.")
        return readln().toInt()
    }

    private fun requestWidth(): Int {
        println("\n너비를 입력하세요.")
        return readln().toInt()
    }

    fun requestCountOfMine(): Int {
        println("\n지뢰는 몇 개인가요?")

        return readln().toInt()
    }
}