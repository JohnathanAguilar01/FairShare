package com.example.fairshare

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.dp

@Composable
fun MainLogo(modifier: Modifier = Modifier) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
    ){
        Text(
            text = "FAIR\n  SHARE",
            fontSize = 40.sp,
            lineHeight = 30.sp,
            letterSpacing = 0.3.sp,
            color = MaterialTheme.colorScheme.primary,
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MainLogoPreview() {
    MainLogo()
}