package work.mayflower.ms.upgate.integration.model

enum class PaymentMethod {
    CARD, ALIPAY, ATM_UNION, EPS, SOFORT, GIROPAY, SEPA
}

enum class ProductType {
    SALE, SUBSCRIPTION
}

enum class PaymentType {
    SALE, AUTHORIZE, MIT_SALE, MIT_AUTHORIZE, RECURRING
}

enum class TransactionStatus {
    SUCCESS, DECLINE, ERROR, INITIALIZED
}

enum class TransactionType {
    THREE_DS, SALE, AUTHORIZE, PAYOUT, CHARGEBACK, REFUND, FRAUD_ALERT
}