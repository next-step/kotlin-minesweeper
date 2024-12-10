package domain

class RandomMinesGenerator : MinesGenerator {
    override fun getMines(
        width: Int,
        height: Int,
        numberOfMine: Int,
    ): MineSet {
        val resultSet: MutableSet<Mine> = mutableSetOf()

        while (resultSet.size < numberOfMine) {
            val x = (0 until width).random()
            val y = (0 until height).random()
            val mine = Mine(x, y)
            if (!resultSet.contains(mine)) resultSet.add(mine)
        }

        return MineSet(resultSet.toSet())
    }
}
