package lk.spring_security.stateful_jwt_refresh_token_rotation.web.wallet.controller;

import jakarta.validation.Valid;
import lk.spring_security.stateful_jwt_refresh_token_rotation.usecase.wallet.DepositMoneyUseCase;
import lk.spring_security.stateful_jwt_refresh_token_rotation.usecase.wallet.WalletBalanceUseCase;
import lk.spring_security.stateful_jwt_refresh_token_rotation.usecase.wallet.WithdrawMoneyUseCase;
import lk.spring_security.stateful_jwt_refresh_token_rotation.usecase.wallet.records.*;
import lk.spring_security.stateful_jwt_refresh_token_rotation.web.wallet.DTOs.*;
import lk.spring_security.stateful_jwt_refresh_token_rotation.web.wallet.webMapper.WalletWebMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/wallet")
public class WalletController {

    //inject required dependencies
    private final DepositMoneyUseCase depositMoneyUseCase;
    private final WithdrawMoneyUseCase withdrawMoneyUseCase;
    private final WalletBalanceUseCase walletBalanceUseCase;
    private final WalletWebMapper walletWebMapper;

    public WalletController(
            DepositMoneyUseCase depositMoneyUseCase,
            WithdrawMoneyUseCase withdrawMoneyUseCase,
            WalletBalanceUseCase walletBalanceUseCase,
            WalletWebMapper walletWebMapper
    ) {
        this.depositMoneyUseCase = depositMoneyUseCase;
        this.withdrawMoneyUseCase = withdrawMoneyUseCase;
        this.walletBalanceUseCase = walletBalanceUseCase;
        this.walletWebMapper = walletWebMapper;
    }

    //get wallet balance
    @GetMapping("/balance")
    public ResponseEntity<WalletBalanceResponseDTO> getWalletBalance(
            @AuthenticationPrincipal UserDetails userDetails
            ){
        //get user email
        String currentUserEmail = userDetails.getUsername();
        //make requestDto
        WalletBalanceRequestDTO  walletBalanceRequestDTO = new WalletBalanceRequestDTO(currentUserEmail);

        //turn to command
        WalletBalanceCommand toCommand = walletWebMapper.toBalanceCommand(walletBalanceRequestDTO);
        //set to usecase
        WalletBalanceResult toUseCase = walletBalanceUseCase.getWalletBalance(toCommand);
        //turn to response obj
        WalletBalanceResponseDTO responseDTO = walletWebMapper.toBalanceResponseDTO(toUseCase);

        return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
    }

    //deposit money
    @PostMapping("/deposit")
    public ResponseEntity<DepositResponseDTO> depositMoney(
            @AuthenticationPrincipal UserDetails userDetails,
            @Valid @RequestBody DepositRequestDTO depositRequestDTO
            ){

        String currentUserEmail = userDetails.getUsername();
        DepositRequestDTO toDepositRequestDTO = new DepositRequestDTO(currentUserEmail,depositRequestDTO.getAmount());

        DepositCommand toCommand = walletWebMapper.toDepositCommand(toDepositRequestDTO);
        DepositResult toUseCase = depositMoneyUseCase.depositMoney(toCommand);
        DepositResponseDTO responseDTO = walletWebMapper.toDepositResponseDTO(toUseCase);

        return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
    }

    //withdraw money
    @PostMapping("/withdraw")
    public ResponseEntity<WithdrawResponseDTO> withdrawMoney(
            @AuthenticationPrincipal UserDetails userDetails,
            @Valid @RequestBody WithdrawRequestDTO withdrawRequestDTO
    ){
        String currentUserEmail = userDetails.getUsername();
        WithdrawRequestDTO toWithdrawRequestDTO = new WithdrawRequestDTO(currentUserEmail,withdrawRequestDTO.getAmount());

        WithdrawCommand toCommand = walletWebMapper.toWithdrawCommand(toWithdrawRequestDTO);
        WithdrawResult toUseCase = withdrawMoneyUseCase.withdrawMoney(toCommand);
        WithdrawResponseDTO responseDTO = walletWebMapper.toWithdrawResponseDTO(toUseCase);

        return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
    }
}
