package com.example.fairshare

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fairshare.ui.theme.archivo

@Composable
fun Bottom(modifier: Modifier = Modifier) {
    var billAmount by remember { mutableStateOf("") }
    var tipPercentage: Int by remember { mutableIntStateOf(0) }
    var numOfPeople by remember { mutableStateOf("") }
    var billAmountPerPerson: Float by remember { mutableStateOf(0.0f) }
    var tipAmountPerPerson: Float by remember { mutableStateOf(0.0f) }
    var totalAmountPerPerson: Float by remember { mutableStateOf(0.0f) }

    fun onClick() {
        if (numOfPeople.toInt() == 0) return // prevent divide-by-zero

        val totalTipAmount = billAmount.toFloat() * (tipPercentage / 100f)
        billAmountPerPerson = billAmount.toFloat() / numOfPeople.toInt()
        tipAmountPerPerson = totalTipAmount / numOfPeople.toInt()
        totalAmountPerPerson = billAmountPerPerson + tipAmountPerPerson
    }


    Column(
        modifier = Modifier
            .clip(RoundedCornerShape(topEnd = 20.dp, topStart = 20.dp))
            .background(MaterialTheme.colorScheme.tertiary)
            .fillMaxSize(),
    ){
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterVertically),
            horizontalAlignment = Alignment.Start,
            modifier = Modifier
                .fillMaxWidth()
                .padding(50.dp)
        ){
            Text("Bill Amount")
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(5.dp))
                    .background(MaterialTheme.colorScheme.background)
                    .width(300.dp)
                    .height(36.dp)
            ){
                BasicTextField(
                    value = billAmount,
                    textStyle = TextStyle(
                        fontFamily = archivo,
                        fontSize = 12.sp,
                    ),
                    onValueChange = { newValue ->
                        // Allow only digits and at most one decimal point
                        val filtered = newValue.filterIndexed { index, c ->
                            c.isDigit() || (c == '.' && !newValue.take(index).contains("."))
                        }

                        // Limit to 2 decimal places if decimal exists
                        val decimalIndex = filtered.indexOf(".")
                        val finalValue = if (decimalIndex >= 0 && filtered.length > decimalIndex + 3) {
                            filtered.substring(0, decimalIndex + 3)
                        } else {
                            filtered
                        }

                        // Add dollar sign prefix
                        billAmount = if (finalValue.isNotEmpty()) {
                            if (finalValue.startsWith("$")) finalValue else "$finalValue"
                        } else {
                            ""
                        }
                    },
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Number
                    ),
                    modifier = Modifier
                        .padding(10.dp)
                )
            }
            Column(
                verticalArrangement = Arrangement.spacedBy(40.dp, Alignment.CenterVertically),
            ){
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .width(300.dp)
                ) {
                    TipButton(
                        text = 5,
                        tipPercentage = tipPercentage,
                        onClick = { tipPercentage = it }
                    )
                    TipButton(
                        text = 10,
                        tipPercentage = tipPercentage,
                        onClick = { tipPercentage = it }
                    )
                }
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .width(300.dp)
                ) {
                    TipButton(
                        text = 15,
                        tipPercentage = tipPercentage,
                        onClick = { tipPercentage = it }
                    )
                    TipButton(
                        text = 20,
                        tipPercentage = tipPercentage,
                        onClick = { tipPercentage = it }
                    )
                }
            }
            Text("# of People")
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(5.dp))
                    .background(MaterialTheme.colorScheme.background)
                    .width(300.dp)
                    .height(36.dp)
            ){
                BasicTextField(
                    value = numOfPeople,
                    textStyle = TextStyle(
                        fontFamily = archivo,
                        fontSize = 12.sp,
                    ),
                    onValueChange = { newValue ->
                        // Keep only digits
                        val filtered = newValue.filter { it.isDigit() }

                        // Optional: prevent leading zeros
                        val cleaned = filtered.trimStart { it == '1' }.ifEmpty { "1" }

                        numOfPeople = cleaned
                    },
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Number
                    ),
                    modifier = Modifier
                        .padding(10.dp)
                )
            }
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterVertically),
                modifier = Modifier
                    .clip(RoundedCornerShape(10.dp))
                    .background(MaterialTheme.colorScheme.primary)
                    .width(300.dp)
                    .padding(15.dp)
            ) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Column(
                        horizontalAlignment = Alignment.End,
                    ) {
                        Text(
                            "Bill Amount",
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.tertiary
                            )
                        Text(
                            "/ Per Person",
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.tertiary
                        )
                    }
                    Text(
                        String.format("%.2f", billAmountPerPerson),
                        color = MaterialTheme.colorScheme.tertiary,
                        fontSize = 24.sp
                        )
                }

                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Column(
                        horizontalAlignment = Alignment.End,
                    ) {
                        Text(
                            "Tip Amount",
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.tertiary
                        )
                        Text(
                            "/ Per Person",
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.tertiary
                        )
                    }
                    Text(
                        String.format("%.2f", tipAmountPerPerson),
                        color = MaterialTheme.colorScheme.tertiary,
                        fontSize = 24.sp
                    )
                }

                Divider(
                    color = MaterialTheme.colorScheme.tertiary,
                    thickness = 1.dp,
                    modifier = Modifier.padding(vertical = 8.dp)
                )

                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Column(
                        horizontalAlignment = Alignment.End,
                    ) {
                        Text(
                            "Total Amount",
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.tertiary
                        )
                        Text(
                            "/ Per Person",
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.tertiary
                        )
                    }
                    Text(
                        String.format("%.2f", totalAmountPerPerson),
                        color = MaterialTheme.colorScheme.tertiary,
                        fontSize = 24.sp
                    )
                }
            }
            Button(
                onClick = { onClick() },
                colors = ButtonDefaults.buttonColors(
                    containerColor  = MaterialTheme.colorScheme.primary
                ),
                shape = RoundedCornerShape(5.dp),
                modifier = Modifier
                    .width(300.dp)
                    .height(36.dp)
            ) {
                Text(
                    "Calculate",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.tertiary
                    )
            }
        }

    }

}