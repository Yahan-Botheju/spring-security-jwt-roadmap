package lk.spring_security.stateful_jwt_refresh_token_rotation.web.wallet.controller;

import lk.spring_security.stateful_jwt_refresh_token_rotation.usecase.wallet.WalletUseCase;
import lk.spring_security.stateful_jwt_refresh_token_rotation.web.wallet.webMapper.WalletWebMapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
