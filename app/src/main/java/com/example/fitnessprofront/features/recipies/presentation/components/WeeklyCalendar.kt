package com.example.fitnessprofront.features.recipies.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.fitnessprofront.features.recipies.domain.entities.Recipe
import java.time.DayOfWeek
import java.time.LocalDate
import java.util.Locale

@Composable
fun WeeklyCalendar(
    recipes: List<Recipe>,
    onRecipeClick: (Recipe) -> Unit,
    onEditRecipe: (Int) -> Unit,
    onDeleteRecipe: (Recipe) -> Unit
) {
    val today = LocalDate.now()
    val startOfWeek = today.minusDays(today.dayOfWeek.value.toLong() - 1)
    val daysOfWeek = (0..6).map { startOfWeek.plusDays(it.toLong()) }
    val spanishLocale = Locale.Builder().setLanguage("es").setRegion("ES").build()
    var showDialog by remember { mutableStateOf(false) }
    var selectedRecipe by remember { mutableStateOf<Recipe?>(null) }

    if (showDialog && selectedRecipe != null) {
        RecipeDetailsDialog(
            recipe = selectedRecipe!!,
            onDismiss = { showDialog = false },
            onEdit = { onEditRecipe(selectedRecipe!!.id) },
            onDelete = { onDeleteRecipe(selectedRecipe!!) }
        )
    }

    Column(modifier = Modifier.fillMaxWidth()) {
        // Días de la semana
        Row(modifier = Modifier.fillMaxWidth()) {
            for (day in daysOfWeek) {
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .padding(4.dp),
                    horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally
                ) {
                    Text(
                        text = day.dayOfWeek.getDisplayName(
                            java.time.format.TextStyle.SHORT,
                            spanishLocale
                        ).uppercase(spanishLocale),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Text(text = day.dayOfMonth.toString())

                    // Obtener el nombre del día en español
                    val dayNameSpanish = getDayNameInSpanish(day.dayOfWeek)

                    // Filtrar recetas para este día específico
                    val recipesForDay = recipes.filter { recipe ->
                        recipe.scheduledDaysOrEmpty().any { scheduledDay ->
                            scheduledDay.equals(dayNameSpanish, ignoreCase = true)
                        }
                    }

                    // Mostrar indicadores solo si hay recetas para este día
                    if (recipesForDay.isNotEmpty()) {
                        Column(
                            modifier = Modifier.padding(top = 8.dp),
                            horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally
                        ) {
                            recipesForDay.forEach { recipe ->
                                RecipeIndicator(
                                    recipe = recipe,
                                    onClick = {
                                        selectedRecipe = recipe
                                        showDialog = true
                                    }
                                )
                            }
                        }
                    }
                }
            }
        }

        // Información adicional (solo se muestra si hay una sola receta)
        if (recipes.size == 1) {
            val singleRecipe = recipes.first()
            Text(
                text = "Receta: ${singleRecipe.name}",
                modifier = Modifier
                    .padding(top = 16.dp)
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = "Tipo: ${singleRecipe.mealType}",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = "Días programados: ${singleRecipe.scheduledDaysOrEmpty().joinToString(", ")}",
                modifier = Modifier
                    .padding(bottom = 8.dp)
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Composable
fun RecipeIndicator(
    recipe: Recipe,
    onClick: () -> Unit
) {
    val indicatorColor = when (recipe.mealType.lowercase()) {
        "desayuno" -> Color(0xFFFBBF24) // Amarillo
        "almuerzo" -> Color(0xFF10B981) // Verde
        "cena" -> Color(0xFF8B5CF6)    // Púrpura
        "snack" -> Color(0xFFF97316)   // Naranja
        else -> Color.Green
    }

    Box(
        modifier = Modifier
            .padding(vertical = 2.dp)
            .width(24.dp)
            .height(24.dp)
            .background(indicatorColor, RoundedCornerShape(4.dp))
            .clickable(onClick = onClick)
    )
}

@Composable
fun RecipeDetailsDialog(
    recipe: Recipe,
    onDismiss: () -> Unit,
    onEdit: () -> Unit,
    onDelete: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(text = recipe.name) },
        text = {
            RecipeCard(
                recipe = recipe,
                onClick = { /* No hace nada en el diálogo */ },
                onEdit = onEdit,
                onDelete = onDelete
            )
        },
        confirmButton = {
            TextButton(onClick = onDismiss) {
                Text("Cerrar")
            }
        }
    )
}

private fun getDayNameInSpanish(dayOfWeek: DayOfWeek): String {
    return when (dayOfWeek) {
        DayOfWeek.MONDAY -> "Lunes"
        DayOfWeek.TUESDAY -> "Martes"
        DayOfWeek.WEDNESDAY -> "Miércoles"
        DayOfWeek.THURSDAY -> "Jueves"
        DayOfWeek.FRIDAY -> "Viernes"
        DayOfWeek.SATURDAY -> "Sábado"
        DayOfWeek.SUNDAY -> "Domingo"
    }
}