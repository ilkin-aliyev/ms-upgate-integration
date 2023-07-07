package work.mayflower.ms.upgate.integration.dao

import org.hibernate.Hibernate
import work.mayflower.ms.upgate.integration.model.PaymentMethod
import work.mayflower.ms.upgate.integration.model.TransactionStatus
import work.mayflower.ms.upgate.integration.model.TransactionStatus.INITIALIZED
import java.math.BigDecimal
import java.time.LocalDateTime
import javax.persistence.Entity
import javax.persistence.EnumType.STRING
import javax.persistence.Enumerated
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "payments")
data class PaymentEntity(
    @Id
    var id: String,

    var amount: BigDecimal,

    var currencyCode: String,

    var countryCode: String,

    var customerId: String,

    @Enumerated(STRING)
    var transactionStatus: TransactionStatus? = INITIALIZED,

    @Enumerated(STRING)
    var paymentMethod: PaymentMethod,

    var createdAt: LocalDateTime? = LocalDateTime.now(),

    var updatedAt: LocalDateTime? = LocalDateTime.now()
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false
        other as PaymentEntity

        return id == other.id
    }

    override fun hashCode(): Int = javaClass.hashCode()
}