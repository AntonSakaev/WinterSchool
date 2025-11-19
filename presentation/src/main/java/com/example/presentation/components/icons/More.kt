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

val Icons.More: ImageVector
    get() {
        if (_more != null) {
            return _more!!
        }
        _more = ImageVector.Builder(
            name = "More",
            defaultWidth = 14.0.dp,
            defaultHeight = 8.0.dp,
            viewportWidth = 14.0F,
            viewportHeight = 8.0F,
        ).path(
            fill = SolidColor(Color(0xFFD9D9D9)),
            fillAlpha = 1.0F,
            strokeAlpha = 1.0F,
            strokeLineWidth = 0.0F,
            strokeLineCap = StrokeCap.Butt,
            strokeLineJoin = StrokeJoin.Miter,
            strokeLineMiter = 4.0F,
            pathFillType = PathFillType.EvenOdd,
        ) {
            moveTo(0.228F, 0.237F)
            curveTo(0.373F, 0.091F, 0.571F, 0.01F, 0.777F, 0.01F)
            curveTo(0.983F, 0.01F, 1.181F, 0.091F, 1.327F, 0.237F)
            lineTo(6.995F, 5.906F)
            lineTo(12.663F, 0.237F)
            curveTo(12.735F, 0.163F, 12.821F, 0.104F, 12.916F, 0.063F)
            curveTo(13.011F, 0.022F, 13.113F, 0.001F, 13.216F, -0.0F)
            curveTo(13.319F, -0.001F, 13.421F, 0.019F, 13.517F, 0.058F)
            curveTo(13.612F, 0.097F, 13.699F, 0.155F, 13.772F, 0.228F)
            curveTo(13.845F, 0.301F, 13.903F, 0.387F, 13.942F, 0.483F)
            curveTo(13.981F, 0.578F, 14.001F, 0.681F, 14.0F, 0.784F)
            curveTo(13.999F, 0.887F, 13.977F, 0.989F, 13.937F, 1.084F)
            curveTo(13.896F, 1.179F, 13.837F, 1.264F, 13.762F, 1.336F)
            lineTo(7.545F, 7.554F)
            curveTo(7.399F, 7.7F, 7.201F, 7.782F, 6.995F, 7.782F)
            curveTo(6.789F, 7.782F, 6.591F, 7.7F, 6.446F, 7.554F)
            lineTo(0.228F, 1.336F)
            curveTo(0.082F, 1.19F, 0.0F, 0.993F, 0.0F, 0.787F)
            curveTo(0.0F, 0.581F, 0.082F, 0.383F, 0.228F, 0.237F)
            close()
        }.build()
        return _more!!
    }
private var _more: ImageVector? = null
