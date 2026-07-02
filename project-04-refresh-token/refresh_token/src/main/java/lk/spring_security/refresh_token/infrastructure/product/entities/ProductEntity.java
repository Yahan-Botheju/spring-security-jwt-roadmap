package lk.spring_security.refresh_token.infrastructure.product.entities;

import jakarta.persistence.*;
import lk.spring_security.refresh_token.infrastructure.user.entities.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SoftDelete;

@Entity
@Table(name = "products")
@SoftDelete(columnName = "is_deleted")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    @Column(nullable = false)
    private String productName;

    @Column(nullable = false)
    private Double productPrice;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;
}
