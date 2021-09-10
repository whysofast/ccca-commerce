package com.ccca.commerce.infra.repository.adapter.memory

import com.ccca.commerce.domain.entity.TaxTable
import com.ccca.commerce.domain.repository.port.TaxTableRepository
import com.ccca.commerce.infra.repository.database.TaxTableJpaRepository
import org.springframework.stereotype.Component

// Port -> (Adapter) -> Repository <= JpaRepository
@Component
class TaxTableRepositoryMemory(
    private val taxTableJpaRepository: TaxTableJpaRepository
) : TaxTableRepository {

    override fun getByItemId(id: String): List<TaxTable?> {
        val taxTablesFound = taxTableJpaRepository.findAllByItemId(id.toLong())
        

        return taxTablesFound.map { it?.toModel() }
    }

}
