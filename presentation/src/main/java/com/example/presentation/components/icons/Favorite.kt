package com.example.presentation.components.icons

import androidx.compose.material.icons.Icons
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val Icons.Favorite: ImageVector
    get() {
        if (_favorite != null) {
            return _favorite!!
        }
        _favorite = ImageVector.Builder(
            name = "Favorite",
            defaultWidth = 20.0.dp,
            defaultHeight = 18.0.dp,
            viewportWidth = 20.0F,
            viewportHeight = 18.0F,
        ).path(
            fill = SolidColor(Color(0xFFD9D9D9)),
            fillAlpha = 1F,
            strokeAlpha = 1F,
            strokeLineWidth = 1.0F,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
            strokeLineMiter = 4.0F,
            pathFillType = PathFillType.NonZero,
        ) {
            moveTo(14.357F, 1.333F)
            curveTo(11.25F, 1.333F, 9.718F, 4.198F, 9.718F, 4.198F)
            curveTo(9.718F, 4.198F, 8.186F, 1.333F, 5.078F, 1.333F)
            curveTo(2.553F, 1.333F, 0.553F, 3.309F, 0.527F, 5.666F)
            curveTo(0.475F, 10.56F, 4.679F, 14.04F, 9.287F, 16.965F)
            curveTo(9.414F, 17.046F, 9.564F, 17.089F, 9.718F, 17.089F)
            curveTo(9.871F, 17.089F, 10.021F, 17.046F, 10.149F, 16.965F)
            curveTo(14.756F, 14.04F, 18.96F, 10.56F, 18.908F, 5.666F)
            curveTo(18.882F, 3.309F, 16.882F, 1.333F, 14.357F, 1.333F)
            close()
        }.build()
        return _favorite!!
    }
private var _favorite: ImageVector? = null


