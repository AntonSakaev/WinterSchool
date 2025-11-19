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

val Icons.Options: ImageVector
    get() {
        if (_options != null) {
            return _options!!
        }
        _options = ImageVector.Builder(
            name = "Options",
            defaultWidth = 16.0.dp,
            defaultHeight = 18.0.dp,
            viewportWidth = 16.0F,
            viewportHeight = 18.0F,
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
            moveTo(5.0F, 2.0F)
            curveTo(4.735F, 2.0F, 4.48F, 2.105F, 4.293F, 2.293F)
            curveTo(4.105F, 2.48F, 4.0F, 2.735F, 4.0F, 3.0F)
            curveTo(4.0F, 3.265F, 4.105F, 3.52F, 4.293F, 3.707F)
            curveTo(4.48F, 3.895F, 4.735F, 4.0F, 5.0F, 4.0F)
            curveTo(5.265F, 4.0F, 5.52F, 3.895F, 5.707F, 3.707F)
            curveTo(5.895F, 3.52F, 6.0F, 3.265F, 6.0F, 3.0F)
            curveTo(6.0F, 2.735F, 5.895F, 2.48F, 5.707F, 2.293F)
            curveTo(5.52F, 2.105F, 5.265F, 2.0F, 5.0F, 2.0F)

            moveTo(2.17F, 2.0F)
            curveTo(2.377F, 1.414F, 2.76F, 0.907F, 3.267F, 0.549F)
            curveTo(3.773F, 0.19F, 4.379F, -0.002F, 5.0F, -0.002F)
            curveTo(5.621F, -0.002F, 6.227F, 0.19F, 6.733F, 0.549F)
            curveTo(7.24F, 0.907F, 7.623F, 1.414F, 7.83F, 2.0F)
            horizontalLineTo(15.0F)
            curveTo(15.265F, 2.0F, 15.52F, 2.105F, 15.707F, 2.293F)
            curveTo(15.895F, 2.48F, 16.0F, 2.735F, 16.0F, 3.0F)
            curveTo(16.0F, 3.265F, 15.895F, 3.52F, 15.707F, 3.707F)
            curveTo(15.52F, 3.895F, 15.265F, 4.0F, 15.0F, 4.0F)
            horizontalLineTo(7.83F)
            curveTo(7.623F, 4.586F, 7.24F, 5.093F, 6.733F, 5.451F)
            curveTo(6.227F, 5.81F, 5.621F, 6.002F, 5.0F, 6.002F)
            curveTo(4.379F, 6.002F, 3.773F, 5.81F, 3.267F, 5.451F)
            curveTo(2.76F, 5.093F, 2.377F, 4.586F, 2.17F, 4.0F)
            horizontalLineTo(1.0F)
            curveTo(0.735F, 4.0F, 0.48F, 3.895F, 0.293F, 3.707F)
            curveTo(0.105F, 3.52F, 0.0F, 3.265F, 0.0F, 3.0F)
            curveTo(0.0F, 2.735F, 0.105F, 2.48F, 0.293F, 2.293F)
            curveTo(0.48F, 2.105F, 0.735F, 2.0F, 1.0F, 2.0F)
            horizontalLineTo(2.17F)

            moveTo(8.17F, 8.0F)
            curveTo(8.377F, 7.414F, 8.76F, 6.907F, 9.267F, 6.549F)
            curveTo(9.773F, 6.19F, 10.379F, 5.998F, 11.0F, 5.998F)
            curveTo(11.621F, 5.998F, 12.226F, 6.19F, 12.733F, 6.549F)
            curveTo(13.24F, 6.907F, 13.623F, 7.414F, 13.83F, 8.0F)
            horizontalLineTo(15.0F)
            curveTo(15.265F, 8.0F, 15.52F, 8.105F, 15.707F, 8.293F)
            curveTo(15.895F, 8.48F, 16.0F, 8.735F, 16.0F, 9.0F)
            curveTo(16.0F, 9.265F, 15.895F, 9.52F, 15.707F, 9.707F)
            curveTo(15.52F, 9.895F, 15.265F, 10.0F, 15.0F, 10.0F)
            horizontalLineTo(13.83F)
            curveTo(13.623F, 10.585F, 13.24F, 11.093F, 12.733F, 11.451F)
            curveTo(12.226F, 11.81F, 11.621F, 12.002F, 11.0F, 12.002F)
            curveTo(10.379F, 12.002F, 9.773F, 11.81F, 9.267F, 11.451F)
            curveTo(8.76F, 11.093F, 8.377F, 10.585F, 8.17F, 10.0F)
            horizontalLineTo(1.0F)
            curveTo(0.735F, 10.0F, 0.48F, 9.895F, 0.293F, 9.707F)
            curveTo(0.105F, 9.52F, 0.0F, 9.265F, 0.0F, 9.0F)
            curveTo(0.0F, 8.735F, 0.105F, 8.48F, 0.293F, 8.293F)
            curveTo(0.48F, 8.105F, 0.735F, 8.0F, 1.0F, 8.0F)
            horizontalLineTo(8.17F)

            moveTo(5.0F, 14.0F)
            curveTo(4.735F, 14.0F, 4.48F, 14.105F, 4.293F, 14.293F)
            curveTo(4.105F, 14.48F, 4.0F, 14.735F, 4.0F, 15.0F)
            curveTo(4.0F, 15.265F, 4.105F, 15.52F, 4.293F, 15.707F)
            curveTo(4.48F, 15.895F, 4.735F, 16.0F, 5.0F, 16.0F)
            curveTo(5.265F, 16.0F, 5.52F, 15.895F, 5.707F, 15.707F)
            curveTo(5.895F, 15.52F, 6.0F, 15.265F, 6.0F, 15.0F)
            curveTo(6.0F, 14.735F, 5.895F, 14.48F, 5.707F, 14.293F)
            curveTo(5.52F, 14.105F, 5.265F, 14.0F, 5.0F, 14.0F)

            moveTo(2.17F, 14.0F)
            curveTo(2.377F, 13.415F, 2.76F, 12.907F, 3.267F, 12.549F)
            curveTo(3.773F, 12.19F, 4.379F, 11.998F, 5.0F, 11.998F)
            curveTo(5.621F, 11.998F, 6.227F, 12.19F, 6.733F, 12.549F)
            curveTo(7.24F, 12.907F, 7.623F, 13.415F, 7.83F, 14.0F)
            horizontalLineTo(15.0F)
            curveTo(15.265F, 14.0F, 15.52F, 14.105F, 15.707F, 14.293F)
            curveTo(15.895F, 14.48F, 16.0F, 14.735F, 16.0F, 15.0F)
            curveTo(16.0F, 15.265F, 15.895F, 15.52F, 15.707F, 15.707F)
            curveTo(15.52F, 15.895F, 15.265F, 16.0F, 15.0F, 16.0F)
            horizontalLineTo(7.83F)
            curveTo(7.623F, 16.586F, 7.24F, 17.093F, 6.733F, 17.451F)
            curveTo(6.227F, 17.81F, 5.621F, 18.003F, 5.0F, 18.003F)
            curveTo(4.379F, 18.003F, 3.773F, 17.81F, 3.267F, 17.451F)
            curveTo(2.76F, 17.093F, 2.377F, 16.586F, 2.17F, 16.0F)
            horizontalLineTo(1.0F)
            curveTo(0.735F, 16.0F, 0.48F, 15.895F, 0.293F, 15.707F)
            curveTo(0.105F, 15.52F, 0.0F, 15.265F, 0.0F, 15.0F)
            curveTo(0.0F, 14.735F, 0.105F, 14.48F, 0.293F, 14.293F)
            curveTo(0.48F, 14.105F, 0.735F, 14.0F, 1.0F, 14.0F)
            horizontalLineTo(2.17F)
            close()
        }.build()
        return _options!!
    }
private var _options: ImageVector? = null
