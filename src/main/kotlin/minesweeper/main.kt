package minesweeper

import minesweeper.domain.MineSweeper
import minesweeper.presentation.InputReceiver
import minesweeper.presentation.UI

fun main() {
    MineSweeper(InputReceiver, UI).run()
}
