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

val Icons.Clear: ImageVector
    get() {
        if (_clear != null) {
            return _clear!!
        }
        _clear = ImageVector.Builder(
            name = "Clear",
            defaultWidth = 14.0.dp,
            defaultHeight = 14.0.dp,
            viewportWidth = 14.0F,
            viewportHeight = 14.0F,
        ).path(
            fill = SolidColor(Color(0xFFD9D9D9)),
            fillAlpha = 1.0F,
            strokeAlpha = 1.0F,
            strokeLineWidth = 0.0F,
            strokeLineCap = StrokeCap.Butt,
            strokeLineJoin = StrokeJoin.Miter,
            strokeLineMiter = 4.0F,
            pathFillType = PathFillType.NonZero,
        ) {
            moveTo(7.0F, 8.4F)
            lineTo(2.1F, 13.3F)
            curveTo(1.917F, 13.483F, 1.683F, 13.575F, 1.4F, 13.575F)
            curveTo(1.117F, 13.575F, 0.883F, 13.483F, 0.7F, 13.3F)
            curveTo(0.517F, 13.117F, 0.425F, 12.883F, 0.425F, 12.6F)
            curveTo(0.425F, 12.317F, 0.517F, 12.083F, 0.7F, 11.9F)
            lineTo(5.6F, 7.0F)
            lineTo(0.7F, 2.1F)
            curveTo(0.517F, 1.917F, 0.425F, 1.683F, 0.425F, 1.4F)
            curveTo(0.425F, 1.117F, 0.517F, 0.883F, 0.7F, 0.7F)
            curveTo(0.883F, 0.517F, 1.117F, 0.425F, 1.4F, 0.425F)
            curveTo(1.683F, 0.425F, 1.917F, 0.517F, 2.1F, 0.7F)
            lineTo(7.0F, 5.6F)
            lineTo(11.9F, 0.7F)
            curveTo(12.083F, 0.517F, 12.317F, 0.425F, 12.6F, 0.425F)
            curveTo(12.883F, 0.425F, 13.117F, 0.517F, 13.3F, 0.7F)
            curveTo(13.483F, 0.883F, 13.575F, 1.117F, 13.575F, 1.4F)
            curveTo(13.575F, 1.683F, 13.483F, 1.917F, 13.3F, 2.1F)
            lineTo(8.4F, 7.0F)
            lineTo(13.3F, 11.9F)
            curveTo(13.483F, 12.083F, 13.575F, 12.317F, 13.575F, 12.6F)
            curveTo(13.575F, 12.883F, 13.483F, 13.117F, 13.3F, 13.3F)
            curveTo(13.117F, 13.483F, 12.883F, 13.575F, 12.6F, 13.575F)
            curveTo(12.317F, 13.575F, 12.083F, 13.483F, 11.9F, 13.3F)
            lineTo(7.0F, 8.4F)
            close()
        }.build()
        return _clear!!
    }
private var _clear: ImageVector? = null
