package model

data class CountedMineBoardProvider(private val installMineBoard: InstalledMineBoard) {

    val countedMineBoard: CountedMineBoard by lazy {
        CountedMineBoard(
            installMineBoard.replacedSafetyMark { position, mineMark ->
                mineMark.next(nearPositionsMineCount(position))
            }
        )
    }

    private fun nearPositionsMineCount(position: Position): Int {
        return installMineBoard.mineCounts(
            position.nearByPositions(
                installMineBoard.maxXPosition,
                installMineBoard.maxYPosition
            )
        )
    }
}
