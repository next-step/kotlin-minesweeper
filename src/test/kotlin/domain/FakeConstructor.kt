package domain

fun rectangle(width: Int, height: Int): Rectangle = Rectangle(Width(width), Height(height))

fun positions(width: Int, height: Int): List<Position> = Rectangle(Width(width), Height(height)).toPositions()

fun fakeBlocks(positions: List<Position>, minePosition: Position = Position.of(0, 0)): Map<Position, Block> {
    return positions.associateWith {
        if (minePosition == it) Mine(it)
        else NormalBlock(it, MineCount(0))
    }
}

fun fakeBoard(width: Int, height: Int, mine: Pair<Int, Int> = 0 to 0): Board {
    val blocks = fakeBlocks(positions(width, height), Position.of(mine.first, mine.second))
    return Board(blocks)
}
