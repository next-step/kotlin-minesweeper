package minesweeper.domain

object MineSweeper {
    // TODO 리팩토링 (STEP4) - 연산 로직 개선, Clone
    fun MineMap.sweep(): MineMap {
        val map = this.map()
        mineCellPositions(map)
            .forEach { (x, y) ->
                map.getOrNull(x)?.getOrNull(y - 1)?.apply { if (this is NumberCell) this.mineCountAround++ }
                map.getOrNull(x)?.getOrNull(y + 1)?.apply { if (this is NumberCell) this.mineCountAround++ }
                map.getOrNull(x - 1)?.getOrNull(y)?.apply { if (this is NumberCell) this.mineCountAround++ }
                map.getOrNull(x + 1)?.getOrNull(y)?.apply { if (this is NumberCell) this.mineCountAround++ }
                map.getOrNull(x - 1)?.getOrNull(y - 1)?.apply { if (this is NumberCell) this.mineCountAround++ }
                map.getOrNull(x + 1)?.getOrNull(y - 1)?.apply { if (this is NumberCell) this.mineCountAround++ }
                map.getOrNull(x - 1)?.getOrNull(y + 1)?.apply { if (this is NumberCell) this.mineCountAround++ }
                map.getOrNull(x + 1)?.getOrNull(y + 1)?.apply { if (this is NumberCell) this.mineCountAround++ }
            }
        return MineMap(map)
    }

    private fun mineCellPositions(map: List<List<Cell>>): List<Pair<Int, Int>> {
        return map.flatMapIndexed { x: Int, cells: List<Cell> ->
            cells.mapIndexedNotNull { y: Int, cell: Cell -> if (cell is MineCell) Pair(x, y) else null }
        }
    }
}
