package minesweeper.domain

@JvmInline
value class Mines(val elements: List<Mine>) {
    operator fun contains(other: Mine): Boolean = elements.contains(other)
}
