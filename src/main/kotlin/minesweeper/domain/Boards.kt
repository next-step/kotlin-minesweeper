package minesweeper.domain

sealed interface Board {
    val cells: Map<Coordinate, Cell>
}

class PlayableBoard(
    override val cells: Map<Coordinate, Cell>,
) : Board {
    init {
        require(cells.isNotEmpty()) {
            "빈 판을 생성할 수 없습니다."
        }
    }

    fun get(
        y: Int,
        x: Int,
    ): Cell? = cells[Coordinate(y, x)]

    fun countMines(
        y: Int,
        x: Int,
    ): Int =
        Coordinate(y, x)
            .neighbors
            .count { cells[it] is MinedCell }

    fun open(
        y: Int,
        x: Int,
    ): Board {
        requireCellExists(y, x)
        checkCellIsNotAlreadyOpened(y, x)

        if (get(y, x) is MinedCell) {
            return MineDetonatedBoard(
                cells.replacing(
                    Coordinate(y, x),
                    DetonatedMineCell,
                ),
            )
        }

        val newCells = open(Coordinate(y, x))

        return if (playerWins(newCells)) {
            PlayerWonBoard(newCells)
        } else {
            PlayableBoard(newCells)
        }
    }

    private fun open(coordinate: Coordinate): Map<Coordinate, Cell> {
        check(cells[coordinate] is ClosedEmptyCell)
        val newCells = cells.toMutableMap()
        val queue = ArrayDeque<Coordinate>()

        newCells[coordinate] = OpenedEmptyCell
        if (isNoNeighboringMines(coordinate)) {
            queue.add(coordinate)
        }

        while (queue.isNotEmpty()) {
            val current = queue.removeFirst()

            for (neighbor in current.neighbors) {
                if (newCells[neighbor] !is ClosedEmptyCell) {
                    continue
                }

                newCells[neighbor] = OpenedEmptyCell
                if (isNoNeighboringMines(neighbor)) {
                    queue.add(neighbor)
                }
            }
        }
        return newCells.toMap()
    }

    private fun isNoNeighboringMines(coordinate: Coordinate) = countMines(y = coordinate.y, x = coordinate.x) == 0

    private fun requireCellExists(
        y: Int,
        x: Int,
    ) {
        require(cells.containsKey(Coordinate(y, x))) {
            "해당 좌표에 칸이 없습니다."
        }
    }

    private fun checkCellIsNotAlreadyOpened(
        y: Int,
        x: Int,
    ) {
        check(get(y, x) !is OpenedEmptyCell) { "해당 좌표에 칸이 없거나 이미 열린 칸입니다." }
    }

    private fun playerWins(newCells: Map<Coordinate, Cell>) =
        newCells
            .values
            .count { it is ClosedEmptyCell } == 0
}

sealed interface CompletedBoard : Board

class PlayerWonBoard(
    override val cells: Map<Coordinate, Cell>,
) : CompletedBoard {
    init {
        require(!cells.values.any { it is ClosedEmptyCell }) {
            "승리한 게임은 지뢰를 제외한 모든 칸을 열어야 합니다."
        }
    }
}

class MineDetonatedBoard(
    override val cells: Map<Coordinate, Cell>,
) : CompletedBoard {
    init {
        require(cells.values.any { it is DetonatedMineCell }) {
            "폭발한 지뢰가 있어야 합니다."
        }
    }
}

internal fun <K, V> Map<K, V>.replacing(
    key: K,
    value: V,
): Map<K, V> = toMutableMap().apply { set(key, value) }
