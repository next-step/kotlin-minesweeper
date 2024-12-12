package minesweeper.domain

class Mines(
    generator: MineGenerator,
    height: Height,
    width: Width,
    count: MineCount,
) {
    val elements: List<Mine> = generator.generate(height, width, count)

    operator fun contains(other: Mine): Boolean = elements.contains(other)
}
