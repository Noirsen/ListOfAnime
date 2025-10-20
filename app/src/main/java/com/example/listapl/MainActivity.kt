package com.example.listapl

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.listapl.ui.theme.ListAplTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ListAplTheme {
                AnimeApp()
            }
        }
    }
}

data class Anime(val name: String, val genre: String)

val animeList = listOf(
    Anime("Neon Genesis Evangelion", "Action"),
    Anime("Drifters", "Action"),
    Anime("The Saga of Tanya the Evil", "Action"),
    Anime("Fate series", "Action"),
    Anime("Tada never falls in love", "Romance"),
    Anime("The Fragrant Flower Blooms with Dignity", "Romance"),
    Anime("Clannad", "Romance"),
    Anime("Wotakoi: Love is Hard for Otaku", "Romance"),
    Anime("Kaguya-sama: Love is War", "Romance"),
    Anime("Tanaka-kun is Always Listless", "Slice of Life"),
    Anime("Daily life of Highschool boys", "Slice of Life"),
    Anime("Hyouka", "Slice of Life"),
    Anime("Today's Menu for the Emiya Family", "Slice of Life"),
    Anime("Yuru camp", "Slice of Life"),
    Anime("Violet Evergarden", "Drama"),
    Anime("Frieren: Beyond Journey's End", "Drama"),
    Anime("A Silent Voice", "Drama"),
    Anime("Howl's Moving Castle", "Drama"),
    Anime("Your Lie in April", "Drama"),
    Anime("Detective Conan", "Mystery"),
    Anime("The Apothecary Diaries", "Mystery"),
    Anime("Monster", "Mystery"),
    Anime("Monogatari series", "Mystery"),
    Anime("Lord of El-Melloi II", "Mystery"),
    Anime("Uma Musume", "Sports"),
    Anime("Haikyuu", "Sports"),
    Anime("Kuroku no Basket", "Sports"),
    Anime("Hajime no Ippo", "Sports"),
    Anime("Baki Hanma", "Sports")
)

@Composable
fun AnimeApp() {
    var selectedGenre by remember { mutableStateOf<String?>(null) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(Color(0xFF3C1053), Color(0xFFDC143C))
                )
            )
    ) {
        if (selectedGenre == null) {
            GenreScreen(onGenreSelected = { genre -> selectedGenre = genre })
        } else {
            AnimeListScreen(
                genre = selectedGenre!!,
                onBackPressed = { selectedGenre = null }
            )
        }
    }
}

@Composable
fun GenreScreen(onGenreSelected: (String) -> Unit) {
    val genres = animeList.map { it.genre }.distinct()

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        LazyColumn(
            contentPadding = PaddingValues(horizontal = 32.dp, vertical = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(genres) { genre ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onGenreSelected(genre) },
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = genre,
                            style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold)
                        )
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AnimeListScreen(genre: String, onBackPressed: () -> Unit) {
    val animeInGenre = animeList.filter { it.genre == genre }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(genre, color = Color.White) },
                navigationIcon = {
                    IconButton(onClick = onBackPressed) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", tint = Color.White)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Transparent
                )
            )
        },
        containerColor = Color.Transparent
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            LazyColumn(
                contentPadding = PaddingValues(horizontal = 32.dp, vertical = 16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(animeInGenre) { anime ->
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = anime.name,
                                style = MaterialTheme.typography.titleMedium
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AnimeAppPreview() {
    ListAplTheme {
        AnimeApp()
    }
}
