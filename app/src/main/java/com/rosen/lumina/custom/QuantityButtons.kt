package com.rosen.lumina.custom

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rosen.lumina.R
import com.rosen.lumina.ui.theme.PaleGray1
import com.rosen.lumina.ui.theme.PaleGray2
import com.rosen.lumina.ui.theme.Peach
import com.rosen.lumina.ui.theme.White

@Composable
fun QuantityButtons(quantity: Int, onQuantityChange: (Int) -> Unit) {
    Box(
        modifier = Modifier
            .background(PaleGray1, shape = RoundedCornerShape(16.dp))
    ) {
        Row(
            modifier = Modifier.padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // Minus Button
            Box(
                modifier = Modifier
                    .background(PaleGray2, shape = RoundedCornerShape(12.dp))
            ) {
                IconButton(
                    modifier = Modifier.size(24.dp),
                    onClick = { onQuantityChange(quantity - 1) },
                    enabled = quantity > 1
                ) {
                    Icon(painter = painterResource(id = R.drawable.ic_minus), contentDescription = null, tint = Peach)
                }
            }

            // Quantity Text
            Text(
                text = quantity.toString(),
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontWeight = FontWeight.Bold,
                    color = White,
                    fontSize = 18.sp
                ),
            )

            // Plus Button
            Box(
                modifier = Modifier
                    .background(PaleGray2, shape = RoundedCornerShape(12.dp))
            ) {
                IconButton(
                    modifier = Modifier.size(24.dp),
                    onClick = { onQuantityChange(quantity + 1) }
                ) {
                    Icon(imageVector = Icons.Default.Add, contentDescription = null, tint = Peach)
                }
            }
        }
    }
}