package sptech.school.application.service;

import com.mercadopago.MercadoPagoConfig;
import com.mercadopago.client.payment.PaymentClient;
import com.mercadopago.client.payment.PaymentCreateRequest;
import com.mercadopago.client.common.IdentificationRequest;
import com.mercadopago.client.payment.PaymentPayerRequest;
import com.mercadopago.resources.payment.Payment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import sptech.school.domain.dto.payments.PaymentRequestDTO;

import java.time.OffsetDateTime;

@Service
public class PaymentService {
    private static final Logger logger = LoggerFactory.getLogger(PaymentService.class);
    private final String accessToken;

    public PaymentService(@Value("${mercadopago.access.token}") String accessToken) {
        this.accessToken = accessToken;

        if (accessToken == null || accessToken.isEmpty()) {
            logger.error("❌ ERRO: O Access Token do Mercado Pago não foi carregado!");
        } else {
            logger.info("✅ Mercado Pago Access Token carregado: {}", accessToken);
        }

        MercadoPagoConfig.setAccessToken(accessToken);
    }

    public Payment processPayment(PaymentRequestDTO request) throws Exception {
        try {
            MercadoPagoConfig.setAccessToken(accessToken);

            logger.info("📤 Enviando pagamento para o Mercado Pago...");
            logger.info("💰 Valor: {}", request.getTransactionAmount());
            logger.info("💳 Método de pagamento: {}", request.getPaymentMethodId());
            logger.info("📦 Descrição: {}", request.getDescription());
            logger.info("🔢 Parcelas: {}", request.getInstallments());
            logger.info("🧑 Payer Email: {}", request.getPayer().getEmail());
            logger.info("👤 Payer First Name: {}", request.getPayer().getIdentification().getType());
            logger.info("👤 Payer First Name: {}", request.getPayer().getIdentification().getNumber());

            PaymentClient client = new PaymentClient();

            PaymentPayerRequest payer = PaymentPayerRequest.builder()
                    .email(request.getPayer().getEmail())
                    .firstName(request.getPayer().getFirstName())
                    .identification(IdentificationRequest.builder()
                            .type(request.getPayer().getIdentification().getType())
                            .number(request.getPayer().getIdentification().getNumber())
                            .build())
                    .build();

            PaymentCreateRequest.PaymentCreateRequestBuilder paymentRequestBuilder = PaymentCreateRequest.builder()
                    .transactionAmount(request.getTransactionAmount())
                    .description(request.getDescription())
                    .paymentMethodId(request.getPaymentMethodId())
                    .payer(payer)
                    .installments(request.getInstallments());

            if ("pix".equalsIgnoreCase(request.getPaymentMethodId()) ||
                    "bolbradesco".equalsIgnoreCase(request.getPaymentMethodId())) {
                paymentRequestBuilder.dateOfExpiration(OffsetDateTime.now().plusDays(2));
            }

            else if (!"bolbradesco".equalsIgnoreCase(request.getPaymentMethodId())) {
                paymentRequestBuilder.token(request.getToken());
            }

            PaymentCreateRequest paymentCreateRequest = paymentRequestBuilder.build();
            Payment payment = client.create(paymentCreateRequest);

            logger.info("✅ Pagamento criado: ID={}, Status={}, Tipo={}",
                    payment.getId(), payment.getStatus(), request.getPaymentMethodId());

            return payment;

        } catch (Exception e) {
            logger.error("❌ Erro ao processar pagamento: {}", e.getMessage(), e);
            throw new Exception("Erro ao processar pagamento: " + e.getMessage());
        }
    }
}
