package com.ahmedmadhoun.world_cup_compose.presentation.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.ahmedmadhoun.world_cup_compose.R

val Intro = FontFamily(
    Font(R.font.intro, FontWeight.Normal),
    Font(R.font.intro, FontWeight.Medium),
    Font(R.font.intro, FontWeight.SemiBold),
    Font(R.font.intro, FontWeight.Bold),
)

// Set of Material typography styles to start with
val Typography = Typography(
    body1 = TextStyle(
        fontFamily = Intro,
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp
    ),


    button = TextStyle(
        fontFamily = Intro,
        fontWeight = FontWeight.Bold,
        fontSize = 15.sp
    ),

    h1 = TextStyle(
        fontFamily = Intro,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp
    ),

    h2 = TextStyle(
        fontWeight = FontWeight.Light,
        fontSize = 18.sp
    ),
    h3 = TextStyle(
        fontFamily = Intro,
        fontWeight = FontWeight.Light,
        fontSize = 14.sp
    ),

    h4 = TextStyle(
        fontFamily = Intro,
        fontWeight = FontWeight.Normal,
        fontSize = 13.sp
    ),

    h5 = TextStyle(
        fontFamily = Intro,
        fontWeight = FontWeight.Normal,
        fontSize = 11.sp
    ),

)