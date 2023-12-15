package com.rosen.lumina

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.rosen.lumina.ui.theme.CharcoalBlack
import com.rosen.lumina.ui.theme.DarkPurple
import com.rosen.lumina.ui.theme.LightGray1
import com.rosen.lumina.ui.theme.LuminaTheme
import com.rosen.lumina.ui.theme.OrangeBrown
import com.rosen.lumina.ui.theme.PaleGray
import com.rosen.lumina.ui.theme.PurpleBlue
import com.rosen.lumina.ui.theme.TransparentGray
import com.rosen.lumina.ui.theme.TransparentOrange
import kotlin.math.pow
import kotlin.math.roundToLong

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LuminaTheme {

                val navController = rememberNavController()

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavHost(navController = navController, startDestination = "home") {
                        composable("home") {
                            Home(navController)
                        }
                        composable("productDetails/{productId}") { backStackEntry ->
                            val productId = backStackEntry.arguments?.getString("productId")
                            ProductDetails(productId = productId)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Home(navController: NavController) {
    Column(
        Modifier.fillMaxSize()
    ) {
        AppBar()
        ProductTabs(navController)
    }
}

@Composable
fun AppBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
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
        Text(
            text = "EXPLORE",
            style = MaterialTheme.typography.titleLarge.copy(
                fontWeight = FontWeight.Bold,
            )
        )
        Box(
            modifier = Modifier
                .size(40.dp)
                .background(PaleGray, shape = RoundedCornerShape(12.dp))
        ) {
            IconButton(onClick = {}) {
                Icon(
                    painter = painterResource(
                        id = R.drawable.ic_search
                    ),
                    tint = DarkPurple,
                    contentDescription = null
                )
            }
        }
    }
}

@Composable
fun ProductTabs(navController: NavController) {
    var selectedTab by remember { mutableStateOf("New") }

    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Tab(text = "New", isSelected = selectedTab == "New") {
                selectedTab = "New"
            }
            Tab(text = "Popular", isSelected = selectedTab == "Popular") {
                selectedTab = "Popular"
            }
            Tab(text = "Trending", isSelected = selectedTab == "Trending") {
                selectedTab = "Trending"
            }
            Tab(text = "Best Selling", isSelected = selectedTab == "Best Selling") {
                selectedTab = "Best Selling"
            }
        }

        // Display list of products based on the selected tab
        when (selectedTab) {
            "New" -> Products(products = getNewProducts(), navController = navController)
            "Popular" -> Products(products = getPopularProducts(), navController = navController)
            "Trending" -> Products(products = getTrendingProducts(), navController = navController)
            "Best Selling" -> Products(products = getBestSellingProducts(), navController = navController)
        }
    }
}

@Composable
fun Tab(text: String, isSelected: Boolean, onClick: () -> Unit) {
    Text(
        text = text,
        modifier = Modifier
            .clickable { onClick() }
            .padding(8.dp),
        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
        color = if (isSelected) PurpleBlue else TransparentGray
    )
}

@Composable
fun Products(products: List<Product>, navController: NavController) {
    LazyVerticalStaggeredGrid(
        modifier = Modifier
            .padding(horizontal = 16.dp),
        columns = StaggeredGridCells.Adaptive(150.dp)) {
        items(products) {
            ProductItem(product = it, navController = navController)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductItem(product: Product, navController: NavController) {
    var isFavorite by remember { mutableStateOf(false) }

    Column {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.BottomEnd
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp, end = 8.dp, top = 10.dp),
                colors = CardDefaults.cardColors(
                    containerColor = LightGray1
                ),
                shape = RoundedCornerShape(20.dp),
                onClick = {
                    navController.navigate("productDetails/${product.id}")
                }
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(vertical = 8.dp),
                ) {
                    Image(
                        painter = painterResource(id = product.imageResId),
                        contentDescription = null,
                        contentScale = ContentScale.Inside,
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight()
                    )
                }
            }

            Box(
                modifier = Modifier
                    .size(30.dp)
                    .offset((-20).dp, (10).dp)
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

        Column(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = product.name,
                style = MaterialTheme.typography.bodyLarge.copy(
                    color = PurpleBlue,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                ),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = "$${product.price}",
                style = MaterialTheme.typography.bodyLarge.copy(
                    color = OrangeBrown,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                ),
            )
        }
    }
}

data class Product(
    val id: String,
    val name: String,
    val price: Double,
    val imageResId: Int
)

fun getNewProducts(): List<Product> = generateProductList("Product", 5)

fun getPopularProducts(): List<Product> = generateProductList("Product", 3)

fun getTrendingProducts(): List<Product> = generateProductList("Trendy Product", 6)

fun getBestSellingProducts(): List<Product> = generateProductList("Best Seller", 2)

private fun generateProductList(prefix: String, count: Int): List<Product> {
    return (1..count).map {
        Product(
            id = it.toString(),
            name = getProduct(),
            price = (10.0 + it).roundTo(2), // Generate random prices
            imageResId = getDrawableResourceId("lamp$it")
        )
    }
}

fun getDrawableResourceId(name: String): Int {
    return try {
        R.drawable::class.java.getField(name).getInt(null)
    } catch (e: Exception) {
        R.drawable.ic_launcher_foreground // Placeholder drawable ID
    }
}

private fun Double.roundTo(decimals: Int): Double {
    val factor = 10.0.pow(decimals.toDouble())
    return (this * factor).roundToLong() / factor
}

fun getProduct() :  String {
    val lamps = listOf(
        "Sunbeam Lamp",
        "Moonlight Glow",
        "Starry Night Illuminator",
        "Aurora Borealis Beacon",
        "Zenith Zen Lamp",
        "Celestial Lantern",
        "Radiant Table Lamp",
        "Mystic Mirage Light",
        "Eclipse Elegance Fixture",
        "Luminary Lotus Lamp"
    )
    return lamps.shuffled().random()
}

@Preview(showBackground = true)
@Composable
fun HomePreview() {
    LuminaTheme {
        val navController = rememberNavController()
        Home(navController)
    }
}