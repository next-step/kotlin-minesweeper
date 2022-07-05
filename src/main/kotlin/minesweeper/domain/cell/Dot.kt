package minesweeper.domain.cell

sealed interface Dot

object Land : Dot

object Mine : Dot
