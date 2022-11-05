package hellojpa;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
//@Table(name = "MBR")
public class Member {

    @Id
    private Long id;

    @Column(name = "name")
    private String username;

    private Integer age;

    @Enumerated(EnumType.STRING)    // 기본값인 Ordinal 사용 X!
    private RoleType roleType;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;

    // LocalDate & LocalDateTime은 annotation 없어도 JPA에서 date 타입으로 인식한다.
    private LocalDate testLocalDate;
    private LocalDateTime testLocalDateTime;

    @Lob
    private String description;

    // 테이블과 매핑하지 않는 컬럼에 붙인다.
    @Transient
    private int temp;

    public Member() {
    }


}
