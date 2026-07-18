package lk.spring_security.stateful_jwt_refresh_token_rotation.web.wallet.webMapper;

import lk.spring_security.stateful_jwt_refresh_token_rotation.domain.models.Wallet;
import lk.spring_security.stateful_jwt_refresh_token_rotation.web.wallet.DTOs.WalletResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface WalletWebMapper {
    //domain model to responseDTO
    @Mapping(source = "user.email", target = "email")
    WalletResponseDTO toResponseDTO(Wallet wallet);
}
