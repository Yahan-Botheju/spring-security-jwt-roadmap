package lk.spring_security.stateful_jwt_refresh_token_rotation.usecase.auth;

import lk.spring_security.stateful_jwt_refresh_token_rotation.domain.models.User;
import lk.spring_security.stateful_jwt_refresh_token_rotation.domain.models.Wallet;
import lk.spring_security.stateful_jwt_refresh_token_rotation.domain.repositories.IdentityProvider;
import lk.spring_security.stateful_jwt_refresh_token_rotation.domain.repositories.UserRepository;
import lk.spring_security.stateful_jwt_refresh_token_rotation.domain.repositories.WalletRepository;
import lk.spring_security.stateful_jwt_refresh_token_rotation.usecase.auth.records.RegisterUseCommand;
import lk.spring_security.stateful_jwt_refresh_token_rotation.usecase.auth.records.RegisterUserResult;

public class RegisterUserUseCaseImpl implements RegisterUserUseCase {

    //inject required dependencies
    private final UserRepository userRepository;
    private final IdentityProvider identityProvider;
    private final WalletRepository walletRepository;

    public RegisterUserUseCaseImpl(
            UserRepository userRepository,
            IdentityProvider identityProvider,
            WalletRepository walletRepository
    ) {
        this.userRepository = userRepository;
        this.identityProvider = identityProvider;
        this.walletRepository = walletRepository;
    }

    @Override
    public RegisterUserResult registerUser(RegisterUseCommand registerUseCommand){

        //check user existence by email
        if(userRepository.findByEmail(registerUseCommand.email()).isPresent()){
            throw new IllegalStateException("User already exists");
        }

        //create new user model
        User newUser = User.createNewUser(
                registerUseCommand.email(),
                identityProvider.passwordEncoder(registerUseCommand.password())
        );

        //save user
        User savedUser = userRepository.registerUser(newUser);

        //create default wallet for new user
        Wallet newWallet = Wallet.createNewWallet(
                savedUser
        );

        //save wallet
        walletRepository.saveWallet(newWallet);

        return new RegisterUserResult(
                savedUser.getUserId(),
                savedUser.getEmail(),
                savedUser.getRole().name(),
                newWallet.getWalletId(),
                newWallet.getWalletBalance()
        );
    }
}
