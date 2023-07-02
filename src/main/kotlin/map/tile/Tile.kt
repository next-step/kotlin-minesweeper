package map.tile

import map.TileType

/**
 * 원래 Tile 이 position 을 가지고 있는데
 * MineMap 클래스에서 position 을 기준으로 Tile 을 저장하게 바꾸면서
 * Tile 에는 position 이 필요없을것같아서 뺀다.
 * 이 결정이 옳은지 나중에 미션을 진행하면서 회고해보자
 */
sealed class Tile(
    private val tileType: TileType,
) {
    fun getSymbol() = tileType.symbol
    override fun toString(): String {
        return tileType.symbol
    }
}
