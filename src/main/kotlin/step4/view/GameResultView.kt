package step4.view

import step4.domain.state.Lose
import step4.domain.state.MinesweeperState
import step4.domain.state.Win

enum class GameResultView(
    val view: String,
) {
    WIN_VIEW("Win Game."),
    LOSE_VIEW("Lose Game."),
    ;

    companion object {
        fun from(state: MinesweeperState): GameResultView =
            when (state) {
                is Win -> WIN_VIEW
                is Lose -> LOSE_VIEW
                else -> throw IllegalArgumentException("뷰에서 지원하지 않는 게임 타입입니다.")
            }
    }
}
