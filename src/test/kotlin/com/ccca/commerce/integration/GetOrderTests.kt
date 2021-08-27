package com.ccca.commerce.integration

import com.ccca.commerce.application.GetOrderUseCase
import com.ccca.commerce.application.PlaceOrderInputDto
import com.ccca.commerce.application.PlaceOrderUseCase
import com.ccca.commerce.domain.entity.Cpf
import com.ccca.commerce.domain.entity.OrderItem
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class GetOrderTests {

    @Autowired
    private lateinit var placeOrderUseCase: PlaceOrderUseCase

    @Autowired
    private lateinit var getOrderUseCase: GetOrderUseCase

    @Test
    fun `should get order`() {
        val orderInputDto = PlaceOrderInputDto(
            cpf = Cpf("01234567890"),
            zipcode = "45000000",
            items = listOf(
                OrderItem("1", 100, 1),
                OrderItem("2", 200, 2),
                OrderItem("3", 500, 3)
            ),
            coupon = "VALE20"
        )

        val orderOutputDto = placeOrderUseCase.execute(orderInputDto)

        val getOrderOutputDto = getOrderUseCase.execute(orderOutputDto.code.value)

        Assertions.assertEquals(getOrderOutputDto.total, 2920)
    }
}