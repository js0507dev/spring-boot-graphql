package com.js0507dev.order.auth.entity;

import com.js0507dev.order.member.entity.Member;
import com.js0507dev.order.common.entity.AbstractTimestampEntity;
import jakarta.persistence.*;
import lombok.*;

@Getter @Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tokens")
public class Token extends AbstractTimestampEntity {
  @Id
  private String jwt;
  @Column(name = "member_id", insertable = false, updatable = false)
  private Long memberId;
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "member_id", referencedColumnName = "id")
  private Member member;
  @Column
  @Builder.Default
  private Boolean revoked = false;
  @Column
  private String revokeReason;
}
