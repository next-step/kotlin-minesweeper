package minesweeper.domain.field

sealed interface Dot

object Mine : Dot

object NonMine : Dot
