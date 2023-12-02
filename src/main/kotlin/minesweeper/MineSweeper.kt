package minesweeper

class MineSweeper(
    val mineMap: MineMap,
    clickedSet: Set<Point> = setOf(),
) {
    var clickedSet: Set<Point> = clickedSet
        private set

    val isDone get() = clickedSet.size == mineMap.totalSize - mineMap.mapInfo.mineNumber

    fun click(point: Point): ClickResult {
        if (clickedSet.contains(point)) return ClickResult.ALREADY_CLICKED

        return when (val clickedTile = mineMap.mineMap[point]) {
            is MapTile.Mine -> ClickResult.GAME_OVER
            is MapTile.Blank -> {
                setBlankTile(point, clickedTile)
                ClickResult.CONTINUE
            }

            else -> ClickResult.ERROR
        }
    }

    private fun setBlankTile(point: Point, clickedTile: MapTile.Blank) {
        setClickedPoint(point)
        if (clickedTile.isNoMineNear) {
            setAdjacentTilesClicked(point)
        }
    }

    private fun setAdjacentTilesClicked(point: Point) {
        val adjacent = point.getAdjacentPoints(mineMap.mapInfo.mapSize)
        for (adj in adjacent) {
            if (clickedSet.contains(adj)) continue

            adjacentTileDfs(adj)
        }
    }

    private fun adjacentTileDfs(adj: Point) {
        val info = mineMap.mineMap[adj]
        if (info is MapTile.Blank) {
            setClickedPoint(adj)
            if (info.isNoMineNear) setAdjacentTilesClicked(adj)
        }
    }

    private fun setClickedPoint(point: Point) {
        clickedSet = clickedSet.plus(point)
    }

    enum class ClickResult {
        GAME_OVER, ALREADY_CLICKED, CONTINUE, ERROR
    }
}
