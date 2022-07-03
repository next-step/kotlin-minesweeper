package game.minesweeper.domain.strategy

import game.minesweeper.domain.map.Coordinate
import game.minesweeper.domain.map.MapConfig

class RandomMineGenerator(private val config: MapConfig) : MineGenerator {
    override fun generate(count: Int): List<Coordinate> {
        return (1..config.height)
            .map { x ->
                (1..config.width)
                    .map { Coordinate(x, it) }
            }
            .flatten().shuffled().take(count)
    }
}
