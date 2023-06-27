package minesweeper_refactor.domain.block

import minesweeper_refactor.domain.board.MinesweeperBoard
import minesweeper_refactor.domain.coordinate.Coordinate
import minesweeper_refactor.domain.coordinate.EightDirectionsDecision

@JvmInline
value class Blocks(private val blocks: List<Block>) {

    fun toAroundCoordinateMap(): Map<Coordinate, Int> = blocks
        .flatMap { it.coordinate.toAroundDirections(aroundDecision = EightDirectionsDecision) }
        .groupingBy { it }
        .eachCount()

    fun toMinesweeperBoard(): MinesweeperBoard = blocks.associateBy { it.coordinate }
        .run(::MinesweeperBoard)

    operator fun plus(other: Blocks): Blocks = Blocks(blocks = blocks + other.blocks)
}
