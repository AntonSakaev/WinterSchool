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

val Icons.ArrowBack: ImageVector
    get() {
        if (_arrowBack != null) {
            return _arrowBack!!
        }
        _arrowBack = ImageVector.Builder(
            name = "ArrowBack",
            defaultWidth = 16.0.dp,
            defaultHeight = 16.0.dp,
            viewportWidth = 16.0F,
            viewportHeight = 16.0F,
        ).path(
            fill = SolidColor(Color.Black),
            fillAlpha = 1.0F,
            strokeAlpha = 1.0F,
            strokeLineWidth = 0.0F,
            strokeLineCap = StrokeCap.Butt,
            strokeLineJoin = StrokeJoin.Miter,
            strokeLineMiter = 4.0F,
            pathFillType = PathFillType.NonZero,
        ) {
            moveTo(14.7912F, 7.00498F)
            lineTo(3.62124F, 7.00498F)
            lineTo(8.50124F, 2.12498F)
            curveTo(8.89124F, 1.73498F, 8.89124F, 1.09498F, 8.50124F, 0.704976F)
            curveTo(8.11124F, 0.314976F, 7.48124F, 0.314976F, 7.09124F, 0.704976F)
            lineTo(0.50124F, 7.29498F)
            curveTo(0.11124F, 7.68498F, 0.11124F, 8.31498F, 0.50124F, 8.70498F)
            lineTo(7.09124F, 15.295F)
            curveTo(7.48124F, 15.685F, 8.11124F, 15.685F, 8.50124F, 15.295F)
            curveTo(8.89124F, 14.905F, 8.89124F, 14.275F, 8.50124F, 13.885F)
            lineTo(3.62124F, 9.00498F)
            lineTo(14.7912F, 9.00498F)
            curveTo(15.3412F, 9.00498F, 15.7912F, 8.55498F, 15.7912F, 8.00498F)
            curveTo(15.7912F, 7.45498F, 15.3412F, 7.00498F, 14.7912F, 7.00498F)
            close()
        }.build()
        return _arrowBack!!
    }
private var _arrowBack: ImageVector? = null