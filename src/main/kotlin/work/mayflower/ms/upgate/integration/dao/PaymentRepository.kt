package work.mayflower.ms.upgate.integration.dao

import org.springframework.data.repository.CrudRepository

interface PaymentRepository : CrudRepository<PaymentEntity, String>