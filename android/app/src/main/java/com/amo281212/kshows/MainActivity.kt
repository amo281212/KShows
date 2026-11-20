
package com.amo281212.kshows

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val Background = Color(0xFF101014)
private val Panel = Color(0xFF19191F)
private val Purple = Color(0xFFC6A5FF)
private val Pink = Color(0xFFFF8FC8)
private val Green = Color(0xFF8CE4B1)
private val Muted = Color(0xFFAAA9B7)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MiKshowApp()
        }
    }
}

@Composable
fun MiKshowApp() {
    var selectedTab by remember { mutableIntStateOf(0) }

    MaterialTheme {
        Scaffold(
            containerColor = Background,
            bottomBar = {
                NavigationBar(
                    containerColor = Panel,
                    contentColor = Color.White
                ) {
                    val tabs = listOf(
                        "Inicio", "Explorar", "Mi lista",
                        "Actividad", "Perfil"
                    )
                    val icons = listOf("⌂", "⌕", "♡", "◷", "●")

                    tabs.forEachIndexed { index, label ->
                        NavigationBarItem(
                            selected = selectedTab == index,
                            onClick = { selectedTab = index },
                            icon = {
                                Text(icons[index], fontSize = 21.sp)
                            },
                            label = { Text(label, fontSize = 9.sp) },
                            colors = NavigationBarItemDefaults.colors(
                                selectedIconColor = Purple,
                                selectedTextColor = Purple,
                                indicatorColor = Purple.copy(alpha = 0.15f),
                                unselectedIconColor = Muted,
                                unselectedTextColor = Muted
                            )
                        )
                    }
                }
            }
        ) { padding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Background)
                    .padding(padding)
            ) {
                Header()

                when (selectedTab) {
                    0 -> HomeScreen()
                    else -> PlaceholderScreen(
                        listOf(
                            "Inicio", "Explorar", "Mi lista",
                            "Actividad", "Perfil"
                        )[selectedTab]
                    )
                }
            }
        }
    }
}

@Composable
fun Header() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Background)
            .padding(horizontal = 18.dp, vertical = 17.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text("Mi", color = Purple, fontSize = 24.sp,
                fontWeight = FontWeight.Black)
            Text("K", color = Pink, fontSize = 24.sp,
                fontWeight = FontWeight.Black)
            Text("show", color = Color.White, fontSize = 24.sp,
                fontWeight = FontWeight.Black)
        }

        Text("⌕", color = Color.White, fontSize = 29.sp)
    }
}

@Composable
fun HomeScreen() {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(bottom = 24.dp)
    ) {
        item {
            FeaturedCarousel()
        }

        item {
            PosterSection(
                "Continuar viendo",
                listOf("Programa 1", "Programa 2", "Programa 3")
            )
        }

        item {
            PosterSection(
                "Mi lista",
                listOf("Programa 4", "Programa 5", "Programa 6")
            )
        }

        item {
            PosterSection(
                "Populares",
                listOf("Programa 7", "Programa 8", "Programa 9")
            )
        }

        item {
            PosterSection(
                "Explorar K-shows",
                listOf("Variedad", "Competencia", "Viajes")
            )
        }
    }
}

@Composable
fun FeaturedCarousel() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .height(220.dp)
            .background(
                Brush.linearGradient(
                    listOf(
                        Color(0xFF493458),
                        Color(0xFF211B31),
                        Panel
                    )
                ),
                RoundedCornerShape(18.dp)
            )
            .padding(20.dp),
        verticalArrangement = Arrangement.Bottom
    ) {
        Text(
            "DESTACADO EN MIKSHOW",
            color = Pink,
            fontSize = 11.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(Modifier.height(8.dp))

        Text(
            "Tu mundo K.\nTu historia.",
            color = Color.White,
            fontSize = 29.sp,
            fontWeight = FontWeight.Black,
            lineHeight = 32.sp
        )

        Spacer(Modifier.height(12.dp))

        Text(
            "Descubre tus próximos programas favoritos.",
            color = Color.White.copy(alpha = 0.8f),
            fontSize = 12.sp
        )
    }
}

@Composable
fun PosterSection(title: String, programs: List<String>) {
    Column(
        modifier = Modifier.padding(top = 25.dp)
    ) {
        Text(
            title,
            modifier = Modifier.padding(horizontal = 18.dp),
            color = Color.White,
            fontSize = 19.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(Modifier.height(12.dp))

        LazyRow(
            contentPadding = PaddingValues(horizontal = 18.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(programs) { program ->
                Column(modifier = Modifier.width(115.dp)) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .aspectRatio(2f / 3f)
                            .background(
                                Brush.verticalGradient(
                                    listOf(
                                        Color(0xFF493458),
                                        Color(0xFF19151F)
                                    )
                                ),
                                RoundedCornerShape(11.dp)
                            ),
                        contentAlignment = Alignment.BottomStart
                    ) {
                        Text(
                            program,
                            modifier = Modifier.padding(10.dp),
                            color = Color.White,
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    Text(
                        program,
                        color = Muted,
                        fontSize = 12.sp,
                        modifier = Modifier.padding(top = 7.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun PlaceholderScreen(title: String) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Background),
        contentAlignment = Alignment.Center
    ) {
        Text(
            title,
            color = Color.White,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )
    }
}
