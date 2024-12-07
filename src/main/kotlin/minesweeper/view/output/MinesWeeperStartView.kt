package minesweeper.view.output

import minesweeper.view.model.BoardViewModel

object MinesWeeperStartView {
    private const val MINE_SIGNATURE = "*"

    fun print(model: BoardViewModel) {
        println("지뢰찾기 게임 시작")

        model.board.forEach { rows ->
            println(
                rows.joinToString(" ") { point ->
                    if (point.isMine) MINE_SIGNATURE else point.aroundMineCount.toString()
                },
            )
        }
    }
}
