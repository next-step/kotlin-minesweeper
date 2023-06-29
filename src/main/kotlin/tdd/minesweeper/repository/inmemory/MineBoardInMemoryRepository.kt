package tdd.minesweeper.repository.inmemory

import tdd.minesweeper.domain.MineBoard
import tdd.minesweeper.repository.MineBoardRepository

object MineBoardInMemoryRepository : MineBoardRepository {
    private var sequence: Int = 1
    private val store: MutableMap<Int, MineBoard> = mutableMapOf()

    override fun save(entity: MineBoard): Int {
        entity.getId()?.run {
            store[this] = entity
            return this
        } ?: run {
            val id = nextSequence()
            entity.updateId(id)

            store[id] = entity
            return id
        }
    }

    private fun nextSequence(): Int = sequence++

    override fun find(id: Int): MineBoard = store[id]
        ?: throw NoSuchElementException("유효한 지뢰판을 찾을 수 없습니다. Input: $id")

    fun clear() {
        sequence = 1
        store.clear()
    }
}
