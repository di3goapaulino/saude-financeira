package br.com.saudefinanceira.app.service;

import org.springframework.stereotype.Service;

import br.com.saudefinanceira.app.dto.CashflowProjectionResponse;
import br.com.saudefinanceira.app.dto.DashboardSummaryResponse;
import br.com.saudefinanceira.app.dto.LoanSummaryResponse;
import br.com.saudefinanceira.app.model.BankAccount;
import br.com.saudefinanceira.app.model.CreditCard;
import br.com.saudefinanceira.app.model.Loan;
import br.com.saudefinanceira.app.model.Subscription;
import br.com.saudefinanceira.app.model.Transaction;
import br.com.saudefinanceira.app.repository.BankAccountRepository;
import br.com.saudefinanceira.app.repository.CreditCardRepository;
import br.com.saudefinanceira.app.repository.LoanRepository;
import br.com.saudefinanceira.app.repository.SubscriptionRepository;
import br.com.saudefinanceira.app.repository.TransactionRepository;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

@Service
public class DashboardService {

    private final BankAccountRepository bankAccountRepo;
    private final CreditCardRepository creditCardRepo;
    private final LoanRepository loanRepo;
    private final SubscriptionRepository subscriptionRepo;
    private final TransactionRepository transactionRepo;

    public DashboardService(
            BankAccountRepository bankAccountRepo,
            CreditCardRepository creditCardRepo,
            LoanRepository loanRepo,
            SubscriptionRepository subscriptionRepo,
            TransactionRepository transactionRepo
    ) {
        this.bankAccountRepo = bankAccountRepo;
        this.creditCardRepo = creditCardRepo;
        this.loanRepo = loanRepo;
        this.subscriptionRepo = subscriptionRepo;
        this.transactionRepo = transactionRepo;
    }

    public DashboardSummaryResponse getSummary(String userId) {
        Double currentBalance = calculateCurrentBalance(userId);
        Double totalDebt = calculateTotalDebt(userId);
        Double creditCardDebt = calculateCreditCardDebt(userId);
        Double monthlySubscriptions = calculateMonthlySubscriptions(userId);
        Double monthlyFixedExpenses = calculateMonthlyFixedExpenses(userId);
        
        int daysRemainingInMonth = getDaysRemainingInMonth();
        Double dailyAvailableAmount = (currentBalance - monthlyFixedExpenses - monthlySubscriptions) / daysRemainingInMonth;

        return new DashboardSummaryResponse(
                currentBalance,
                totalDebt,
                creditCardDebt,
                monthlySubscriptions,
                monthlyFixedExpenses,
                Math.max(0, dailyAvailableAmount)
        );
    }

    public List<CashflowProjectionResponse> getCashflowProjection(String userId) {
        List<CashflowProjectionResponse> projections = new ArrayList<>();
        Double currentBalance = calculateCurrentBalance(userId);
        
        LocalDate today = LocalDate.now();
        for (int i = 0; i < 30; i++) {
            LocalDate date = today.plusDays(i);
            Double projectedBalance = currentBalance; // TODO: calcular com base em transações futuras
            projections.add(new CashflowProjectionResponse(date, projectedBalance));
        }
        
        return projections;
    }

    public LoanSummaryResponse getLoanSummary(String userId) {
        List<Loan> activeLoans = loanRepo.findByUserIdAndStatus(userId, "ACTIVE");
        
        Double totalDebt = activeLoans.stream()
                .mapToDouble(Loan::getRemainingBalance)
                .sum();
        
        Double monthlyCommitment = activeLoans.stream()
                .mapToDouble(Loan::getMonthlyInstallment)
                .sum();

        return new LoanSummaryResponse(
                totalDebt,
                activeLoans.size(),
                monthlyCommitment
        );
    }

    private Double calculateCurrentBalance(String userId) {
        return bankAccountRepo.findByUserId(userId).stream()
                .mapToDouble(BankAccount::getCurrentBalance)
                .sum();
    }

    private Double calculateTotalDebt(String userId) {
        Double loanDebt = loanRepo.findByUserIdAndStatus(userId, "ACTIVE").stream()
                .mapToDouble(Loan::getRemainingBalance)
                .sum();
        
        Double creditCardDebt = calculateCreditCardDebt(userId);
        
        return loanDebt + creditCardDebt;
    }

    private Double calculateCreditCardDebt(String userId) {
        return creditCardRepo.findByUserId(userId).stream()
                .mapToDouble(card -> card.getLimitAmount() - card.getAvailableLimit())
                .sum();
    }

    private Double calculateMonthlySubscriptions(String userId) {
        return subscriptionRepo.findByUserIdAndActive(userId, true).stream()
                .mapToDouble(Subscription::getAmount)
                .sum();
    }

    private Double calculateMonthlyFixedExpenses(String userId) {
        YearMonth currentMonth = YearMonth.now();
        List<Transaction> transactions = transactionRepo.findByUserId(userId);
        
        return transactions.stream()
                .filter(t -> t.getDate() != null && 
                           YearMonth.from(t.getDate()).equals(currentMonth))
                .mapToDouble(Transaction::getAmount)
                .sum();
    }

    private int getDaysRemainingInMonth() {
        LocalDate today = LocalDate.now();
        LocalDate lastDayOfMonth = today.withDayOfMonth(today.lengthOfMonth());
        return (int) java.time.temporal.ChronoUnit.DAYS.between(today, lastDayOfMonth) + 1;
    }
}
