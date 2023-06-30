package tdd.minesweeper.repository

import tdd.minesweeper.domain.MineBoard

interface MineBoardRepository {
    fun save(entity: MineBoard): Int
    fun find(id: Int): MineBoard
}
