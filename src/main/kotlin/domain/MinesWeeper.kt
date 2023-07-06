package domain

class MinesWeeper(val boards: List<Cell>) {
    companion object {
        fun of(height: Int, width: Int, count: Int): MinesWeeper {

            require((height * width) > count) { COUNT_EXCEPTION }

            val mines = MineGenerator.create(height, width, count)
            val boards = (LOCATION_START_NUM until height)
                .flatMap { y ->
                    (LOCATION_START_NUM until width)
                        .map { x ->
                            val location = Location(y, x)
                            getCell(mines, location)
                        }
                }
            return MinesWeeper(boards)
        }

        private fun getCell(mines: List<Location>, location: Location): Cell {
            if (mines.contains(location)) return Mine(location)
            return Basic(location)
        }

        private const val COUNT_EXCEPTION = "지뢰수가 지뢰찾기게임의 칸수보다 적어야합니다."
        private const val LOCATION_START_NUM = 0
    }
}
