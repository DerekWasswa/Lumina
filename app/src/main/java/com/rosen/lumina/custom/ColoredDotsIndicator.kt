package com.rosen.lumina.custom

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rosen.lumina.ui.theme.DarkerPurple
import com.rosen.lumina.ui.theme.OrangeBrown
import com.rosen.lumina.ui.theme.Peach
import com.rosen.lumina.ui.theme.Pink40
import com.rosen.lumina.ui.theme.Pink80
import com.rosen.lumina.ui.theme.White

@Composable
fun ColoredDotsIndicator(activeIndex: Int, modifier: Modifier) {
    val dotColors = listOf(Peach, OrangeBrown, DarkerPurple, Pink40, Pink80)

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        dotColors.forEachIndexed { index, color ->
            Dot(color = color, isActive = index == activeIndex)
            if (index < dotColors.size - 1) {
                Spacer(modifier = Modifier.height(6.dp))
            }
        }
    }
}

@Composable
fun Dot(color: Color, isActive: Boolean) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.wrapContentHeight()
    ) {
        Box(
            modifier = Modifier
                .size(12.dp)
                .clip(CircleShape)
                .background(color = White)
                .border(width = (0.5).dp, color = color, shape = CircleShape)
        ) {
            Box(
                modifier = Modifier
                    .size(6.dp)
                    .clip(CircleShape)
                    .background(color = color)
                    .align(Alignment.Center)
            )
        }

        Spacer(modifier = Modifier.width(8.dp))

        Box(
            modifier = Modifier
                .size(16.dp, 2.dp)
                .clip(CircleShape)
                .alpha(if (isActive) 1f else 0f)
                .background(color = MaterialTheme.colorScheme.primary)
        )
    }
}

@Preview
@Composable
fun ColoredDotsIndicatorPreview() {
    var activeIndex by remember { mutableStateOf(4) }

    ColoredDotsIndicator(activeIndex = activeIndex, modifier = Modifier.padding(16.dp))
}

@Preview
@Composable
fun DotPreview() {
    Dot(color = Color.Red, isActive = true)
}