package com.rosen.lumina

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rosen.lumina.custom.ColoredDotsIndicator
import com.rosen.lumina.custom.QuantityButtons
import com.rosen.lumina.custom.RatingBar
import com.rosen.lumina.ui.theme.CharcoalBlack
import com.rosen.lumina.ui.theme.DarkPurple
import com.rosen.lumina.ui.theme.DarkerPurple
import com.rosen.lumina.ui.theme.LuminaTheme
import com.rosen.lumina.ui.theme.OrangeBrown
import com.rosen.lumina.ui.theme.PaleGray
import com.rosen.lumina.ui.theme.PaleWhite
import com.rosen.lumina.ui.theme.Peach
import com.rosen.lumina.ui.theme.PurpleBlue
import com.rosen.lumina.ui.theme.TransparentOrange
import com.rosen.lumina.ui.theme.White
import kotlin.random.Random

@Composable
fun ProductDetails(productId: String?) {
    var isFavorite by remember { mutableStateOf(false) }

    Column(
        Modifier.fillMaxSize()
    ) {
        ProductDetails()
    }
}

@Composable
fun ProductNavBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(40.dp)
                .background(PaleGray, shape = RoundedCornerShape(12.dp))
        ) {
            IconButton(onClick = {}) {
                Icon(
                    painter = painterResource(
                        id = R.drawable.ic_home
                    ),
                    tint = DarkPurple,
                    contentDescription = null
                )
            }
        }

        Box(
            modifier = Modifier
                .size(40.dp)
                .background(PaleGray, shape = RoundedCornerShape(12.dp))
        ) {
            IconButton(onClick = {}) {
                Icon(
                    painter = painterResource(
                        id = R.drawable.ic_shopping_cart
                    ),
                    tint = DarkPurple,
                    contentDescription = null,
                )
            }
        }
    }
}

@Composable
fun ProductDetails() {
    var isFavorite by remember { mutableStateOf(false) }

    val product = getRandomProduct()

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {

        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
        ) {
            Column {
                ProductNavBar()

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                ) {
                    Image(
                        painter = painterResource(id = product.imageResId),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxHeight()
                            .align(Alignment.Center)
                    )

                    ColoredDotsIndicator(
                        activeIndex = 1,
                        modifier = Modifier
                            .align(alignment = Alignment.CenterEnd)
                            .padding(start = 16.dp)
                    )
                }
            }
        }

        Box (
            modifier = Modifier
                .weight(1f)
                .padding(top = 16.dp)
                .fillMaxWidth(),
            contentAlignment = Alignment.TopEnd
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        color = PurpleBlue,
                        shape = RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp)
                    )
            ) {
                Column(
                    modifier = Modifier
                        .padding(24.dp)
                        .fillMaxSize(),
                ) {

                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Column(
                            modifier = Modifier.weight(1f)
                        ) {
                            Text(
                                text = product.name,
                                style = MaterialTheme.typography.bodyLarge.copy(
                                    fontWeight = FontWeight.Bold,
                                    color = White,
                                    fontSize = 24.sp
                                ),
                            )

                            Row(
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                RatingBar(rating = 3.5, modifier = Modifier.padding(end = 8.dp), starsColor = Peach, starSize = 16.dp)
                                Text(
                                    text = "249 Reviews",
                                    style = MaterialTheme.typography.labelSmall.copy(
                                        fontSize = 12.sp,
                                        color = PaleWhite
                                    ),
                                )
                            }
                        }

                        QuantityButtons(quantity = 3, onQuantityChange = {

                        })

                    }

                    Spacer(modifier = Modifier.size(30.dp))

                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {

                        Column {
                            Box(
                                modifier = Modifier
                                    .background(DarkerPurple, shape = RoundedCornerShape(8.dp))
                            ) {
                                Icon(painter = painterResource(id = R.drawable.ic_plug), contentDescription = null, tint = White, modifier = Modifier.padding(8.dp))
                            }

                            Spacer(modifier = Modifier.size(8.dp))

                            Text(
                                text = "36 kwh",
                                style = MaterialTheme.typography.labelSmall.copy(
                                    color = White,
                                    fontSize = 10.sp
                                ),
                            )
                        }

                        Column {
                            Box(
                                modifier = Modifier
                                    .background(DarkerPurple, shape = RoundedCornerShape(12.dp))
                            ) {
                                Icon(painter = painterResource(id = R.drawable.ic_charger), contentDescription = null, tint = White, modifier = Modifier.padding(8.dp))
                            }

                            Spacer(modifier = Modifier.size(8.dp))

                            Text(
                                text = "10 watt",
                                style = MaterialTheme.typography.labelSmall.copy(
                                    color = White,
                                    fontSize = 10.sp
                                ),
                            )
                        }

                        Column {
                            Box(
                                modifier = Modifier
                                    .background(DarkerPurple, shape = RoundedCornerShape(12.dp))
                            ) {
                                Icon(painter = painterResource(id = R.drawable.full_size), contentDescription = null, tint = White, modifier = Modifier.padding(8.dp))
                            }

                            Spacer(modifier = Modifier.size(8.dp))

                            Text(
                                text = "9 Sizes",
                                style = MaterialTheme.typography.labelSmall.copy(
                                    color = White,
                                    fontSize = 10.sp
                                ),
                            )
                        }

                        Column {
                            Box(
                                modifier = Modifier
                                    .background(DarkerPurple, shape = RoundedCornerShape(12.dp))
                            ) {
                                Icon(painter = painterResource(id = R.drawable.ic_diversity), contentDescription = null, tint = White, modifier = Modifier.padding(8.dp))
                            }

                            Spacer(modifier = Modifier.size(8.dp))

                            Text(
                                text = "15 Colors",
                                style = MaterialTheme.typography.labelSmall.copy(
                                    color = White,
                                    fontSize = 10.sp
                                ),
                            )
                        }

                    }

                    Spacer(modifier = Modifier.size(30.dp))

                    Text(
                        text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. " +
                                "Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
                        style = MaterialTheme.typography.labelSmall.copy(
                            fontSize = 12.sp,
                            color = PaleWhite
                        )
                    )

                    Spacer(modifier = Modifier.size(30.dp))

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = buildAnnotatedString {
                                withStyle(style = SpanStyle(color = White, fontSize = 18.sp, fontWeight = FontWeight.Bold)) {
                                    append("${product.price}/")
                                }
                                withStyle(style = SpanStyle(fontWeight = FontWeight.Light, fontSize = 12.sp, color = PaleWhite)) {
                                    append("Price")
                                }
                            },
                            style = MaterialTheme.typography.bodyLarge.copy(
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold,
                                color = White
                            ),
                            modifier = Modifier.weight(1f)
                        )

                        Button(
                            onClick = { /* Handle button click */ },
                            modifier = Modifier.padding(start = 8.dp),
                            colors = ButtonDefaults.buttonColors(
                                contentColor = PurpleBlue,
                                containerColor = Peach
                            )
                        ) {
                            Text(text = "Purchase Now")
                        }
                    }
                }
            }

            Box(
                modifier = Modifier
                    .size(30.dp)
                    .offset((-20).dp, (-16).dp)
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = rememberRipple(bounded = false),
                        onClick = { isFavorite = !isFavorite }
                    )
                    .background(TransparentOrange, shape = CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = if (isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                    contentDescription = null,
                    tint = if (isFavorite) OrangeBrown else CharcoalBlack,
                    modifier = Modifier.size(20.dp)
                )
            }
        }
    }
}

private fun getRandomProduct(): Product {
    val index = Random.nextInt(1, 16)
    return Product(
        id = System.currentTimeMillis().toString(),
        name = getProduct(),
        price = (100 + index).toDouble(), // Generate random prices
        imageResId = getDrawableResourceId("lamp$index")
    )
}


@Preview(showBackground = true)
@Composable
fun ProductDetailsPreview() {
    LuminaTheme {
        ProductDetails("")
    }
}