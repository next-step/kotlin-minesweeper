package minesweeper.domain.board

import minesweeper.domain.component.Components

interface Board : Components {
    val height: Int
    val width: Int
}
