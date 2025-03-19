package com.map711s.mk1.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.map711s.mk1.data.repository.MortalKombatRepository
import com.map711s.mk1.ui.components.ComboListItem
import com.map711s.mk1.ui.components.DifficultyIndicator
import com.map711s.mk1.ui.components.FatalityListItem
import com.map711s.mk1.ui.components.MoveListItem
import com.map711s.mk1.ui.theme.MKBlack
import com.map711s.mk1.ui.theme.MKDarkGray
import com.map711s.mk1.ui.theme.MKGold
import com.map711s.mk1.ui.theme.MKRed
import com.map711s.mk1.data.MortalKombatRepository

// Character Detail Screen
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharacterDetailScreen(
    navController: NavController,
    characterId: String
) {
    val character = remember { MortalKombatRepository.getCharacterById(characterId) }

    if (character == null) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MKBlack),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Character not found",
                color = Color.White,
                fontSize = 18.sp
            )
        }
        return
    }

    var selectedTab by remember { mutableStateOf(0) }
    val tabs = listOf("Special Moves", "Combos", "Fatalities")

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        character.name,
                        color = MKGold
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            Icons.Default.ArrowBack,
                            contentDescription = "Back",
                            tint = MKGold
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MKDarkGray
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(MKBlack)
        ) {
            // Character Info Section
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Character Avatar
                Image(
                    painter = rememberAsyncImagePainter(model = character.imageUrl),
                    contentDescription = character.name,
                    modifier = Modifier
                        .size(100.dp)
                        .padding(end = 16.dp),
                    contentScale = ContentScale.Crop
                )

                Column {
                    Text(
                        text = character.name,
                        color = MKGold,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    DifficultyIndicator(character.difficultyLevel)

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = character.bio,
                        color = Color.White,
                        fontSize = 14.sp
                    )
                }
            }

            Divider(color = MKRed, thickness = 2.dp)

            // Tab Row for different move types
            TabRow(
                selectedTabIndex = selectedTab,
                containerColor = MKDarkGray,
                contentColor = MKGold
            ) {
                tabs.forEachIndexed { index, title ->
                    Tab(
                        selected = selectedTab == index,
                        onClick = { selectedTab = index },
                        text = {
                            Text(
                                text = title,
                                color = if (selectedTab == index) MKGold else Color.White
                            )
                        }
                    )
                }
            }

            // Content based on selected tab
            when (selectedTab) {
                0 -> {
                    // Special Moves
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(8.dp)
                    ) {
                        if (character.specialMoves.isEmpty()) {
                            item {
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(16.dp),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        text = "No special moves available",
                                        color = Color.White,
                                        fontSize = 16.sp
                                    )
                                }
                            }
                        } else {
                            items(character.specialMoves) { move ->
                                MoveListItem(move = move)
                            }
                        }
                    }
                }
                1 -> {
                    // Combos
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(8.dp)
                    ) {
                        if (character.combos.isEmpty()) {
                            item {
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(16.dp),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        text = "No combos available",
                                        color = Color.White,
                                        fontSize = 16.sp
                                    )
                                }
                            }
                        } else {
                            items(character.combos) { combo ->
                                ComboListItem(combo = combo)
                            }
                        }
                    }
                }
                2 -> {
                    // Fatalities
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(8.dp)
                    ) {
                        if (character.fatalities.isEmpty()) {
                            item {
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(16.dp),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        text = "No fatalities available",
                                        color = Color.White,
                                        fontSize = 16.sp
                                    )
                                }
                            }
                        } else {
                            items(character.fatalities) { fatality ->
                                FatalityListItem(fatality = fatality)
                            }
                        }
                    }
                }
            }
        }
    }
}