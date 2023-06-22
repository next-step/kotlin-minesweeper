package minesweeper.application

import minesweeper.request.MineBoardCreateRequest
import minesweeper.strategy.MineBoardCreateStrategy

class MineSweeperService(
    private val mineBoardCreateStrategy: MineBoardCreateStrategy
) {

    fun createMineBoard(request: MineBoardCreateRequest) {
        TODO("지뢰 판 생성 로직 구현 필요 ")
    }
}
