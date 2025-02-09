package tdd.minesweeper.domain.dsl

import tdd.minesweeper.domain.Board

@DslMarker
annotation class BoardDslMaker

fun board(block: BoardBuilder.() -> Unit): Board = BoardBuilder().apply(block).build()
