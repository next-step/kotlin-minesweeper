package minesweeper.domain

sealed interface Block {
    companion object {
        fun empty(): Block = Empty
    }
}

object Mine : Block
object Empty : Block
