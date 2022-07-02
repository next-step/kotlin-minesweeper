package minesweeper.entity

object MineMap {
    fun createMap(mapInfo: MapInformation): List<MapElement> {
        return mutableListOf<MapElement>().apply {
            addAll(List(mapInfo.numberOfMines) { MapElement.MINE })
            addAll(List(mapInfo.height * mapInfo.width - mapInfo.numberOfMines) { MapElement.GROUND })
            shuffle()
        }
    }

    fun getChunkedMap(mapInfo: MapInformation): List<List<MapElement>> {
        return createMap(mapInfo).chunked(mapInfo.width)
    }
}
