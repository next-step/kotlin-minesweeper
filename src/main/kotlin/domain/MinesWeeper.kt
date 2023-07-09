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
        val count = Around.values().mapNotNull {
            val x = location.x + it.x
            val y = location.y + it.y
            findBoard(Location(x, y))
        }.count { board -> board.cell is Mine }
        basic.addCount(count)
    }

    fun findBoard(location: Location) = boards.firstOrNull { board -> board.location == location }

    fun isMine(location: Location): Boolean {
        val board = findBoard(location) ?: throw IllegalArgumentException(LOCATION_EXCEPTION)
        return board.cell is Mine
    }

    fun isSuccess() = boards.count { it.cell is Basic && !it.cell.isOpen } == 0

    fun openCell(location: Location) {
        val board = findBoard(location) ?: throw IllegalArgumentException(LOCATION_EXCEPTION)
        val cell = board.cell as Basic
        cell.open()
        if (cell.count == 0) {
            openAround(location)
        }
    }

    private fun openAround(location: Location) {
        val openingCells = mutableListOf(location)
        while (openingCells.isNotEmpty()) {
            val now = openingCells.removeFirst()
            Around.values().mapNotNull {
                val x = now.x + it.x
                val y = now.y + it.y
                findBoard(Location(x, y))
            }.filter {
                it.cell is Basic && !it.cell.isOpen
            }.forEach {
                val cell = it.cell as Basic
                cell.open()
                if (cell.count == 0) {
                    openingCells.add(it.location)
                }
            }
        }
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
