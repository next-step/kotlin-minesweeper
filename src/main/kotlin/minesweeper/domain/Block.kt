package minesweeper.domain

abstract class Block {

    abstract val mark: String
    abstract fun open()
}
