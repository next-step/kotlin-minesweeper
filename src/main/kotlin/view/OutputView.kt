package view

import domain.Block
import domain.MineField

object OutputView {
    private const val MARK_MINE_BLOCK = "*"
    private const val MARK_NORMAL_BLOCK = "C"
    private const val SEPARATOR_ROW = "\n"
    private const val SEPARATOR_BLOCK = " "

    fun printGameStart(mineField: MineField) {
        println("지뢰찾기 게임 시작")
        printMineField(mineField)
    }

    private fun printMineField(mineField: MineField) {
        println(
            parseListAsBlockMark(mineField.blocks)
                .chunked(mineField.rectangle.width)
                .joinMineField()
        )
    }

    private fun parseListAsBlockMark(blocks: List<Block>): List<String> {
        return blocks.map { getBlockMark(it.isMine()) }
    }

    private fun List<List<String>>.joinMineField(): String {
        return this.joinToString(SEPARATOR_ROW) { it.joinToString(SEPARATOR_BLOCK) }
    }

    private fun getBlockMark(isMine: Boolean): String {
        return if (isMine) MARK_MINE_BLOCK else MARK_NORMAL_BLOCK
    }


}
