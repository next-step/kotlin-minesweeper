package minesweeper.domain.generator

import minesweeper.domain.tile.state.Tile
import minesweeper.domain.tile.strategy.GenerateStrategy

@JvmInline
value class TileGenerator(private val generateStrategy: GenerateStrategy) {
    fun generate(): List<Tile> {
        return generateStrategy.generate()
    }
}
