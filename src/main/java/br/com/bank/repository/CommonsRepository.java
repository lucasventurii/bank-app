package br.com.bank.repository;

import br.com.bank.expcetion.NoFundsEnoughException;
import br.com.bank.model.Money;
import br.com.bank.model.MoneyAudit;
import br.com.bank.model.Wallet;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

import static br.com.bank.model.BankService.ACCOUNT;
import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public final class CommonsRepository {

        public static void checkFundsForTransaction(final Wallet source, final long amount){
            if (source.getFunds() < amount){
                throw new NoFundsEnoughException("Sua conta não tem dinheiro o suficiente para realizar essa transação");
            }

        }

    public static List<Money> generateMoney(final UUID transactionId, final long funds, final String description) {
        var history = new MoneyAudit(transactionId, ACCOUNT, description, OffsetDateTime.now());
        return Stream.generate(() -> new Money(history)).limit(funds).toList();

    }


}
