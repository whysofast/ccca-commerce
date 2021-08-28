package com.ccca.commerce.infra.repository.database

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ItemJpaRepository : JpaRepository<ItemDBO, Long>