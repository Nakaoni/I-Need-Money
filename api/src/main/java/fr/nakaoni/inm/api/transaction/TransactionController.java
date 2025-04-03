package fr.nakaoni.inm.api.transaction;

import fr.nakaoni.inm.domain.transaction.Transaction;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@RestController
public class TransactionController {
    private final TransactionRepository transactionRepository;

    public TransactionController(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @GetMapping("/api/v1/transactions")
    public Set<Transaction> all() {
        Set<Transaction> transactions = new HashSet<>();
        transactionRepository.findAll().forEach(transactions::add);

        return transactions;
    }

    @PostMapping("/api/v1/transactions")
    public ResponseEntity<Transaction> create(@RequestBody Transaction createTransactionRequest) {
        Transaction transaction = new Transaction(
                createTransactionRequest.category(),
                createTransactionRequest.payee(),
                createTransactionRequest.account(),
                createTransactionRequest.type(),
                createTransactionRequest.amount(),
                createTransactionRequest.createdAt()
        );

        transaction = transactionRepository.save(transaction);

        URI accountUri = showUri(transaction.id());

        return ResponseEntity.created(accountUri).contentType(MediaType.APPLICATION_JSON).body(transaction);
    }

    URI showUri(Long id) {
        return ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(id).toUri();
    }

    @GetMapping("/api/v1/transactions/{id}")
    public ResponseEntity<?> show(@PathVariable(value = "id") Long id) {
        return ResponseEntity.ok(transactionRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND))
        );
    }

    @PatchMapping("/api/v1/transactions/{id}")
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @RequestBody TransactionDto transactionDto) {
        Transaction transaction = transactionRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        transaction.setComment(transactionDto.comment());
        transactionRepository.save(transaction);

        return ResponseEntity
                .noContent()
                .build();
    }

    @DeleteMapping("/api/v1/transactions/{id}")
    public ResponseEntity<?> remove(@PathVariable(value = "id") Long id) {

        Optional<Transaction> transaction = transactionRepository.findById(id);

        transaction.ifPresent(transactionRepository::delete);

        return ResponseEntity
                .accepted()
                .build();
    }
}
