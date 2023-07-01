package next.step.minesweeper.domain.mine

import next.step.minesweeper.domain.board.BoardHeight
import next.step.minesweeper.domain.board.BoardWidth

data class MinePosition(val x: Int, val y: Int) {
    init {
        require(x >= BoardWidth.MIN_WIDTH) { "위치 x는 ${BoardWidth.MIN_WIDTH} 보다 작을 수 없습니다." }
        require(y >= BoardHeight.MIN_HEIGHT) { "위치 y는 ${BoardHeight.MIN_HEIGHT} 보다 작을 수 없습니다." }
    }
}
