package domain

import util.RandomGenerator

object MineMapGenerator {

    fun createMineMap(height: Int, width: Int, numOfMine: Int): MineMap {
        require(numOfMine <= height * width)

        //mine 위치 정보 받아오기
        val mineLocations = mutableSetOf<Pair<Int, Int>>()
        while (mineLocations.size < numOfMine) {
            val randomLocation = RandomGenerator.generateRandomLocation(height, width)
            mineLocations.add(randomLocation)
        }

        //mine 위치 정보를 바탕으로 지도 제작
        val elementLocations = mutableListOf<List<MapElement>>()
        for (i in 0 until height) {
            elementLocations += createRow(i, width, mineLocations)
        }

        return MineMap(elementLocations.toList())
    }

    fun createRow(
        rowNum: Int,
        rowLen: Int,
        mineLocations: MutableSet<Pair<Int, Int>>
    ): List<MapElement> {

        val row = mutableListOf<MapElement>()

        // 해당 row에서 mine의 index만 가지는 set
        val minesOfRow = mineLocations.filter { it.first == rowNum }
            .map { it.second }

        for (i in 0 until rowLen) {
            row += getElement(i, minesOfRow)
        }

        return row.toList()

    }

    fun getElement(index: Int, minesOfRow: List<Int>): MapElement {
        val mine = Mine
        val noneMine = EmptyElement
        return if (minesOfRow.contains(index)) mine else noneMine
    }
}
