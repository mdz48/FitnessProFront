package com.example.fitnessprofront.features.recipies.presentation.screens

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.fitnessprofront.core.ui.components.InputFitness
import com.example.fitnessprofront.features.recipies.presentation.viewmodels.RecipiesViewModel
import com.example.fitnessprofront.features.recipies.presentation.viewmodels.RecipiesViewModelFactory

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddRecipeScreen(
    factory: RecipiesViewModelFactory,
    onNavigateBack: () -> Unit
) {
    val viewModel: RecipiesViewModel = viewModel(factory = factory)
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val isDarkTheme = isSystemInDarkTheme()

    val backgroundColor = if (isDarkTheme) Color(0xFF0F172A) else Color(0xFFF8FAFC)
    val textColor = if (isDarkTheme) Color.White else Color(0xFF0F172A)
    val labelColor = if (isDarkTheme) Color(0xFF94A3B8) else Color(0xFF64748B)
    val textFieldBackground = if (isDarkTheme) Color(0xFF1E293B) else Color.White
    val textFieldBorder = if (isDarkTheme) Color(0xFF334155) else Color(0xFFE2E8F0)
    val placeholderColor = if (isDarkTheme) Color(0xFF64748B) else Color(0xFF94A3B8)

    var recipeName by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var ingredients by remember { mutableStateOf("") }
    var instructions by remember { mutableStateOf("") }
    var selectedDays by remember { mutableStateOf<Set<String>>(emptySet()) }
    var selectedMealType by remember { mutableStateOf<String?>(null) }

    LaunchedEffect(uiState.recipeCreated) {
        if (uiState.recipeCreated) {
            viewModel.resetRecipeCreated()
            onNavigateBack()
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = backgroundColor,
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Agregar Receta",
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        color = textColor
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = labelColor
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = backgroundColor
                )
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(16.dp)
                    .padding(bottom = 80.dp),
                verticalArrangement = Arrangement.spacedBy(24.dp)
            ) {
                InputFitness(
                    value = recipeName,
                    onValueChange = { recipeName = it },
                    placeholder = "e.g. Grilled Salmon Salad"
                )


                Text(
                    text = "Días de la semana",
                    fontWeight = FontWeight.Medium,
                    fontSize = 16.sp,
                    color = textColor,
                    modifier = Modifier.padding(bottom = 4.dp)
                )

                val days = listOf("Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado", "Domingo")
                Column(
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {

                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        days.take(3).forEach { day ->
                            FilterChip(
                                selected = selectedDays.contains(day),
                                onClick = {
                                    selectedDays = if (selectedDays.contains(day)) {
                                        selectedDays - day
                                    } else {
                                        selectedDays + day
                                    }
                                },
                                label = { Text(day, fontSize = 14.sp) }
                            )
                        }
                    }


                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        days.slice(3..4).forEach { day ->
                            FilterChip(
                                selected = selectedDays.contains(day),
                                onClick = {
                                    selectedDays = if (selectedDays.contains(day)) {
                                        selectedDays - day
                                    } else {
                                        selectedDays + day
                                    }
                                },
                                label = { Text(day, fontSize = 14.sp) }
                            )
                        }
                    }


                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        days.takeLast(2).forEach { day ->
                            FilterChip(
                                selected = selectedDays.contains(day),
                                onClick = {
                                    selectedDays = if (selectedDays.contains(day)) {
                                        selectedDays - day
                                    } else {
                                        selectedDays + day
                                    }
                                },
                                label = { Text(day, fontSize = 14.sp) }
                            )
                        }
                    }
                }

                Text(
                    text = "Tipo de comida",
                    fontWeight = FontWeight.Medium,
                    fontSize = 16.sp,
                    color = textColor,
                    modifier = Modifier.padding(bottom = 4.dp)
                )

                val mealTypes = listOf("Desayuno", "Almuerzo", "Cena")
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    mealTypes.forEach { meal ->
                        FilterChip(
                            selected = selectedMealType == meal,
                            onClick = { selectedMealType = meal },
                            label = { Text(meal) }
                        )
                    }
                }

                Text(
                    text = "Descripción",
                    fontWeight = FontWeight.Medium,
                    fontSize = 16.sp,
                    color = textColor,
                    modifier = Modifier.padding(bottom = 4.dp)
                )

                OutlinedTextField(
                    value = description,
                    onValueChange = { description = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(120.dp),
                    placeholder = { Text("Escribe una breve descripción del plato...", color = placeholderColor, fontSize = 16.sp) },
                    shape = RoundedCornerShape(12.dp)
                )

                Text(
                    text = "Ingredientes",
                    fontWeight = FontWeight.Medium,
                    fontSize = 16.sp,
                    color = textColor,
                    modifier = Modifier.padding(bottom = 4.dp)
                )

                OutlinedTextField(
                    value = ingredients,
                    onValueChange = { ingredients = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(120.dp),
                    placeholder = { Text("Lista los ingredientes...\nej. 1 taza de quinoa, 200g de tomates cherry", color = placeholderColor, fontSize = 16.sp) },
                    shape = RoundedCornerShape(12.dp)
                )

                Text(
                    text = "Instrucciones",
                    fontWeight = FontWeight.Medium,
                    fontSize = 16.sp,
                    color = textColor,
                    modifier = Modifier.padding(bottom = 4.dp)
                )

                OutlinedTextField(
                    value = instructions,
                    onValueChange = { instructions = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(180.dp),
                    placeholder = { Text("1. Enjuagar la quinoa...\n2. Picar las verduras...\n3. Mezclar todo en un bowl...", color = placeholderColor, fontSize = 16.sp) },
                    shape = RoundedCornerShape(12.dp)
                )

                if (uiState.errorMessage != null) {
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(containerColor = Color(0xFF991B1B).copy(alpha = 0.1f)),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            horizontalArrangement = Arrangement.spacedBy(12.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(imageVector = Icons.Default.Warning, contentDescription = null, tint = Color(0xFFEF4444))
                            Text(text = uiState.errorMessage ?: "", color = Color(0xFFEF4444), fontSize = 14.sp)
                        }
                    }
                }

                Button(
                    onClick = {
                        viewModel.createRecipe(
                            name = recipeName,
                            description = description,
                            ingredients = ingredients,
                            instructions = instructions,
                            userId = 1,
                            scheduledDays = selectedDays.toList(),
                            mealType = selectedMealType ?: ""
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    enabled = !uiState.isLoading && recipeName.isNotBlank() && description.isNotBlank() && ingredients.isNotBlank() && instructions.isNotBlank() && selectedMealType != null,
                    shape = RoundedCornerShape(12.dp)
                ) {
                    if (uiState.isLoading) {
                        CircularProgressIndicator(modifier = Modifier.size(24.dp), color = Color.White, strokeWidth = 2.dp)
                    } else {
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(imageVector = Icons.Default.Check, contentDescription = null, tint = Color.White)
                            Text(text = "Guardar Receta", fontSize = 16.sp, fontWeight = FontWeight.SemiBold, color = Color.White)
                        }
                    }
                }
            }
        }
    }
}