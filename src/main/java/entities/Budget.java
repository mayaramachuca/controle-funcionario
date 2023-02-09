package entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@AllArgsConstructor
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public enum Budget {
    BUDGET_RH(BigDecimal.valueOf(25000.00)),
    BUDGET_VENDAS(BigDecimal.valueOf(15000.00)),
    BUDGET_MARKETING(BigDecimal.valueOf(35000.00));

    BigDecimal valorBudget;

}
