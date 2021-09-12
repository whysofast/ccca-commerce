package com.ccca.commerce.unit

import com.ccca.commerce.application.PlaceOrderInputDto
import com.ccca.commerce.application.PlaceOrderUseCase
import com.ccca.commerce.domain.entity.Cpf
import com.ccca.commerce.domain.entity.OrderItem
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers


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
}