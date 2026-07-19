package lk.spring_security.stateful_jwt_refresh_token_rotation.web.wallet.controller;

import jakarta.validation.Valid;
import lk.spring_security.stateful_jwt_refresh_token_rotation.domain.models.Wallet;
import lk.spring_security.stateful_jwt_refresh_token_rotation.usecase.wallet.WalletUseCase;
import lk.spring_security.stateful_jwt_refresh_token_rotation.web.wallet.DTOs.TransferRequestDTO;
import lk.spring_security.stateful_jwt_refresh_token_rotation.web.wallet.DTOs.WalletResponseDTO;
import lk.spring_security.stateful_jwt_refresh_token_rotation.web.wallet.webMapper.WalletWebMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/wallet")
public class WalletController {

    //inject required dependencies
    private final WalletUseCase walletUseCase;
    private final WalletWebMapper walletWebMapper;

    public WalletController(
            WalletUseCase walletUseCase,
            WalletWebMapper walletWebMapper
    ) {
        this.walletUseCase = walletUseCase;
        this.walletWebMapper = walletWebMapper;
    }

    //get wallet balance
    @GetMapping("/balance")
    public ResponseEntity<WalletResponseDTO> getWalletBalance(
            @AuthenticationPrincipal UserDetails userDetails
            ){
        //get user email
        String currentUserEmail = userDetails.getUsername();
        Wallet setWallet = walletUseCase.getWalletBalance(currentUserEmail);

        return ResponseEntity.ok(walletWebMapper.toResponseDTO(setWallet));
    }

    //deposit money
    @PostMapping("/deposit")
    public ResponseEntity<WalletResponseDTO> depositMoney(
            @AuthenticationPrincipal UserDetails userDetails,
            @Valid @RequestBody TransferRequestDTO  transferRequestDTO
            ){
        String currentUserEmail = userDetails.getUsername();
        Wallet updateWallet = walletUseCase.depositMoney(currentUserEmail, transferRequestDTO.getAmount());

        return ResponseEntity.ok(walletWebMapper.toResponseDTO(updateWallet));
    }
}
