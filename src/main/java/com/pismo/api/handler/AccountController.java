package com.pismo.api.handler;

import com.pismo.dynamodb.repository.AccountRepository;
import com.pismo.service.IAccountService;
import com.pismo.service.IOperationService;
import com.pismo.dynamodb.models.AccountDTO;
import com.pismo.dynamodb.models.OperationDTO;
import com.pismo.dynamodb.entity.Account;

import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Configuration
@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class AccountController {

    @Autowired
    IAccountService accountService;

    @Autowired
    IOperationService operationService;


    @Autowired
    AccountRepository repository;

    @PostMapping("/account")
    public ResponseEntity<AccountDTO> createAccount(@RequestBody AccountDTO dto) {
        accountService.create(dto);
        return ResponseEntity.ok().body(dto);
    }

    @PostMapping("/account/operation")
    public ResponseEntity<OperationDTO> createOperation(@RequestBody OperationDTO dto) {
        operationService.create(dto);
        return ResponseEntity.ok().body(dto);
    }

    @RequestMapping("/account")
    public ResponseEntity<List<Account>> handle(@RequestBody String code) {
        return ResponseEntity.ok().body(repository.findAllById("teste"));
    }
}

// @GetMapping("/products")
//     public List<ProductDto> getAllProducts() {
//         return StreamSupport
//                 .stream(productRepository.findAll().spliterator(), false)
//                 .map(ProductDto::new)
//                 .collect(Collectors.toList());
//     }

//     @GetMapping("/products/{username}")
//     public List<ProductDto> findByUsername(@PathVariable String username) {
//         return productRepository.findAllByPk(username)
//                 .stream()
//                 .map(ProductDto::new)
//                 .collect(Collectors.toList());
//     }

//     @GetMapping("/products/{username}/{code}")
//     public ResponseEntity<ProductDto> findByUsernameAndCode(@PathVariable String username, @PathVariable String code) {
//         return productRepository.findByPkAndSk(username, code)
//                 .map(product -> new ResponseEntity<>(new ProductDto(product), HttpStatus.OK))
//                 .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
//     }

//     @PostMapping("/products")
//     public ResponseEntity<ProductDto> saveProduct(
//             @RequestBody ProductDto productDto) {
//         Product productCreated = productRepository.save(new Product(productDto));

//         productEventPublisher.publishProductEvent(productCreated,
//                 EventType.PRODUCT_CREATED);

//         return new ResponseEntity<ProductDto>(new ProductDto(productCreated),
//                 HttpStatus.CREATED);
//     }

//     @PutMapping(path = "/products/{username}/{code}")
//     public ResponseEntity<ProductDto> updateProduct(@RequestBody ProductDto productDto,
//                                                     @PathVariable("username") String username,
//                                                     @PathVariable("code") String code) {
//         if (productRepository.findByPkAndSk(username, code).isPresent()) {
//             Product productUpdated = productRepository.save(new Product(productDto));

//             productEventPublisher.publishProductEvent(productUpdated,
//                     EventType.PRODUCT_UPDATED);

//             return new ResponseEntity<ProductDto>(new ProductDto(productUpdated),
//                     HttpStatus.OK);
//         } else {
//             return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//         }
//     }

//     @DeleteMapping(path = "/products/{username}/{code}")
//     public ResponseEntity<ProductDto> deleteProduct(@PathVariable("username") String username,
//                                                  @PathVariable("code") String code) {
//         Optional<Product> optProduct = productRepository.findByPkAndSk(username, code);
//         if (optProduct.isPresent()) {
//             Product product = optProduct.get();

//             productRepository.delete(product);

//             productEventPublisher.publishProductEvent(product,
//                     EventType.PRODUCT_DELETED);

//             return new ResponseEntity<ProductDto>(new ProductDto(product), HttpStatus.OK);
//         } else {
//             return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//         }
//     }