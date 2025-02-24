package dev.shop.fast_shop.ui.login

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.lifecycle.viewmodel.compose.viewModel
import dev.shop.fast_shop.R
import dev.shop.fast_shop.ui.theme.white
import dev.shop.fast_shop.ui.theme.FastShopTheme
import dev.shop.fast_shop.ui.component.ErrorText
import dev.shop.fast_shop.ui.component.SwipeToRevealLogin
import dev.shop.fast_shop.ui.home.HomeViewModel


@Composable
fun LoginScreen(
    navController: NavController,
    viewModel: LoginViewModel = viewModel(),
    homeViewModel: HomeViewModel = viewModel()
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
                        .fillMaxSize()
                        .padding(horizontal = 16.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.retail),
                        contentDescription = "Logo",
                        modifier = Modifier
                                .offset(y = (10).dp)
                    )

                    Text(
                        text = "FAST SHOP",
                        fontWeight = FontWeight(700),
                        fontSize = 48.sp
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

                    Spacer(modifier = Modifier.height(8.dp))

                    OutlinedTextField(
                        value = state.password,
                        onValueChange = { viewModel.onPasswordChange(it) },
                        label = { Text("Senha") },
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = white,
                            unfocusedContainerColor = white,
                        ),
                        visualTransformation = if (state.passwordVisible)
                            VisualTransformation.None
                        else
                            PasswordVisualTransformation(),
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Password,
                            imeAction = ImeAction.Done
                        ),
                        trailingIcon = {
                            IconButton(onClick = { viewModel.onTogglePasswordVisibility() }) {
                                Icon(
                                    imageVector = Icons.Filled.Lock,
                                    contentDescription = if (state.passwordVisible)
                                        "Ocultar senha"
                                    else
                                        "Mostrar senha"
                                )
                            }
                        }
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Button(
                        modifier = Modifier
                            .width(250.dp),
                        onClick = {
                            Log.d("uidUser", "UID DO USUARIO: ${homeViewModel.uidUser}")
                            viewModel.login {

                                navController.navigate("HomeScreen")
                            }
                        }
                    ) {
                        Text("Entrar", fontSize = 18.sp)
                    }
                    TextButton(
                        modifier = Modifier
                            .offset(y = (-10).dp),
                        onClick = {
                            navController.navigate("Login")
                        }
                    ){
                        Text("Esqueceu a senha?")
                    }
                    Button(
                        modifier = Modifier
                            .offset(y = (-20).dp)
                            .width(250.dp),
                        onClick = {
                            navController.navigate("SignUp")

                        }
                    ) {
                        Text("Cadastro", fontSize = 18.sp)
                    }




                    if (state.showError) {
                        ErrorText(R.string.error_login_failed)
                    }
                }

                SwipeToRevealLogin(drawableRes = R.drawable.login_splash)
            }
        }
    }
}

