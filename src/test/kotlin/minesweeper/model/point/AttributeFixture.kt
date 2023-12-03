package minesweeper.model.point

object AttributeFixture

fun TileType.toAttribute(): Attribute {
    return Attribute(this, Vision.EXPOSED)
}
