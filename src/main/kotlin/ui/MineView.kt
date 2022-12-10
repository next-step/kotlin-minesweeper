package ui

import domain.DotState
import domain.NormalState

enum class MineView(private val ui: String) {
    NORMAL("C"),
    MINE("*");

    companion object {
        fun valueOf(dotState: DotState): String {
            if (dotState is NormalState) {
                return NORMAL.ui
            }
            return MINE.ui
        }
    }
}
