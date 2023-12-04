package minesweeper.domain

import minesweeper.domain.mine.MineSweeperShape

class MineSweeperWidth(
    private val widthList: List<String>,
) : List<String> by widthList {
    companion object {
        fun newInstance(widthSize: Int, mineSweeperShape: MineSweeperShape = MineSweeperShape.NUMBER): MineSweeperWidth {
            return (1..widthSize).map {
                mineSweeperShape.shape
            }.toMineSweeperWidth()
        }
    }
}

fun List<String>.toMineSweeperWidth() = MineSweeperWidth(this)
