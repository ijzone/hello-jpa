package hellojpa;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @ManyToMany는 실무에서 사용 X
 * 이러한 구조가 필요하면 중간테이블을 엔티티로 승격 시켜서 사용한다.
 */
@Entity
public class MemberProduct {

    @Id @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "PRODUCT_ID")
    private Product product;

    private int count;
    private int price;

    private LocalDateTime orderDateTime;
}
