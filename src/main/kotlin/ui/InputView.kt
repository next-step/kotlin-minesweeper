package ui

import domain.position.Position
import model.GameData

object InputView {

    fun askInfo(): GameData {
        val height = askHeight()
        val width = askWidth()
        val mineNumber = askMineNumber()

        return GameData(width, height, mineNumber)
    }

    private fun askHeight(): Int {
        println("높이를 입력하세요.")
        val height = readLine()?.toInt() ?: throw IllegalArgumentException("잘못된 입력 값입니다.")
        println()
        return height
    }

    private fun askWidth(): Int {
        println("너비를 입력하세요.")
        val width = readLine()?.toInt() ?: throw IllegalArgumentException("잘못된 입력 값입니다.")
        println()
        return width
    }

    private fun askMineNumber(): Int {
        println("지뢰는 몇 개인가요?")
        val mineNumber = readLine()?.toInt() ?: throw IllegalArgumentException("잘못된 입력 값입니다.")
        println()
        return mineNumber
    }

    fun askOpenPosition(): Position {
        print("Open: ")
        val rowAndCol =
            readLine()?.split(",")?.map { it.trim().toIntOrNull() ?: throw IllegalArgumentException("잘못된 입력 값입니다.") }
        require(rowAndCol != null && rowAndCol.size == 2) { "잘못된 입력 값입니다." }

        val row = rowAndCol[0]
        val col = rowAndCol[1]
        return Position(row, col)
    }
}
