package domain

class MinesWeeper(val boards: List<Board>) {

    fun calculateCount() {
        boards.forEach {
            if (it.cell is Basic) {
                checkAround(it.location, it.cell)
            }
        }
    }

    private fun checkAround(location: Location, basic: Basic) {
        val count = Around.values()
            .mapNotNull {
                val x = location.x + it.x
                val y = location.y + it.y
                findBoard(Location(x, y))
            }.count { board -> board.cell is Mine }
        basic.addCount(count)
    }

    fun findBoard(location: Location) = boards.firstOrNull { board -> board.location == location }

    fun isEnd() = isSuccess() || isLose()

    private fun isSuccess() = boards.count { it.cell is Basic && !it.cell.isOpen } == 0

    fun isLose() = boards.count { it.cell is Mine && it.cell.isOpen } > 0

    fun openCell(location: Location) {
        val board = findBoard(location) ?: throw IllegalArgumentException(LOCATION_EXCEPTION)
        if (shouldContinueOpening(board.cell)) {
            openAround(location)
        }
    }

    private fun openAround(location: Location) {
        val openingCells = mutableListOf(location)
        while (openingCells.isNotEmpty()) {
            val now = openingCells.removeFirst()
            Around.values()
                .mapNotNull {
                    val x = now.x + it.x
                    val y = now.y + it.y
                    findBoard(Location(x, y))
                }.filter {
                    it.cell is Basic && !it.cell.isOpen
                }.forEach {
                    if (shouldContinueOpening(it.cell)) {
                        openingCells.add(it.location)
                    }
                }
        }
    }

    private fun shouldContinueOpening(cell: Cell): Boolean {
        cell.open()
        if (cell is Basic && cell.count == 0) {
            return true
        }
        return false
    }

    companion object {
        fun of(height: Int, width: Int, count: Int): MinesWeeper {

            require((height * width) > count) { COUNT_EXCEPTION }

            val mines = MineGenerator.create(height, width, count)
            val boards = (LOCATION_START_NUM until height)
                .flatMap { y ->
                    (LOCATION_START_NUM until width)
                        .map { x ->
                            val location = Location(x, y)
                            Board(location, getCell(mines, location))
                        }
                }
            return MinesWeeper(boards)
        }

        private fun getCell(mines: List<Location>, location: Location): Cell {
            if (mines.contains(location)) return Mine
            return Basic()
        }

        private const val COUNT_EXCEPTION = "지뢰수가 지뢰찾기게임의 칸수보다 적어야합니다."
        private const val LOCATION_START_NUM = 0
        private const val LOCATION_EXCEPTION = "잘못된 위치입니다."
    }
}
