package ui

import domain.FieldInfo
import domain.Length
import domain.MinesweeperGame

fun main() {
    val inputView = InputView().also {
        it.show()
    }
    val minesweeperGame = MinesweeperGame(
        fieldInfo = FieldInfo(
            width = Length(inputView.width),
            height = Length(inputView.height)
        ),
        mineSize = inputView.mineSize
    )
    val minesweeperGameView = MinesweeperGameView()
    minesweeperGameView.printField(minesweeperGame)
}
