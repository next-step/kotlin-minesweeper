package minesweeper.domain.cell

import minesweeper.domain.MineCount

sealed interface Dot

data class Land(val mineCount: MineCount) : Dot

object Mine : Dot
