package dev.shop.fast_shop.ui.signup


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.*
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.*
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import shop.fast_shop.R
import dev.shop.fast_shop.ui.theme.*
import dev.shop.fast_shop.ui.component.ErrorText

@Composable
fun SignUpScreen(
    navController: NavController,
    viewModel: SignUpViewModel = viewModel()
) {
    val state = viewModel.state

    Box(modifier = Modifier.fillMaxSize()) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            FastShopTheme {
                Image(
                    painter = painterResource(id = R.drawable.login_background),
                    contentDescription = "background",
                    modifier = Modifier
                        .fillMaxSize()
                        .alpha(0.05f)
                )
                Column(
                    modifier = Modifier
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    Image(
                        painter = painterResource(id = R.drawable.retail),
                        contentDescription = "Logo",
                        modifier = Modifier
                            .offset(y = (20).dp),
                    )
                    Text(
                        text = "FAST SHOP",
                        fontSize = 48.sp,
                        modifier = Modifier
                            .offset(y = (10).dp),
                    )
                    Text(
                        text = "Registro de Usuário",
                        fontSize = 18.sp
                    )

                    OutlinedTextField(
                        value = state.email,
                        onValueChange = { viewModel.onEmailChange(it) },
                        label = { Text("Email") },
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = white,
                            unfocusedContainerColor = white,
                        ),
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Email,
                            imeAction = ImeAction.Next
                        )
                    )
                    OutlinedTextField(
                        value = state.password,
                        onValueChange = { viewModel.onPasswordChange(it) },
                        label = { Text("Senha") },
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = white,
                            unfocusedContainerColor = white,
                        ),
                        visualTransformation = PasswordVisualTransformation(),
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Password,
                            imeAction = ImeAction.Next
                        )
                    )
                    OutlinedTextField(
                        value = state.confirmPassword,
                        onValueChange = { viewModel.onConfirmPasswordChange(it) },
                        label = { Text("Confirmar Senha") },
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = white,
                            unfocusedContainerColor = white,
                        ),
                        visualTransformation = PasswordVisualTransformation(),
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Password,
                            imeAction = ImeAction.Done
                        )
                    )
                    Button(
                        onClick = {
                            if (viewModel.matchingPassword()){
                                viewModel.register {
                                    navController.navigate("Login")
                                }
                            }

                        }
                    ) {
                        Text("Registrar", fontSize = 18.sp)
                    }
                    if (state.showError) {
                        ErrorText(text = state.errorMessage)
                    }

                }
            }
        }
    }
}