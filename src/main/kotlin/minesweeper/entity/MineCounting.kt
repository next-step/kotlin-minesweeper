package minesweeper.entity

class MineCounting(private val minedMap: List<List<MapElement>>, private val mapInfo: MapInformation) {
    val dx = listOf<Int>(0, 1, 0, -1)
    val dy = listOf<Int>(-1, 0, 1, 0)

    fun searchMap(): List<List<MapElement>> {
        return minedMap.mapIndexed { rowIndex: Int, row: List<MapElement> ->
            List(row.size) { colIndex -> searchAllDirection(rowIndex, colIndex) }
        }
    }

    fun searchAllDirection(curX: Int, curY: Int): MapElement {
        var countOfMines = 0
        if (minedMap[curX][curY] == MapElement.MINE) return MapElement.MINE
        for (idx in 0..3) {
            if (!isValid(curX + dx[idx], curY + dy[idx])) continue
            if (minedMap[curX + dx[idx]][curY + dy[idx]] == MapElement.MINE) {
                countOfMines += 1
            }
        }
        return MapElement.find(countOfMines)
    }

    fun isValid(curX: Int, curY: Int): Boolean {
        return (curX >= 0 && curX < mapInfo.height && curY >= 0 && curY < mapInfo.width)
    }
}