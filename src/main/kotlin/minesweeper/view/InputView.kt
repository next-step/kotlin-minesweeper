package minesweeper.view

import minesweeper.domain.MinePosition
import minesweeper.domain.Position

object InputView {

    private const val SEPERATOR = ","

    fun inputDataFromConsole(type: InputType): String {
        return when (type) {
            InputType.HEIGHT -> inputData("높이를 입력하세요.")
            InputType.WIDTH -> inputData("너비를 입력하세요.")
            InputType.MINE_COUNT -> inputData("지뢰는 몇 개인가요?")
        }
    }

    private fun inputData(typeMessage: String): String {
        println(typeMessage)
        val inputData = readln()
        println()
        return inputData
    }

    fun inputOpenPosition(): MinePosition {
        print("open :")
        val positionStrings = readln().split(SEPERATOR)
        if (positionStrings.size != 2) {
            throw IllegalArgumentException("위치는 두개의 숫자이어야함 ")
        }
        val positions = positionStrings.map {
            Position.of(it)
        }
        return MinePosition(positions)
    }
}
