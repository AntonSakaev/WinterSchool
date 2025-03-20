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

val Icons.Search: ImageVector
    get() {
        if (_search != null) {
            return _search!!
        }
        _search = ImageVector.Builder(
            name = "Search",
            defaultWidth = 18.0.dp,
            defaultHeight = 18.0.dp,
            viewportWidth = 18.0F,
            viewportHeight = 18.0F,
        ).path(
            fill = SolidColor(Color(0xFF00ACFF)),
            fillAlpha = 1.0F,
            strokeAlpha = 1.0F,
            strokeLineWidth = 0.0F,
            strokeLineCap = StrokeCap.Butt,
            strokeLineJoin = StrokeJoin.Miter,
            strokeLineMiter = 4.0F,
            pathFillType = PathFillType.NonZero,
        ) {
              moveTo(7.2F, -0.0F)
              curveTo(5.29F, -0.0F, 3.459F, 0.758F, 2.109F, 2.109F)
              curveTo(0.758F, 3.459F, -0.0F, 5.29F, -0.0F, 7.2F)
              curveTo(-0.0F, 9.11F, 0.758F, 10.941F, 2.109F, 12.291F)
              curveTo(3.459F, 13.642F, 5.29F, 14.4F, 7.2F, 14.4F)
              curveTo(9.11F, 14.4F, 10.941F, 13.642F, 12.291F, 12.291F)
              curveTo(13.642F, 10.941F, 14.4F, 9.11F, 14.4F, 7.2F)
              curveTo(14.4F, 5.29F, 13.642F, 3.459F, 12.291F, 2.109F)
              curveTo(10.941F, 0.758F, 9.11F, -0.0F, 7.2F, -0.0F)
              close()
        }.path(
            fill = SolidColor(Color(0xFF00ACFF)),
            fillAlpha = 1.0F,
            strokeAlpha = 1.0F,
            strokeLineWidth = 0.0F,
            strokeLineCap = StrokeCap.Butt,
            strokeLineJoin = StrokeJoin.Miter,
            strokeLineMiter = 4.0F,
            pathFillType = PathFillType.EvenOdd,
        ) {
              moveTo(17.736F, 17.737F)
              curveTo(17.568F, 17.905F, 17.339F, 18.0F, 17.1F, 18.0F)
              curveTo(16.861F, 18.0F, 16.632F, 17.905F, 16.464F, 17.737F)
              lineTo(13.314F, 14.587F)
              curveTo(13.15F, 14.417F, 13.059F, 14.189F, 13.061F, 13.953F)
              curveTo(13.063F, 13.717F, 13.158F, 13.492F, 13.325F, 13.325F)
              curveTo(13.492F, 13.158F, 13.717F, 13.063F, 13.953F, 13.061F)
              curveTo(14.189F, 13.059F, 14.417F, 13.15F, 14.586F, 13.314F)
              lineTo(17.736F, 16.464F)
              curveTo(17.905F, 16.633F, 18.0F, 16.862F, 18.0F, 17.1F)
              curveTo(18.0F, 17.339F, 17.905F, 17.568F, 17.736F, 17.737F)
              close()
        }.build()
        return _search!!
    }
private var _search: ImageVector? = null
