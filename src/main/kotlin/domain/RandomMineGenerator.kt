package domain

class RandomMineGenerator : MineGenerator {
    override fun getMine(
        width: Int,
        height: Int,
        numberofMine: Int,
    ): Set<Mine> {
        val resultSet: MutableSet<Mine> = mutableSetOf()

        while (resultSet.size < numberofMine) {
            val x = (0 until width).random()
            val y = (0 until height).random()
            val mine = Mine(x, y)
            if (!resultSet.contains(mine)) resultSet.add(mine)
        }

        return resultSet.toSet()
    }
}
