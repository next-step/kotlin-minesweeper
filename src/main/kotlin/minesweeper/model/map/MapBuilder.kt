package minesweeper.model.map

import minesweeper.model.laying.EvenlyStrategy

class MapBuilder {
    companion object {
        fun generate(mapHeight: Int, mapWidth: Int, countOfMines: Int): MineMap {
            val placeToMines = EvenlyStrategy(mapHeight, mapWidth).layingCoordinates(countOfMines)
            TODO()
            (1..mapHeight).forEach { row ->
                run {
                    (1..mapWidth).forEach { col ->
                        {
                            val i = row + col
                            println(i)
                        }
                    }
                }
            }

//            val matrix = Array(mapHeight) { row ->
//                IntArray(mapWidth) { col ->
//                    // 각 요소에 원하는 초기값 설정
//                    val i = 1
//                    val j = row+col
//                    row * mapWidth + col + 1
//                }
//            }
        }
    }
}
