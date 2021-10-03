package com.ccca.commerce.unit

import com.ccca.commerce.application.PlaceOrderInputDto
import com.ccca.commerce.application.PlaceOrderInputRESTDto
import com.ccca.commerce.application.PlaceOrderUseCase
import com.ccca.commerce.domain.entity.Cpf
import com.ccca.commerce.domain.entity.OrderItem
import com.google.gson.Gson
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import java.time.LocalDate


@SpringBootTest
@AutoConfigureMockMvc
class ApiTest {

    @Autowired
    lateinit var mvc: MockMvc

    @Autowired
    lateinit var placeOrderUseCase: PlaceOrderUseCase

    @Test
    fun `should get order's code via API`() {

        val orderInputDto = PlaceOrderInputDto(
            cpf = Cpf("01234567890"),
            zipcode = "45000000",
            items = listOf(
                OrderItem("1", 101, 1),
            ),
            coupon = ""
        )

        placeOrderUseCase.execute(orderInputDto)

        val code = "202100000001"

        mvc.perform(
            MockMvcRequestBuilders.get("/orders/${code}")
                .contentType(MediaType.APPLICATION_JSON)
        )
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.jsonPath("$.code").value("202100000001"))

    }

    @Test
    fun `should place an order via API`() {

        val orderInputDto = PlaceOrderInputRESTDto(
            cpf = Cpf("01234567890"),
            zipcode = "45000000",
            items = listOf(
                OrderItem("1", 100, 1),
                OrderItem("2", 200, 2),
                OrderItem("3", 500, 3)
            ),
            coupon = "VALE20"
        )

        val body = Gson().toJson(orderInputDto)

        mvc.perform(
            MockMvcRequestBuilders.post("/orders")
                .contentType(MediaType.APPLICATION_JSON)
                .content(body)
        )
            .andExpect(MockMvcResultMatchers.jsonPath("$.total").value(2920L))

    }
}