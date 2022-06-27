package minesweeper

import minesweeper.domain.MineSweeper
import minesweeper.presentation.InputReceiver
import minesweeper.presentation.UI

fun main() {
    MineSweeper(
        InputReceiver::receiveBoardSize,
        InputReceiver::receiveMineCount,
        InputReceiver::receiveOpenCoordinate,
        UI::drawMessage,
        UI::drawBoard
    ).run()
}
