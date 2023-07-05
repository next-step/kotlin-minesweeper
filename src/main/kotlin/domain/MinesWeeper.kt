package domain

class MinesWeeper(val boards: List<Cell>) {
    companion object {
        fun of(height: Int, width: Int, count: Int): MinesWeeper {

            val mines = MineGenerator.create(height, width, count)
            val boards = (LOCATION_START_NUM until height)
                .flatMap { y ->
                    (LOCATION_START_NUM until width)
                        .map { x ->
                            val location = Location(y, x)
                            Cell(location, mines.contains(location))
                        }
                }
            return MinesWeeper(boards)
        }

        private const val LOCATION_START_NUM = 0
    }
}
