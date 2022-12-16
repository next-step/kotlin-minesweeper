package view

import domain.MineField

object OutputView {
    private const val MARK_MINE_BLOCK = "*"
    private const val MARK_NORMAL_BLOCK = "C"
    fun printGameStart(mineField: MineField) {
        println("지뢰찾기 게임 시작")
        printMineField(mineField)
    }

    private fun printMineField(mineField: MineField) {
        println(mineField.blocks.map { getBlockMark(it.isMine()) })
    }

    private fun getBlockMark(isMine: Boolean): String {
        return if (isMine) MARK_MINE_BLOCK else MARK_NORMAL_BLOCK
    }
}
