package view

import dto.MineInputDto

object InputView {
    private const val INPUT_HEIGHT = "높이를 입력하세요."
    private const val INPUT_WIDTH = "너비를 입력하세요."
    private const val INPUT_MINE_CNT = "지뢰는 몇 개인가요?"

    fun readMineSweeperInput(): MineInputDto {
        println(INPUT_HEIGHT)
        val height = readInt()
        println()
        println(INPUT_WIDTH)
        val width = readInt()
        println()
        println(INPUT_MINE_CNT)
        val mineCnt = readInt()
        println()
        return MineInputDto(height, width, mineCnt)
    }

    private fun readInt(): Int {
        return readln().toInt()
    }
}
