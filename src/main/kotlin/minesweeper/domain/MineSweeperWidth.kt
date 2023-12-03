package minesweeper.domain

import minesweeper.domain.mine.MineShape

class MineSweeperWidth(
    private val widthList: List<String>,
) : List<String> by widthList {

    companion object {

        fun newInstance(widthSize: Int, mineShape: MineShape): MineSweeperWidth {
            return (1..widthSize).map {
                mineShape.shape
            }.toMineSweeperWidth()
        }
    }
}

fun List<String>.toMineSweeperWidth() = MineSweeperWidth(this)
