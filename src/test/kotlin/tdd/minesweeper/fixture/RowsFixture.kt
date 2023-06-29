package tdd.minesweeper.fixture

import tdd.minesweeper.domain.Row
import tdd.minesweeper.domain.Rows
import tdd.minesweeper.domain.SymbolPoint
import tdd.minesweeper.domain.type.SymbolType.MINE
import tdd.minesweeper.domain.type.SymbolType.ONE
import tdd.minesweeper.domain.type.SymbolType.TWO
import tdd.minesweeper.domain.type.SymbolType.ZERO

/**
 * 0 1 *
 * 1 2 2
 * 1 * 1
 */
fun create3x3Rows(): Rows =
    buildList {
        add(
            Row(
                listOf(
                    SymbolPoint(0, 0, ZERO), SymbolPoint(1, 0, ONE), SymbolPoint(2, 0, MINE)
                )
            )
        )
        add(
            Row(
                listOf(
                    SymbolPoint(0, 1, ONE), SymbolPoint(1, 1, TWO), SymbolPoint(2, 1, TWO)
                )
            )
        )
        add(
            Row(
                listOf(
                    SymbolPoint(0, 2, ONE), SymbolPoint(1, 2, MINE), SymbolPoint(2, 2, ONE)
                )
            )
        )
    }.let(::Rows)

/**
 * 1 1 1 1 1
 * 1 x 2 2 x
 * 1 2 x 2 1
 * 0 1 1 2 1
 * 0 0 1 1 x
 */
fun create5x5Rows(): Rows =
    buildList {
        add(
            Row(
                listOf(
                    SymbolPoint(0, 0, ONE), SymbolPoint(1, 0, ONE), SymbolPoint(2, 0, ONE), SymbolPoint(3, 0, ONE), SymbolPoint(4, 0, ONE)
                )
            )
        )
        add(
            Row(
                listOf(
                    SymbolPoint(0, 1, ONE), SymbolPoint(1, 1, MINE), SymbolPoint(2, 1, TWO), SymbolPoint(3, 1, TWO), SymbolPoint(4, 1, MINE)
                )
            )
        )
        add(
            Row(
                listOf(
                    SymbolPoint(0, 2, ONE), SymbolPoint(1, 2, TWO), SymbolPoint(2, 2, MINE), SymbolPoint(3, 2, TWO), SymbolPoint(4, 2, ONE)
                )
            )
        )
        add(
            Row(
                listOf(
                    SymbolPoint(0, 3, ZERO), SymbolPoint(1, 3, ONE), SymbolPoint(2, 3, ONE), SymbolPoint(3, 3, TWO), SymbolPoint(4, 3, ONE)
                )
            )
        )
        add(
            Row(
                listOf(
                    SymbolPoint(0, 4, ZERO), SymbolPoint(1, 4, ZERO), SymbolPoint(2, 4, ONE), SymbolPoint(3, 4, ONE), SymbolPoint(4, 4, MINE)
                )
            )
        )
    }.let(::Rows)
