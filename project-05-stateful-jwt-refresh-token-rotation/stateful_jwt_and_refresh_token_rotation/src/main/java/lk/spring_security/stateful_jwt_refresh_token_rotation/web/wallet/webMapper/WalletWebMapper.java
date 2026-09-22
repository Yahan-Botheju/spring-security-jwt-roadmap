package lk.spring_security.stateful_jwt_refresh_token_rotation.web.wallet.webMapper;


import lk.spring_security.stateful_jwt_refresh_token_rotation.usecase.wallet.records.*;
import lk.spring_security.stateful_jwt_refresh_token_rotation.web.wallet.DTOs.*;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WalletWebMapper {

     /* __DEPOSIT_MONEY__ */

    //requestDTO to usecase command
    DepositCommand toDepositCommand(DepositRequestDTO depositRequestDTO);

    //domain model to responseDTO
    DepositResponseDTO toDepositResponseDTO(DepositResult depositResult);


     /* __WALLET_BALANCE__ */

    //requestDTO to usecase command
    WalletBalanceCommand toBalanceCommand(WalletBalanceRequestDTO walletBalanceRequestDTO);

    //domain model to responseDTO
    WalletBalanceResponseDTO toBalanceResponseDTO(WalletBalanceResult walletBalanceResult);


     /* __WITHDRAW_MONEY__ */

    //requestDTO to usecase command
    WithdrawCommand toWithdrawCommand(WithdrawRequestDTO withdrawRequestDTO);

    //domain model to responseDTO
    WithdrawResponseDTO toWithdrawResponseDTO(WithdrawResult withdrawResult);
}
