package minesweeper.entity

class MineMap {
  fun createMap(mapInfo: MapInformation): List<MapElement> {
    return mutableListOf<MapElement>().apply {
      repeat(mapInfo.numberOfMines) { this.add(MapElement.MINE) }
      repeat(mapInfo.height * mapInfo.width - mapInfo.numberOfMines) { this.add(MapElement.GROUND) }
    }.shuffled()
  }

  fun getChunkedMap(mapInfo: MapInformation): List<List<MapElement>> {
    return createMap(mapInfo).chunked(mapInfo.width)
  }
}