package ru.shcheglov.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@ToString(exclude = "currency")
@NoArgsConstructor
@RequiredArgsConstructor
@Table(name = "app_currency_quotas")
public class CurrencyQuota {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "general-generator")
    @SequenceGenerator(name = "general-generator", sequenceName = "SQ_GENERAL", allocationSize = 1)
    @Column(name = "QUOTA_ID", nullable = false, unique = true)
    private Long id;

    @NonNull
    @Column(name = "DATE", nullable = false)
    private LocalDate date;

    @NonNull
    @Column(name = "VALUE", nullable = false)
    private Double value;

    @CreationTimestamp
    @Column(name = "CREATED", nullable = false)
    private LocalDateTime created;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn(name = "CURRENCY_FID", referencedColumnName = "CURRENCY_ID")
    private Currency currency;
}
