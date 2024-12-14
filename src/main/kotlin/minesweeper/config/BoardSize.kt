package minesweeper.config

import minesweeper.domain.Height
import minesweeper.domain.Width

data class BoardSize(
    val height: Height,
    val width: Width,
)
