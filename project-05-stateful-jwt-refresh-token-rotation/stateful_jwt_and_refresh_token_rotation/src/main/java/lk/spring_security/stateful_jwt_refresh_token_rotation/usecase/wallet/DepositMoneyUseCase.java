package lk.spring_security.stateful_jwt_refresh_token_rotation.usecase.wallet;

import lk.spring_security.stateful_jwt_refresh_token_rotation.usecase.wallet.records.DepositCommand;
import lk.spring_security.stateful_jwt_refresh_token_rotation.usecase.wallet.records.DepositResult;

public interface DepositMoneyUseCase {

    //get deposit amount
    DepositResult depositMoney(DepositCommand depositCommand);
}
