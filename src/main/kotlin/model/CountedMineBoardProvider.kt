package model

data class CountedMineBoardProvider(private val installMineBoard: InstalledMineBoard) {

    val countedMineBoard: CountedMineBoard by lazy {
        CountedMineBoard(
            installMineBoard.replacedSafetyMark {
                installMineBoard.mineCounts(
                    it.nearByPositions(
                        installMineBoard.maxXPosition,
                        installMineBoard.maxYPosition
                    )
                )
            }
        )
    }
}
