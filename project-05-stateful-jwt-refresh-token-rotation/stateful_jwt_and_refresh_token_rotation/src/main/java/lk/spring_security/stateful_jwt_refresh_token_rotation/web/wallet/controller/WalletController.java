package lk.spring_security.stateful_jwt_refresh_token_rotation.web.wallet.controller;

import lk.spring_security.stateful_jwt_refresh_token_rotation.usecase.wallet.DepositMoneyUseCase;
import lk.spring_security.stateful_jwt_refresh_token_rotation.usecase.wallet.WalletBalanceUseCase;
import lk.spring_security.stateful_jwt_refresh_token_rotation.usecase.wallet.WithdrawMoneyUseCase;
import lk.spring_security.stateful_jwt_refresh_token_rotation.usecase.wallet.records.WalletBalanceCommand;
import lk.spring_security.stateful_jwt_refresh_token_rotation.usecase.wallet.records.WalletBalanceResult;
import lk.spring_security.stateful_jwt_refresh_token_rotation.web.wallet.DTOs.WalletBalanceRequestDTO;
import lk.spring_security.stateful_jwt_refresh_token_rotation.web.wallet.DTOs.WalletBalanceResponseDTO;
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
        WalletBalanceRequestDTO  walletBalanceRequestDTO = new WalletBalanceRequestDTO(currentUserEmail);

        WalletBalanceCommand toCommand = walletWebMapper.toBalanceCommand(walletBalanceRequestDTO);
        WalletBalanceResult toUseCase = walletBalanceUseCase.getWalletBalance(toCommand);
        WalletBalanceResponseDTO responseDTO = walletWebMapper.toBalanceResponseDTO(toUseCase);

        return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
    }

    //deposit money
//    @PostMapping("/deposit")
//    public ResponseEntity<WalletResponseDTO> depositMoney(
//            @AuthenticationPrincipal UserDetails userDetails,
//            @Valid @RequestBody TransferRequestDTO  transferRequestDTO
//            ){
//        String currentUserEmail = userDetails.getUsername();
//        Wallet updateWallet = walletUseCase.depositMoney(currentUserEmail, transferRequestDTO.getAmount());
//
//        return ResponseEntity.ok(walletWebMapper.toResponseDTO(updateWallet));
//    }

    //withdraw money
//    @PostMapping("/withdraw")
//    public ResponseEntity<WalletResponseDTO> withdrawMoney(
//            @AuthenticationPrincipal UserDetails userDetails,
//            @Valid @RequestBody TransferRequestDTO  transferRequestDTO
//    ){
//        String currentUserEmail = userDetails.getUsername();
//        Wallet updatedWallet = walletUseCase.withdrawMoney(currentUserEmail, transferRequestDTO.getAmount());
//
//        return ResponseEntity.ok(walletWebMapper.toResponseDTO(updatedWallet));
//    }
}
