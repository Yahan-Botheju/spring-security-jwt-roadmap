package lk.spring_security.stateful_jwt_refresh_token_rotation.usecase.wallet;

import lk.spring_security.stateful_jwt_refresh_token_rotation.usecase.wallet.records.WithdrawCommand;
import lk.spring_security.stateful_jwt_refresh_token_rotation.usecase.wallet.records.WithdrawResult;

public interface WithdrawMoneyUseCase {

    //withdraw money
    WithdrawResult withdrawMoney(WithdrawCommand withdrawCommand);
}
