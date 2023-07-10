package minesweeper.domain

interface TestCell {
    val isOpen: Boolean
}

class Normal(override val isOpen: Boolean = false) : TestCell

class Mine(override val isOpen: Boolean = false) : TestCell
