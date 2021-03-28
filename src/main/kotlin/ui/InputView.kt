package ui

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
}
