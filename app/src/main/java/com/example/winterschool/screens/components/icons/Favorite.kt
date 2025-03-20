package com.example.winterschool.screens.components.icons

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
            defaultWidth = 16.0.dp,
            defaultHeight = 17.0.dp,
            viewportWidth = 16.0F,
            viewportHeight = 17.0F,
        ).path(
            fill = SolidColor(Color(0xFFD9D9D9)),
            fillAlpha = 1.0F,
            strokeAlpha = 1.0F,
            strokeLineWidth = 0.666667F,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
            strokeLineMiter = 4.0F,
            pathFillType = PathFillType.NonZero,
        ) {
              moveTo(10.905F, 3.41F)
              curveTo(8.833F, 3.41F, 7.812F, 5.32F, 7.812F, 5.32F)
              curveTo(7.812F, 5.32F, 6.791F, 3.41F, 4.719F, 3.41F)
              curveTo(3.035F, 3.41F, 1.702F, 4.727F, 1.685F, 6.299F)
              curveTo(1.65F, 9.561F, 4.453F, 11.881F, 7.525F, 13.831F)
              curveTo(7.609F, 13.885F, 7.709F, 13.914F, 7.812F, 13.914F)
              curveTo(7.914F, 13.914F, 8.014F, 13.885F, 8.099F, 13.831F)
              curveTo(11.171F, 11.881F, 13.974F, 9.561F, 13.939F, 6.299F)
              curveTo(13.922F, 4.727F, 12.588F, 3.41F, 10.905F, 3.41F)
              close()
        }.build()
        return _favorite!!
    }
private var _favorite: ImageVector? = null

