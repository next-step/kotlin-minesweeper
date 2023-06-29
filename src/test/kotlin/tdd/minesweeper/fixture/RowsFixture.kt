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
fun create3x3Rows() : Rows =
    buildList {
        add(Row(listOf(
            SymbolPoint(0,0, ZERO), SymbolPoint(1,0, ONE), SymbolPoint(2,0, MINE)
        )))
        add(Row(listOf(
            SymbolPoint(0,1, ONE), SymbolPoint(1,1, TWO), SymbolPoint(2,1, TWO)
        )))
        add(Row(listOf(
            SymbolPoint(0,2, ONE), SymbolPoint(1,2, MINE), SymbolPoint(2,2, ONE)
        )))
    }.let(::Rows)
