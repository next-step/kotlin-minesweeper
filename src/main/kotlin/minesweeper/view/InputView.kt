package minesweeper.view

import minesweeper.domain.Coordinate

object InputView {
    fun inputLength(): Int {
        println("높이를 입력하세요.")
        return changeInt(readLine())
    }

    fun inputWidth(): Int {
        println("너비를 입력하세요.")
        return changeInt(readLine())
    }

    fun inputMines(): Int {
        println("지뢰는 몇 개인가요?")
        return changeInt(readLine())
    }

    fun inputCoordinate(): Coordinate {
        print("open:")
        val inputValue = readLine() ?: throw IllegalArgumentException("null값을 입력하지 마세요")
        val list = inputValue.split(",").map { changeInt(it.trim()) }
        checkListSize(list)
        return Coordinate(list[0], list[1])
    }

    private fun changeInt(inputValue: String?): Int {
        if (inputValue.isNullOrBlank()) {
            throw IllegalArgumentException("공백값과 null값은 받을수없습니다.")
        }
        try {
            return inputValue.toInt()
        } catch (e: Exception) {
            throw IllegalArgumentException("숫자 이외의 값: $inputValue")
        }
    }

    private fun checkListSize(list: List<Int>) {
        if (list.size != COORDINATE_SIZE) {
            throw IllegalArgumentException("리스트 크기가 ${list.size}입니다. 좌표는 x, y값만 입력할 수 있습니다.")
        }
    }

    const val COORDINATE_SIZE = 2
}
