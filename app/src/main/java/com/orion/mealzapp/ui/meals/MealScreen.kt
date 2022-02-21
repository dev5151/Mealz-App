package com.orion.mealzapp.ui.meals

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import coil.compose.rememberImagePainter
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.placeholder
import com.google.accompanist.placeholder.shimmer
import com.orion.mealzapp.data.models.Category

@Composable
fun MealScreen(
    viewModel: MealsViewModel,
    onClick: (meal: Category) -> Unit
) {

    val scaffoldState = rememberScaffoldState()
    val meals = viewModel.mealsList.observeAsState(listOf()).value


    Scaffold(
        topBar = { AppBar() },
        backgroundColor = Color.Gray,
        scaffoldState = scaffoldState,
        snackbarHost = { scaffoldState.snackbarHostState },
        modifier = Modifier.fillMaxSize()
    )
    {
        LazyColumn() {
            items(meals) { meal ->
                MealCard(
                    meal = meal,
                    isLoading = false,
                    onClick = onClick
                )
            }
        }

    }

}

@Composable
fun AppBar() {
    TopAppBar(backgroundColor = Color.Black) {
        Text(
            text = "Mealz",
            color = Color.White,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            letterSpacing = 4.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MealCard(meal: Category, isLoading: Boolean, onClick: (meal: Category) -> Unit) {

    Card(
        elevation = 4.dp,
        shape = RoundedCornerShape(6.dp),
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 12.dp)
            .placeholder(
                visible = isLoading,
                highlight = PlaceholderHighlight.shimmer(
                    progressForMaxAlpha = 0.5f,
                    highlightColor = Color.Gray
                ),
                color = Color.Gray
            ),
        onClick = { onClick(meal) }
    ) {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxWidth()
                .height(216.dp)
        ) {

            val (image, meadCategory) = createRefs()

            Image(
                painter = rememberImagePainter(
                    data = meal.strCategoryThumb,
                    builder = { crossfade(true) }
                ),
                contentDescription = "Meal Image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
                    .constrainAs(image) {
                        start.linkTo(parent.start)
                        top.linkTo(parent.top)
                        end.linkTo(parent.end)
                        bottom.linkTo(parent.bottom)
                    }
            )


            Text(
                text = meal.strCategory ?: "",
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.Normal,
                modifier = Modifier.constrainAs(meadCategory) {
                    start.linkTo(parent.start, 12.dp)
                    end.linkTo(parent.end, 12.dp)
                    linkTo(
                        top = parent.top,
                        topMargin = 16.dp,
                        bottom = parent.bottom,
                        bottomMargin = 8.dp,
                        bias = 1f
                    )
                    width = Dimension.fillToConstraints
                }
            )


        }
    }
}
