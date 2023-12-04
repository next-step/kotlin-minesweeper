package minesweeper.domain.mine

import minesweeper.domain.MineSweeperBoard

enum class MineCheckStrategy(val width: Int, val height: Int) {
    ELEVEN(-1, -1),
    TWELVE(0, -1),
    ONE(+1, -1),
    THREE(1, 0),
    FIVE(1, 1),
    SIX(0, +1),
    SEVEN(-1, +1),
    NINE(-1, 0);

    companion object {
        fun mineMatchCount(mineSweeperBoard: MineSweeperBoard, widthIndex: Int, heightIndex: Int): Int {
            return MineCheckStrategy.values().count {
                kotlin.runCatching {
                    MineSweeperShape.isMine(mineSweeperBoard[widthIndex + it.width][heightIndex + it.height])
                }.getOrNull() ?: false
            }
        }
    }
}
