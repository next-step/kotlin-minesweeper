package minesweeper.view.output

import minesweeper.view.dto.BoardDto

object MinesWeeperStartView {
    private const val LAND_SIGNATURE = "C"
    private const val MINE_SIGNATURE = "*"

    fun print(dto: BoardDto) {
        println("지뢰찾기 게임 시작")
        dto.board.forEach { rows ->
            println(rows.joinToString(" ") { isMine -> if (isMine) MINE_SIGNATURE else LAND_SIGNATURE })
        }
    }
}
