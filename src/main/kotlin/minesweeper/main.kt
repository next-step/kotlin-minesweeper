package minesweeper

import minesweeper.domain.MotherCells
import minesweeper.view.BoardView
import minesweeper.view.UserInput

fun main() {
    val height = UserInput.Int("높이를 입력하세요.").answer()
    val width = UserInput.Int("너비를 입력하세요.").answer()
    val bombCount = UserInput.Int("지뢰는 몇 개인가요?").answer()

    val cells = MotherCells(width, height).cells(bombCount)
    BoardView(cells).show()
}
