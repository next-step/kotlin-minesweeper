package minesweeper.domain

import minesweeper.domain.flag.Flag

data class Block(val coordinate: Coordinate, val flag: Flag)
