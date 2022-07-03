package domain

/**
 * 지뢰찾기 판
 * Created by Jaesungchi on 2022.06.28..
 */
data class Board(
    val grounds: Map<Position, Ground>
) {
    fun setAutoMine(mineCount: Int) {
        grounds.toList().shuffled().take(mineCount).map {
            grounds[it.first]?.installMine()
        }
    }

    fun setManualMine(positions: List<Position>?) {
        positions?.forEach {
            grounds[it]?.installMine()
        }
    }
}
