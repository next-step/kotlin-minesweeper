package minesweeper.view

import minesweeper.domain.MineBoardStatus
import minesweeper.domain.MineBoardStatus.LOSE
import minesweeper.domain.MineBoardStatus.WIN

enum class GameResultView(
    val mineBoardStatus: MineBoardStatus,
    val view: String,
) {
    WIN_VIEW(WIN, "Win Game."),
    LOSE_VIEW(LOSE, "Lose Game."),
    ;

    companion object {
        fun from(mineBoardStatus: MineBoardStatus): GameResultView =
            values().firstOrNull { it.mineBoardStatus == mineBoardStatus }
                ?: throw IllegalArgumentException("뷰에서 지원하지 않는 게임 결과 타입입니다.")
    }
}
