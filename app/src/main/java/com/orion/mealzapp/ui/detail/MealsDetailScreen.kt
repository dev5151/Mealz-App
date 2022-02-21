package com.orion.mealzapp.ui.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import coil.compose.rememberImagePainter
import com.orion.mealzapp.R
import com.orion.mealzapp.data.models.Category

@Composable
fun MealsDetailScreen(meal: Category?, onBackPress: () -> Unit) {
    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        val (backIcon, mealImage, titleText, descText) = createRefs()

        Image(
            painter = rememberImagePainter(data = meal?.strCategoryThumb),
            contentDescription = "Meal Image",
            contentScale = ContentScale.Crop,
            alignment = Alignment.Center,
            modifier = Modifier
                .fillMaxSize()
                .constrainAs(mealImage) {
                    linkTo(parent.start, parent.top, parent.end, parent.bottom)
                    width = Dimension.matchParent
                    height = Dimension.matchParent
                }
        )

        /*  Image(
              painter = rememberImagePainter(R.drawable.backdrop_gradient, builder = {
                  decoder(SvgDecoder(LocalContext.current))
              }),
              contentDescription = "Image Scrim",
              modifier = Modifier
                  .fillMaxSize()
                  .constrainAs(scrimImage) {
                      linkTo(parent.start, parent.top, parent.end, parent.bottom)
                      width = Dimension.matchParent
                      height = Dimension.matchParent
                  }
          )*/

        IconButton(
            onClick = onBackPress,
            modifier = Modifier
                .size(42.dp)
                .constrainAs(backIcon) {
                    start.linkTo(parent.start, 16.dp)
                    top.linkTo(parent.top, 16.dp)
                }
        ) {
            Image(
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_back),
                contentDescription = "Back Icon",
                modifier = Modifier.fillMaxSize()
            )
        }

        Text(
            text = meal?.strCategoryDescription ?: "",
            fontSize = 14.sp,
            fontWeight = FontWeight.Normal,
            color = Color.White,
            modifier = Modifier.constrainAs(descText) {
                start.linkTo(parent.start, 24.dp)
                end.linkTo(parent.end, 24.dp)
                bottom.linkTo(parent.bottom, 8.dp)
                width = Dimension.fillToConstraints
            }
        )


        Text(
            text = meal?.strCategory ?: "",
            fontSize = 29.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            modifier = Modifier.constrainAs(titleText) {
                start.linkTo(parent.start, 24.dp)
                end.linkTo(parent.end, 24.dp)
                bottom.linkTo(descText.top, 64.dp)
                width = Dimension.fillToConstraints
            }
        )
    }
}