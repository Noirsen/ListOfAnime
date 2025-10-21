//// Muhammad Dzaki Wirayuda | NIM: 23523197
//// Ahmad Aiman Zumar Prawirosunoto | NIM: 23523112
//// Raditya Pratama | NIM: 23523169

package com.example.listapl

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
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

data class AnimeDetail(val premise: String, val review: String, val profileImageRes: Int, val backgroundImageRes: Int)

val animeDetails = mapOf(
    "Violet Evergarden" to AnimeDetail(
        premise = "The story follows Violet Evergarden's journey of reintegrating back into society after the war is over and her search for her life's purpose now that she is no longer a soldier in order to understand the last words her major, Gilbert, had told her: 'I love you.'",
        review = "Ini adalah sebuah masterpiece bagi ku hingga sampai menangis pada teather",
        profileImageRes = R.drawable.violet_profile,
        backgroundImageRes = R.drawable.violet_background
    ),
    "Fate series" to AnimeDetail(
        premise = "A series of visual novels and their adaptations, detailing the events of the Holy Grail Wars, intense conflicts where mages summon legendary heroes, known as Servants, to fight for the ultimate prize: the Holy Grail, an omnipotent wish-granting device.",
        review = "",
        profileImageRes = R.drawable.fate_profile,
        backgroundImageRes = R.drawable.fate_background
    ),
    "The Fragrant Flower Blooms with Dignity" to AnimeDetail(
        premise = "In a school sharply divided between the elite, advanced students and the rowdy, commoner students, a heartwarming romance blossoms between Rin, a girl from the elite side, and Kaoru, a boy from the commoner side, as they bridge the gap between their two worlds.",
        review = "",
        profileImageRes = R.drawable.kaoru_profile,
        backgroundImageRes = R.drawable.kaoru_background
    ),
    "Today's Menu for the Emiya Family" to AnimeDetail(
        premise = "A wholesome slice-of-life spin-off of the 'Fate' series, where the usual intense Holy Grail War is replaced by Master Shirou Emiya and his Servant Saber exploring their daily lives through the joy of cooking and sharing delicious meals with friends and former rivals.",
        review = "",
        profileImageRes = R.drawable.emiya_profile,
        backgroundImageRes = R.drawable.emiya_background
    ),
    "Lord of El-Melloi II" to AnimeDetail(
        premise = "Years after the Fourth Holy Grail War, Waver Velvet, now known as Lord El-Melloi II, navigates the treacherous political landscape of the Clock Tower, the heart of the magical world. He solves various magical mysteries and cases while mentoring the next generation of mages.",
        review = "",
        profileImageRes = R.drawable.lord_profile,
        backgroundImageRes = R.drawable.lord_background
    ),
    "Uma Musume" to AnimeDetail(
        premise = "In a world where great racehorses of the past are reborn as 'horse girls' with exceptional speed and endurance, Special Week, a horse girl from the countryside, moves to Tokyo to attend Tracen Academy. She and her teammates train to compete in the 'Twinkle Series' national race, aiming for glory.",
        review = "",
        profileImageRes = R.drawable.uma_profile,
        backgroundImageRes = R.drawable.uma_background
    )
)

@Composable
fun AnimeApp() {
    var selectedGenre by remember { mutableStateOf<String?>(null) }
    var selectedAnime by remember { mutableStateOf<Anime?>(null) }

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
        } else if (selectedAnime == null) {
            AnimeListScreen(
                genre = selectedGenre!!,
                onBackPressed = { selectedGenre = null },
                onAnimeSelected = { anime ->
                    val animeWithDetails = listOf(
                        "Violet Evergarden",
                        "Fate series",
                        "The Fragrant Flower Blooms with Dignity",
                        "Today's Menu for the Emiya Family",
                        "Lord of El-Melloi II",
                        "Uma Musume"
                    )
                    if (anime.name in animeWithDetails) {
                        selectedAnime = anime
                    }
                }
            )
        } else {
            AnimeDetailScreen(
                anime = selectedAnime!!,
                onBackPressed = { selectedAnime = null }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GenreScreen(onGenreSelected: (String) -> Unit) {
    val genres = animeList.map { it.genre }.distinct()
    var isList by remember { mutableStateOf(true) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Anime Genres", color = Color.White) },
                actions = {
                    Button(
                        onClick = { isList = !isList },
                        modifier = Modifier.padding(end = 8.dp)
                    ) {
                        Text(if (isList) "Grid" else "List")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent)
            )
        },
        containerColor = Color.Transparent
    ) { innerPadding ->
        if (isList) {
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
                    items(genres) { genre ->
                        GenreCard(genre = genre, onGenreSelected = onGenreSelected)
                    }
                }
            }
        } else {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier.padding(innerPadding),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(genres) { genre ->
                    GenreCard(genre = genre, onGenreSelected = onGenreSelected)
                }
            }
        }
    }
}

@Composable
fun GenreCard(genre: String, onGenreSelected: (String) -> Unit, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AnimeListScreen(
    genre: String,
    onBackPressed: () -> Unit,
    onAnimeSelected: (Anime) -> Unit
) {
    val animeInGenre = animeList.filter { it.genre == genre }
    var isList by remember { mutableStateOf(true) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(genre, color = Color.White) },
                navigationIcon = {
                    IconButton(onClick = onBackPressed) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", tint = Color.White)
                    }
                },
                actions = {
                    Button(
                        onClick = { isList = !isList },
                        modifier = Modifier.padding(end = 8.dp)
                    ) {
                        Text(if (isList) "Grid" else "List")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Transparent
                )
            )
        },
        containerColor = Color.Transparent
    ) { innerPadding ->
        if (isList) {
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
                        AnimeCard(anime = anime, onAnimeSelected = onAnimeSelected)
                    }
                }
            }
        } else {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier.padding(innerPadding),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(animeInGenre) { anime ->
                    AnimeCard(anime = anime, onAnimeSelected = onAnimeSelected)
                }
            }
        }
    }
}

@Composable
fun AnimeCard(anime: Anime, onAnimeSelected: (Anime) -> Unit, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onAnimeSelected(anime) },
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


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AnimeDetailScreen(anime: Anime, onBackPressed: () -> Unit) {
    val detail = animeDetails[anime.name]

    Box(modifier = Modifier.fillMaxSize()) {
        if (detail != null && detail.backgroundImageRes != 0) {
            Image(
                painter = painterResource(id = detail.backgroundImageRes),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
        }

        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(anime.name, color = Color.White) },
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
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .padding(16.dp)
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                if (detail != null) {
                    if (detail.profileImageRes != 0) {
                        Image(
                            painter = painterResource(id = detail.profileImageRes),
                            contentDescription = "Profile Picture",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .size(150.dp)
                                .clip(CircleShape)
                                .border(2.dp, Color.White, CircleShape)
                        )
                    }

                    Text(
                        text = "Premise",
                        style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
                        color = Color.Black
                    )
                    Text(
                        text = detail.premise,
                        style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
                        color = Color.Black
                    )
                    if (detail.review.isNotEmpty()) {
                        Text(
                            text = "My Review",
                            style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
                            color = Color.Black
                        )
                        Text(
                            text = detail.review,
                            style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
                            color = Color.Black
                        )
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
