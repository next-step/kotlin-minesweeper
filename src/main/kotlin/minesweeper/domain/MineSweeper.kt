package minesweeper.domain

object MineSweeper {
    fun sweepe(map: List<List<Cell>>): List<List<Cell>> {
        for (x in map.indices) {
            for (y in map[x].indices) {
                if (map[x][y].isMineCell) {
                    map.getOrNull(x)?.getOrNull(y - 1)?.let { it.mineCountAround++ }
                    map.getOrNull(x)?.getOrNull(y + 1)?.let { it.mineCountAround++ }
                    map.getOrNull(x - 1)?.getOrNull(y)?.let { it.mineCountAround++ }
                    map.getOrNull(x + 1)?.getOrNull(y)?.let { it.mineCountAround++ }
                }
            }
        }

        return map
    }
}
