package next.step.minesweeper.domain.mine

import next.step.minesweeper.domain.board.Board
import next.step.minesweeper.domain.board.BoardRow

data class MinePosition(val x: Int, val y: Int) {
    init {
        require(x >= BoardRow.BASE_X) { "위치 x는 ${BoardRow.BASE_X} 보다 작을 수 없습니다." }
        require(y >= Board.BASE_Y) { "위치 y는 ${Board.BASE_Y} 보다 작을 수 없습니다." }
    }
}
